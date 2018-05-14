package util;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.configurations.TestConfiguration;
import util.enums.BrowserType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;


@Log4j
public class SeleniumExecutor {

    public final static int TIMEOUT = 60;
    public final static int LONG_TIMEOUT = 120;
    public final static int MIDDLE_TIMEOUT = 90;
    public final static int SHORT_TIMEOUT = 3;
    public final static int TINY_TIMEOUT = 3;
    public final static int MINIMUM_TIMEOUT = 1;

    public final static int PAGE_LOAD_TIMEOUT = 100;
    public final static int VERY_LONG_TIMEOUT = 300;

    public final static int SHORT_TIME_FOR_THREAD = 1000;
    public final static int MEDIUM_TIME_FOR_THREAD = 3000;
    public final static int LONG_TIME_FOR_THREAD = 6000;


    private static SeleniumExecutor executor;
    private static WebDriver driver;
    private static WebDriverWait waitDriver;

    private static String parentWindowHandle;
    private static Iterator<String> windowIterator;

    public static String pageDefaultUrl;

    private SeleniumExecutor() {

        driver = createDriver();

        parentWindowHandle = driver.getWindowHandle();

        pageDefaultUrl = TestConfiguration.baseUri;
    }

    public static void stop() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
        executor = null;
    }

    private static void startExecutor() {
        if (executor == null)
            executor = new SeleniumExecutor();
    }

    public static SeleniumExecutor getExecutor() {
        startExecutor();
        return executor;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWaitDriver() {
        return waitDriver;
    }

    public static String getParentWindowHandle() {
        return parentWindowHandle;
    }

    public static void setParentWindowHandle(String windowHandle) {
        parentWindowHandle = windowHandle;
    }

    public static Iterator<String> getWindowIterator() {
        return windowIterator;
    }

    public static void setWindowIterator() {
        windowIterator = driver.getWindowHandles().iterator();
    }

    private static BrowserType getBrowserTypeFromString(String val) {
        switch (val.toLowerCase()) {
            case "chrome":
                return BrowserType.chrome;
            case "firefox":
                return BrowserType.firefox;
            case "ie":
                return BrowserType.ie;
            default:
                return null;
        }
    }

    private static WebDriver createDriver() {
        BrowserType browserParameter = TestConfiguration.browserParameter;
        log.info("Creating webDriver");

        DesiredCapabilities capabilities = null;

        switch (browserParameter) {
            case chrome:
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", TestConfiguration.downloadFolderPath);
                chromePrefs.put("download.prompt_for_download", false);
                chromePrefs.put("download.directory_upgrade", true);
                chromePrefs.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-print-preview");

                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);

                if (System.getProperty("os.name").startsWith("Windows"))
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//chrome//chromedriver.exe");
                else
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//chrome//chromedriver");

                driver = new ChromeDriver(capabilities);
                break;

            case firefox:
                if (System.getProperty("os.name").startsWith("Windows"))
                    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//gecko//geckodriver.exe");
                else
                    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//gecko//geckodriver");

                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("browser.download.folderList", 2);
                firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
                firefoxProfile.setPreference("browser.download.dir", TestConfiguration.downloadFolderPath);
                firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel,application/pdf");
                firefoxProfile.setPreference("pdfjs.disabled", true);
                firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
                firefoxProfile.setPreference("plugin.scan.plid.all", false);

                driver = new FirefoxDriver(firefoxProfile);
                break;

            case ie:
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "//IE//IEDriverServer.exe");

                driver = new InternetExplorerDriver();
                break;
        }

        driver.manage().window().maximize();
        waitDriver = new WebDriverWait(driver, TIMEOUT);
        driver.manage().timeouts().pageLoadTimeout(SeleniumExecutor.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

        return driver;
    }

    public static String getTitle() {
        return driver.getTitle();
    }

    public static String getUrl() {
        return driver.getCurrentUrl();
    }

    public void deleteCookies() {
        try {
            driver.manage().deleteAllCookies();
            pause(SHORT_TIME_FOR_THREAD);
        } catch (Exception e) {
            log.error("Unable to clear cookies", e);
        }
    }

    public void deleteLocalStorage() {
        try {
            LocalStorage local = ((WebStorage) driver).getLocalStorage();
            local.clear();
        } catch (WebDriverException e) {
            log.error("Unable to clear local storage", e);
        }
    }

    public void openPage(String url) {
        try {
            driver.navigate().to(url);
        } catch (UnhandledAlertException e) {
            driver.switchTo().alert().accept();
            driver.navigate().to(url);
        }
    }

    public static void setWaitDriverTimeOut(int timeout) {
        waitDriver = new WebDriverWait(driver, timeout);
    }

    public static void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void refreshPage() {
        driver.navigate().refresh();
    }
}