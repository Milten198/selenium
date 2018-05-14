package pages.executors;

import org.openqa.selenium.support.PageFactory;
import pages.locators.LoggedLocators;
import util.SeleniumExecutor;
import util.helpers.DownloadFileHelper;
import util.helpers.FileHelper;
import util.helpers.SeleniumExtender;

public class LoggedPage {

    private LoggedLocators locators;
    private DownloadFileHelper downloadFileHelper;
    private static final String FILE_URL = "https://testingcup.pgs-soft.com/task_6/download";
    private static final String DOWNLOAD_PATH = "D:\\Repo\\PGSAutomatedTests\\pgs-java-automated-tests\\downloads";
    private static final String FILE_NAME = "pgs_cv.jpg";

    public LoggedPage() {
        locators = new LoggedLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
        downloadFileHelper = new DownloadFileHelper();
    }

    public boolean checkLoggedPageIsVisible() {
        return SeleniumExtender.SeleniumIsDisplayed.isElementDisplayedWithCorrectText(locators.downloadLink, "Pobierz plik");
    }

    public void clickLogoutLink() {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.logoutLink);
    }

    public void clickDownloadLink() {
        SeleniumExtender.SeleniumClick.clickWithWait(locators.downloadLink);
    }

    public boolean checkIfFileWasDownloaded() {
        return downloadFileHelper.isFileDownloaded(DOWNLOAD_PATH, FILE_NAME);
    }

    public void cleanDownloadFolder() {
        FileHelper.cleanDirectory(DOWNLOAD_PATH);
    }

    public void downloadLinkIsVisible() {
        SeleniumExtender.SeleniumIsDisplayed.isElementDisplayed(locators.downloadLink);
    }
}
