package util.helpers;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import cucumber.api.Scenario;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import util.SeleniumExecutor;
import util.enums.AttributeType;
import util.exceptions.CollectionSizeException;
import util.exceptions.NoMultipleAttribute;

import javax.imageio.ImageIO;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static util.helpers.SeleniumExtender.SeleniumClear.clearWithWait;
import static util.helpers.SeleniumExtender.SeleniumCloseWindow.closeAnyNotParentWindow;
import static util.helpers.SeleniumExtender.SeleniumGetElement.getElementWithTextFromElementList;
import static util.helpers.SeleniumExtender.SeleniumIsPresent.*;
import static util.helpers.SeleniumExtender.SeleniumScroll.scrollToElement;
import static util.helpers.SeleniumExtender.SeleniumSwitchWindow.*;
import static util.helpers.SeleniumExtender.SeleniumWait.*;

@Log4j
public class SeleniumExtender {


    public static class SeleniumScroll {

        public static WebElement scrollToElement(WebElement element) {
            WebDriver driver = SeleniumExecutor.getDriver();
            String position = Integer.toString(element.getLocation().getY() / 2);
            String script = String.format("window.scroll(0, %s)", position);
            ((JavascriptExecutor) driver).executeScript(script);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return element;
        }
    }

    public static class SeleniumClick {

        public static void clickWithWait(WebElement element) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(element);
            waitForElementToBeClickable(element);
            element.click();
        }

        public static void doubleClickWithWait(WebElement element) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(element);
            waitForElementToBeClickable(element);
            Actions action = new Actions(SeleniumExecutor.getDriver());
            action.doubleClick(element).perform();
        }

        public static void clickRightWithWait(WebElement element) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(element);
            waitForElementToBeClickable(element);

            Actions action = new Actions(SeleniumExecutor.getDriver()).contextClick(element);
            action.build().perform();
        }

        public static void clickWithWaitWithoutAjaxReady(WebElement element) {
            waitForElementToBeDisplayed(element);
            waitForElementToBeClickable(element);
            element.click();
            System.out.println("Clicked");
        }

        public static void clickWithWait(WebElement element, int number) {

            for (int i = 0; i < number; i++) {
                waitForNoAjaxRequestsPending();
                waitForElementToBeDisplayed(element);
                waitForElementToBeClickable(element);
                element.click();
            }
        }

        public static void clickElementFromListByPosition(List<WebElement> webElements, int position) {
            waitForNoAjaxRequestsPending();
            waitForElementsIsDisplayed(webElements);
            clickWithWait(webElements.get(position));
        }

        public static void clickElementWithTextFromElementList(List<WebElement> elements, String searchWord) {
            try {
                clickElementWithAttributeValue(elements, searchWord);
            } catch (NullPointerException e) {
                clickElementWithGetText(elements, searchWord);
            }
        }

        private static void clickElementWithGetText(List<WebElement> elements, String searchWord) {
            waitForElementsIsDisplayed(elements);

            Optional<WebElement> searchElement = elements.stream()
                    .filter(x -> x.getText().trim().equals(searchWord))
                    .findFirst();

            if (searchElement.isPresent()) {
                searchElement.get().click();
            } else {
                throw new NullPointerException("List don't contain item with text: " + searchWord);
            }
        }

        private static void clickElementWithAttributeValue(List<WebElement> elements, String searchWord) {
            waitForElementsIsDisplayed(elements);

            elements.stream()
                    .filter(x -> x.getAttribute("value").trim().equals(searchWord))
                    .findFirst()
                    .get()
                    .click();
        }

        public static void clickElementRandomFromElementList(List<WebElement> webElements, int howMany) {
            for (int i = 0; i <= howMany; i++) {
                getElementWithTextFromElementList(webElements, "any").click();
            }
        }
    }

    public static class SeleniumDragAndDrop {

        public static void dragElementWithTextAndDrop(List<WebElement> drags, String dragText, WebElement drop) {
            WebElement drag = getElementWithTextFromElementList(drags, dragText);

            dragAndDropElement(drop, drag);
        }

        public static void dragAndDropElementWithoutWait(WebElement drag, WebElement drop) {
            Actions actions = new Actions(SeleniumExecutor.getDriver());
            actions.dragAndDrop(drag, drop)
                    .build()
                    .perform();
        }

        private static void dragAndDropElement(WebElement drop, WebElement drag) {
            isElementPresentWithWait(drag);
            isElementPresentWithWait(drop);

            Actions actions = new Actions(SeleniumExecutor.getDriver());
            actions.dragAndDrop(drag, drop)
                    .build()
                    .perform();
        }
    }

    public static class SeleniumSendKeys {

        public static void clearBeforeSendKeys(WebElement e, String text) {
            clearWithWait(e);
            e.sendKeys(text);
        }

        public static void sendKeysWithWait(WebElement e, String text) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(e);
            e.sendKeys(text);
        }

        public static void sendKeysWithWaitWithoutAjaxReady(WebElement e, String text) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(e);
            e.sendKeys(text);
        }

        public static void clearBeforeSendKeysWithWait(WebElement e, String text) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(e);
            e.clear();
            e.sendKeys(text);
        }

        public static void sendKeysWithoutWait(WebElement e, String text) {
            e.sendKeys(text);
        }
    }

    public static class SeleniumDropDown {

        public static void selectFromDropDown(WebElement dropDown, String value) {
            isElementPresentWithWait(dropDown);
            Select select = new Select(dropDown);
            waitForSelectOptionsIsPresents(select);
            select.selectByVisibleText(value);
        }

        public static void selectMultipleOptionsFromDropDown(WebElement dropDown, List<String> values) {
            isElementPresentWithWait(dropDown);
            Select select = new Select(dropDown);

            if (select.isMultiple()) {
                waitForSelectOptionsIsPresents(select);
                values.forEach(select::selectByVisibleText);
            } else {
                throw new NoMultipleAttribute("DropDown is not multiple");
            }
        }

        private static void waitForSelectOptionsIsPresents(Select select) {
            SeleniumExecutor.getWaitDriver()
                    .until((Predicate<WebDriver>) x -> !select.getOptions().isEmpty());
        }

        public static void selectFromDropDownWithWait(WebElement dropDown, String value) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(dropDown);
            Select select = new Select(dropDown);
            select.selectByVisibleText(value);
        }

        public static void selectFromDropDownByValueWithWait(WebElement dropDown, String value) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(dropDown);
            Select select = new Select(dropDown);
            select.selectByValue(value);
        }

        public static void selectFromDropDownByIndexWithWait(WebElement dropDown, int index) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(dropDown);
            Select select = new Select(dropDown);
            select.selectByIndex(index);
        }

        public static String getFirstSelectionFromDropDown(WebElement dropDown) {
            waitForNoAjaxRequestsPending();
            Select select = new Select(dropDown);
            return select.getFirstSelectedOption().getText();
        }

        public static void selectRandomOptionFromDropDown(WebElement dropdown) {
            Random rand = new Random();
            waitForNoAjaxRequestsPending();
            Select select = new Select(dropdown);
            List<WebElement> allOptions = select.getOptions();
            select.selectByIndex(rand.nextInt(allOptions.size() - 1));
        }

        public static List<WebElement> getAllSelectedFromDropDown(WebElement dropDown) {
            waitForNoAjaxRequestsPending();
            Select select = new Select(dropDown);
            return select.getAllSelectedOptions();
        }

        public static List<String> getAllSelectedAsTextFromDropDown(WebElement dropDown) {
            return SeleniumGetText.getTextListFromElementList(getAllSelectedFromDropDown(dropDown));
        }
    }

    public static class SeleniumClear {

        public static void clearWithWait(WebElement e) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(e);
            e.clear();
        }

        public static void clearWithWaitWithoutAjaxReady(WebElement e) {
            waitForElementToBeDisplayed(e);
            e.clear();
        }

        public static void clearWithWaitByKeys(WebElement e) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(e);
            e.sendKeys(Keys.CONTROL + "a");
            e.sendKeys(Keys.DELETE);
        }
    }

    public static class SeleniumGetText {

        public static List<String> getTextListFromElementList(List<WebElement> list) {
            List<String> finalList = new ArrayList<String>();
            list.stream().forEach(x -> finalList.add(x.getText()));

            return finalList;
        }

        public static String getTextFromElementList(List<WebElement> list) {
            return getTextFromElementList(list, "any");
        }

        public static String getTextFromElementList(List<WebElement> list, String caption) {
            if (caption.equals("any")) {
                Random r = new Random();
                caption = list.get(r.nextInt(list.size())).getText();
            }
            return caption;
        }

        public static String getTextWithWait(WebElement e) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(e);
            return e.getText();
        }

        public static String getValueWithWait(WebElement e) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(e);
            return e.getAttribute("value");
        }
    }

    public static class SeleniumFind {

        public static WebElement findElementWithWait(WebElement e, By by) {
            waitForNoAjaxRequestsPending();
            WebElement element = e.findElement(by);
            scrollToElement(element);
            return element;
        }

        public static List<WebElement> findElementsWithWait(WebElement e, By by) {
            waitForNoAjaxRequestsPending();
            return e.findElements(by);
        }

        //doesn't take a parent element so searches the entire document object
        public static List<WebElement> findElementsWithWait(By by) {
            waitForNoAjaxRequestsPending();
            return SeleniumExecutor.getDriver().findElements(by);
        }

        public static WebElement findElement(By locator) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            return waitDriver.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }

        public static List<WebElement> findElements(By locator) {
            return SeleniumExecutor.getDriver().findElements(locator);
        }
    }

    public static class SeleniumGetElement {

        public static WebElement getElementRandomFromElementList(List<WebElement> list) {
            return getElementWithTextFromElementList(list, "any");
        }

        public static WebElement getElementWithTextFromElementList(List<WebElement> elements, String caption) {
            isListOfElementsPresentWithWait(elements);

            if (caption.equals("any")) {
                Random r = new Random();
                caption = elements.get(r.nextInt(elements.size())).getText();
            }
            final String finalCaption = caption;
            return elements.stream().filter(element -> element.getText().trim().contains(finalCaption)).findFirst().get();
        }

        public static long getNumberOfElementsWithoutTextFromElementList(List<WebElement> list, String finalCaption) {
            return list.stream().filter(element -> !element.getText().contains(finalCaption)).count();
        }
    }

    public static class SeleniumGetAttribute {

        public static String getElementAttribute(WebElement webElement, AttributeType attribute) {
            return webElement.getAttribute(attribute.toString());
        }

        public static String getAttributeWithWait(WebElement e, String attribute) {
            waitForNoAjaxRequestsPending();
            return e.getAttribute(attribute);
        }
    }

    public static class SeleniumWait {

        public static void waitForNoAjaxRequestsPending() {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();

            waitDriver.until((Function<? super WebDriver, Boolean>) d -> ((JavascriptExecutor) SeleniumExecutor.getDriver()).executeScript("return document.readyState").toString().equalsIgnoreCase("complete"));
            waitDriver.until((Function<? super WebDriver, Boolean>) d -> (Boolean) ((JavascriptExecutor) SeleniumExecutor.getDriver()).executeScript("return window.jQuery != undefined && jQuery.active == 0"));
        }

        public static void waitForElementToBeDisplayed(WebElement e) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            try {
                waitDriver.until((Function<? super WebDriver, Boolean>) d -> e.isDisplayed());
            } catch (WebDriverException wde) {
                log.error("waitForElementToBeDisplayed - failed to check if element " + e.toString() + " is displayed");
            }
        }

        public static boolean waitForElementToBeVisible(WebElement e) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            try {
                waitDriver.until((Function<? super WebDriver, Boolean>) d -> e.isDisplayed());
                return true;
            } catch (WebDriverException wde) {
                log.error("waitForElementToBeDisplayed - failed to check if element " + e.toString() + " is displayed");
                return false;
            }
        }

        public static void waitForElementToBeEnabled(WebElement e) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            waitDriver.until((Function<? super WebDriver, Boolean>) d -> e.isEnabled());
        }

        public static void waitForElementToBeClickable(WebElement e) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            waitDriver.until(ExpectedConditions.elementToBeClickable(e));
        }

        public static void waitForElementToBeNotDisplayed(WebElement webElement) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            waitDriver.until((Function<? super WebDriver, Boolean>) d -> webElement.isDisplayed());
            waitDriver.until((Function<? super WebDriver, Boolean>) d -> !isElementPresent(webElement));
        }

        public static void waitForElementToBeNotDisplayed(By by) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            WebElement element = null;

            try {
                waitDriver.until((Function<? super WebDriver, Boolean>) d -> element.findElement(by).isDisplayed());
            } catch (Exception e) {
                waitDriver.until((Function<? super WebDriver, Boolean>) d -> element.findElements(by).size() == 0);
            }
        }

        public static void waitForElementTextToNotContain(WebElement e, String text) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            waitDriver.until((Function<? super WebDriver, Boolean>) d -> !e.getText().toLowerCase().contains(text));
        }

        public static void waitForPageTitleToContain(String title) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            WebDriver driver = SeleniumExecutor.getDriver();

            waitDriver.until((Function<? super WebDriver, Boolean>) d -> driver.getTitle().toLowerCase().contains(title));
        }

        public static void waitForElementsIsDisplayed(List<WebElement> webElements) {
            WebDriverWait wait = SeleniumExecutor.getWaitDriver();
            wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
        }

        public static void waitForElementsIsNotDisplayed(List<WebElement> webElements) {
            WebDriverWait wait = SeleniumExecutor.getWaitDriver();
            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfAllElements(webElements)));
        }

        public static void waitForAnyNotParentWindow() {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            waitDriver.until((Function<? super WebDriver, Boolean>) e -> SeleniumExecutor.getDriver().getWindowHandles().size() > 1);
        }
    }

    public static class SeleniumIsDisplayed {

        public static boolean isElementDisplayedWithCorrectText(WebElement element, String elementText) {
            try {
                waitForElementToBeDisplayed(element);
            } catch (TimeoutException e) {
                e.printStackTrace();
                return false;
            }

            return element.getText().trim().equals(elementText);
        }


        public static boolean isElementDisplayed(By by) {
            WebDriver driver = SeleniumExecutor.getDriver();
            List<WebElement> list = driver.findElements(by);

            if (list.size() == 0) {
                return false;
            } else {
                return list.get(0).isDisplayed();
            }
        }

        public static boolean isElementDisplayed(WebElement element) {

            try {
                waitForElementToBeDisplayed(element);
                return true;
            } catch (TimeoutException e) {
                return false;
            }
        }
    }

    public static class SeleniumIsPresent {

        public static boolean isElementPresentWithWait(By by) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            try {
                waitDriver.until(ExpectedConditions.presenceOfElementLocated(by));
                return true;
            } catch (NoSuchElementException e) {
                return false;
            } catch (TimeoutException e) {
                return false;
            }
        }

        public static boolean isElementPresentWithWait(WebElement webElement) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            try {
                waitDriver.until(ExpectedConditions.elementToBeClickable(webElement));
                return true;
            } catch (NoSuchElementException e) {
                return false;
            } catch (TimeoutException e) {
                return false;
            }
        }

        public static boolean isListOfElementsPresentWithWait(List<WebElement> webElements) {
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            try {
                waitDriver.until(ExpectedConditions.visibilityOfAllElements(webElements));
                return true;
            } catch (NoSuchElementException e) {
                return false;
            } catch (TimeoutException e) {
                return false;
            }
        }

        public static boolean isListOfElementsPresentWithCustomWait(List<WebElement> webElements, int seconds) {
            WebDriverWait waitDriver = new WebDriverWait(SeleniumExecutor.getDriver(), seconds);
            try {
                waitDriver.until(ExpectedConditions.visibilityOfAllElements(webElements));
                return true;
            } catch (NoSuchElementException e) {
                return false;
            } catch (TimeoutException e) {
                return false;
            }
        }

        public static boolean isElementPresentWithCustomWait(WebElement webElement, int seconds) {
            WebDriverWait waitDriver = new WebDriverWait(SeleniumExecutor.getDriver(), seconds);
            try {
                waitDriver.until(ExpectedConditions.elementToBeClickable(webElement));
                return true;
            } catch (NoSuchElementException e) {
                return false;
            } catch (TimeoutException e) {
                return false;
            }
        }

        public static boolean areElementsPresents(WebElement... webElements) {

            for (WebElement webElement : Arrays.asList(webElements)) {
                if (!isElementPresentWithWait(webElement)) {
                    return false;
                }
            }
            return true;
        }

        public static boolean isElementPresent(WebElement webElement) {
            try {
                boolean isPresent = webElement.isDisplayed();
                return isPresent;
            } catch (NoSuchElementException e) {
                return false;
            } catch (StaleElementReferenceException e) {
                return false;
            }
        }
    }

    public static class SeleniumWebElementsList {

        public static boolean isElementOnListWithText(List<WebElement> elements, String word) {
            waitForElementsIsDisplayed(elements);

            try {
                return isAnyElementOnListWhitValueWithText(elements, word);

            } catch (NullPointerException e) {
                return isAnyElementOnListWithText(elements, word);
            }
        }

        public static boolean isElementOnListContainsText(List<WebElement> elements, String word) {
            waitForElementsIsDisplayed(elements);

            try {
                return isAnyElementOnListWithValueContainsText(elements, word);

            } catch (NullPointerException e) {
                return isAnyElementOnListContainsText(elements, word);
            }
        }

        private static boolean isAnyElementOnListWithText(List<WebElement> elements, String word) {
            Optional<WebElement> element = elements.stream()
                    .filter(x -> x.getText().trim().equals(word)).findFirst();

            return element.isPresent();
        }

        private static boolean isAnyElementOnListWhitValueWithText(List<WebElement> elements, String word) {
            Optional<WebElement> element = elements.stream()
                    .filter(x -> x.getAttribute("value").trim().equals(word)).findFirst();

            return element.isPresent();
        }

        private static boolean isAnyElementOnListWithValueContainsText(List<WebElement> elements, String word) {
            Optional<WebElement> element = elements.stream()
                    .filter(x -> x.getAttribute("value").trim().contains(word)).findFirst();

            return element.isPresent();
        }

        private static boolean isAnyElementOnListContainsText(List<WebElement> elements, String word) {
            Optional<WebElement> element = elements.stream()
                    .filter(x -> x.getText().trim().contains(word)).findFirst();

            return element.isPresent();
        }

        public static int getIndexElementWithText(List<WebElement> elements, String searchWord) {
            try {
                return getIndexOfElementWithAttributeValue(elements, searchWord);
            } catch (NullPointerException e) {
                return getIndexOfElementWithGetText(elements, searchWord);
            }
        }

        public static int getIndexElementContainsText(List<WebElement> elements, String searchWord) {
            try {
                return getIndexOfElementContainsAttributeValue(elements, searchWord);
            } catch (NullPointerException e) {
                return getIndexOfElementContainsGetText(elements, searchWord);
            }
        }

        private static int getIndexOfElementWithGetText(List<WebElement> elements, String searchWord) {
            waitForElementsIsDisplayed(elements);

            List<WebElement> foundElementsByText = elements.stream()
                    .filter(x -> x.getText().trim().equals(searchWord))
                    .collect(toList());

            if (foundElementsByText.size() > 1) {
                throw new CollectionSizeException("There is more than one item with the given text");
            } else {
                return elements.indexOf(foundElementsByText.get(0));
            }
        }

        private static int getIndexOfElementWithAttributeValue(List<WebElement> elements, String searchWord) {
            waitForElementsIsDisplayed(elements);

            List<WebElement> foundElementsByAttribute = elements.stream()
                    .filter(x -> x.getAttribute("value").trim().equals(searchWord))
                    .collect(toList());

            if (foundElementsByAttribute.size() > 1) {
                throw new CollectionSizeException("There is more than one item with the given name");
            } else {
                return elements.indexOf(foundElementsByAttribute.get(0));
            }
        }

        private static int getIndexOfElementContainsGetText(List<WebElement> elements, String searchWord) {
            waitForElementsIsDisplayed(elements);

            List<WebElement> foundElementsByText = elements.stream()
                    .filter(x -> x.getText().trim().contains(searchWord))
                    .collect(toList());

            if (foundElementsByText.size() > 1) {
                throw new CollectionSizeException("There is more than one item with the given name");
            } else {
                return elements.indexOf(foundElementsByText.get(0));
            }
        }

        private static int getIndexOfElementContainsAttributeValue(List<WebElement> elements, String searchWord) {
            waitForElementsIsDisplayed(elements);

            List<WebElement> foundElementsByAttribute = elements.stream()
                    .filter(x -> x.getAttribute("value").trim().contains(searchWord))
                    .collect(toList());

            if (foundElementsByAttribute.size() > 1) {
                throw new CollectionSizeException("There is more than one item with the given name");
            } else {
                return elements.indexOf(foundElementsByAttribute.get(0));
            }
        }

        public static List<String> webElementsWithTextToStrings(List<WebElement> elements) {

            return elements.stream()
                    .map(x -> x.getText().trim())
                    .collect(Collectors.toList());
        }

        public static List<String> webElementsWithValueToStrings(List<WebElement> elements) {

            return elements.stream()
                    .map(x -> x.getAttribute("value").trim())
                    .collect(Collectors.toList());
        }
    }

    public static class SeleniumAlertHandle {

        public static void alertHandle(boolean accept) {
            WebDriver driver = SeleniumExecutor.getDriver();
            //This method helps to handle confirm popup window
            if (accept == false) {
                driver.switchTo().alert().dismiss();
            } else if (accept == true) {
                driver.switchTo().alert().accept();
            }
        }

        public static void acceptAlert() {
            if (isAlertPresentWithWait()) {
                SeleniumExecutor.pause(500);

                alertHandle(true);
            }
        }

        public static Alert switchToAlert() {
            WebDriver driver = SeleniumExecutor.getDriver();
            if (isAlertPresentWithWait()) {
                return driver.switchTo().alert();
            }
            return null;
        }

        public static boolean isAlertPresentWithWait() {
            boolean foundAlert;
            WebDriverWait waitDriver = SeleniumExecutor.getWaitDriver();
            try {
                waitDriver.until(ExpectedConditions.alertIsPresent());
                foundAlert = true;
            } catch (TimeoutException e) {
                foundAlert = false;
            }
            return foundAlert;
        }

        public static boolean isTextOnAlert(String expectedText) {

            waitForNoAjaxRequestsPending();

            if (isAlertPresentWithWait()) {
                Alert alert = switchToAlert();
                return alert.getText().equals(expectedText);
            }

            throw new NoAlertPresentException();
        }

        public static boolean alertContainsText(String expectedText) {
            if (isAlertPresentWithWait()) {
                Alert alert = switchToAlert();
                return alert.getText().contains(expectedText);
            }

            throw new NoAlertPresentException();
        }

        public static String getAlertText() {
            String text = null;
            if (isAlertPresentWithWait()) {
                Alert alert = switchToAlert();
                text = alert.getText();
            }
            return text;
        }
    }

    public static class SeleniumKeys {

        public static void highlightText(WebElement element) {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        }

        public static void copyText(WebElement element) {
            element.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        }

        public static void copyTextToClipboard(WebElement webelement) {
            highlightText(webelement);
            copyText(webelement);
        }

    }

    public static class SeleniumFrame {

        public static void sendKeysToFrameAny(String message) {
            sendKeysToFrameNumber(message, 0);
        }

        public static void switchToFrame(WebElement frame) {
            WebDriver driver = SeleniumExecutor.getDriver();
            driver.switchTo().frame(frame);
        }

        public static void switchToDefaultContent() {
            WebDriver driver = SeleniumExecutor.getDriver();
            driver.switchTo().defaultContent();
        }

        public static void sendKeysToFrameNumber(String message, int frameNumber) {
            WebDriver driver = SeleniumExecutor.getDriver();
            driver.switchTo().frame(driver.findElements(By.xpath("//iframe")).get(frameNumber));

            WebElement textInput = driver.findElement(By.id("tinymce"));
            textInput.clear();
            textInput.click();
            textInput.sendKeys(message);

            driver.switchTo().window(driver.getWindowHandle());
        }

        public static List<String> getTextListFromFrame() {
            WebDriver driver = SeleniumExecutor.getDriver();
            List<String> text = new ArrayList<String>();

            List<WebElement> elements = driver.findElements(By.xpath("//iframe"));

            for (WebElement element : elements) {
                try {
                    if (text.isEmpty())// || text.stream () x => string.IsNullOrEmpty(x)))
                    {
                        driver.switchTo().frame(element);
                        List<WebElement> textInputs = driver.findElements(By.tagName("div"));

                        for (WebElement e : textInputs)
                            text.add(e.getText());

                        driver.switchTo().window(driver.getWindowHandle());
                    }
                } catch (Exception e) {
                    driver.switchTo().window(driver.getWindowHandle());
                }
            }

            return text;
        }
    }

    public static class SeleniumSwitchWindow {

        public static WebDriver switchToWindow(String windowHandle) {
            WebDriver driver = SeleniumExecutor.getDriver();
            WebDriver tmp = driver.switchTo().window(windowHandle);

            SeleniumExecutor.pause(500);

            return tmp;
        }

        public static void switchToParentWindow() {
            switchToWindow(SeleniumExecutor.getParentWindowHandle());
        }

        public static boolean switchToAnyNotParentWindow() {
            SeleniumExecutor.setWindowIterator();
            Iterator<String> windowIterator = SeleniumExecutor.getWindowIterator();
            String parentWindowHandle = SeleniumExecutor.getParentWindowHandle();

            while (windowIterator.hasNext()) {
                String windowHandle = windowIterator.next();

                if (!windowHandle.equals(parentWindowHandle)) {
                    switchToWindow(windowHandle);
                    return true;
                }
            }

            switchToParentWindow();
            return false;
        }

        public static boolean switchToWindowWithUrl(String url) {
            if (SeleniumExecutor.getUrl().contains(url)) {
                return true;
            }

            WebDriver popup;
            SeleniumExecutor.setWindowIterator();
            Iterator<String> windowIterator = SeleniumExecutor.getWindowIterator();

            while (windowIterator.hasNext()) {
                String windowHandle = windowIterator.next();

                popup = switchToWindow(windowHandle);
                if (popup.getCurrentUrl().contains(url)) {
                    return true;
                }
            }

            switchToParentWindow();
            return false;
        }

        public static boolean switchToWindowWithUrlAndMakeItParent(String url) {
            if (switchToWindowWithUrl(url)) {
                String windowHandle = SeleniumExecutor.getDriver().getWindowHandle();
                SeleniumExecutor.setParentWindowHandle(windowHandle);
                closeAnyNotParentWindow();
                return true;
            } else
                return false;
        }

        public static boolean switchToWindowWithTitleAndMakeItParent(String title) {
            if (switchToWindowWithTitle(title)) {
                String windowHandle = SeleniumExecutor.getDriver().getWindowHandle();
                SeleniumExecutor.setParentWindowHandle(windowHandle);
                closeAnyNotParentWindow();
                return true;
            } else
                return false;
        }

        public static boolean switchToWindowWithTitle(String title) {
            if (SeleniumExecutor.getTitle().contains(title))
                return true;

            WebDriver popup = null;
            SeleniumExecutor.setWindowIterator();
            Iterator<String> windowIterator = SeleniumExecutor.getWindowIterator();

            while (windowIterator.hasNext()) {
                String windowHandle = windowIterator.next();

                popup = switchToWindow(windowHandle);
                if (popup.getTitle().contains(title)) {
                    return true;
                }
            }

            switchToParentWindow();
            return false;
        }

        public static void switchToNewWindowAndChangeParentWindow() {
            waitForAnyNotParentWindow();
            switchToAnyNotParentWindow();
            String windowHandle = SeleniumExecutor.getDriver().getWindowHandle();
            SeleniumExecutor.setParentWindowHandle(windowHandle);
            closeAnyNotParentWindow();
        }
    }

    public static class SeleniumCloseWindow {

        public static void closeWindow() {
            WebDriver driver = SeleniumExecutor.getDriver();
            driver.close();
            driver.navigate().to("");
        }

        public static void closeAnyNotParentWindow() {
            while (switchToAnyNotParentWindow()) {
                SeleniumExecutor.getDriver().close();
                SeleniumExecutor.pause(500);
            }
            switchToParentWindow();
        }
    }

    public static class SeleniumIsWindow {

        public static boolean isWindowWithTitlePresent(String title) {
            boolean isPresent = false;
            String recentWindow;
            String parentWindowHandle = SeleniumExecutor.getParentWindowHandle();

            try {
                recentWindow = SeleniumExecutor.getDriver().getWindowHandle();
            } catch (NoSuchWindowException e) {
                recentWindow = parentWindowHandle;
            }

            switchToWindow(recentWindow);

            if (SeleniumExecutor.getTitle().contains(title)) {
                isPresent = true;
            } else {
                WebDriver popup = null;
                SeleniumExecutor.setWindowIterator();
                Iterator<String> windowIterator = SeleniumExecutor.getWindowIterator();

                while (windowIterator.hasNext()) {
                    String windowHandle = windowIterator.next();

                    popup = switchToWindow(windowHandle);
                    if (popup.getTitle().contains(title)) {
                        isPresent = true;
                        break;
                    }
                }
            }

            switchToWindow(recentWindow);
            return isPresent;
        }

        public static boolean isWindowWithUrlPresent(String url) {
            boolean isPresent = false;
            String recentWindow;
            String parentWindowHandle = SeleniumExecutor.getParentWindowHandle();

            try {
                recentWindow = SeleniumExecutor.getDriver().getWindowHandle();
            } catch (NoSuchWindowException e) {
                recentWindow = parentWindowHandle;
            }

            switchToWindow(recentWindow);

            if (SeleniumExecutor.getUrl().contains(url)) {
                isPresent = true;
            } else {
                WebDriver popup = null;
                SeleniumExecutor.setWindowIterator();
                Iterator<String> windowIterator = SeleniumExecutor.getWindowIterator();

                while (windowIterator.hasNext()) {
                    String windowHandle = windowIterator.next();

                    popup = switchToWindow(windowHandle);
                    if (popup.getCurrentUrl().contains(url)) {
                        isPresent = true;
                        break;
                    }
                }
            }

            switchToWindow(recentWindow);
            return isPresent;
        }
    }

    public static class SeleniumCheckbox {
        public static void selectCheckbox(WebElement checkBox, boolean selectionDecision) {
            if (selectionDecision) {
                clickUnselectedElement(checkBox);
            } else {
                deselectCheckbox(checkBox);
            }
        }

        public static void selectCheckboxWithWait(WebElement checkBox, boolean selectionDecision) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(checkBox);

            if (selectionDecision) {
                clickUnselectedElement(checkBox);
            } else {
                deselectCheckbox(checkBox);
            }
        }

        private static void clickUnselectedElement(WebElement checkBox) {
            if (!checkBox.isSelected()) {
                checkBox.click();
            }
        }

        public static void deselectCheckbox(WebElement checkbox) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }

        public static boolean getSelectionWithWait(WebElement e) {
            waitForNoAjaxRequestsPending();
            waitForElementToBeDisplayed(e);
            return e.isSelected();
        }
    }

    public static class SeleniumRadioButton {

        public static void selectRadioButton(List<WebElement> radioButtons, String textToSelect) {
            Optional<WebElement> radioButtonByText = findRadioButtonByText(radioButtons, textToSelect);

            if (radioButtonByText.isPresent()) {
                radioButtonByText.get().click();
            } else {
                Optional<WebElement> radioButtonByValue = findRadioButtonByValue(radioButtons, textToSelect);

                if (radioButtonByValue.isPresent()) {
                    radioButtonByValue.get().click();
                }
            }
        }

        private static Optional<WebElement> findRadioButtonByValue(List<WebElement> radioButtons, String textToSelect) {
            return radioButtons.stream()
                    .filter(r -> r.getAttribute("value").toLowerCase().replace(" ", "").equals(textToSelect.toLowerCase().trim().replace(" ", "")))
                    .findFirst();
        }

        private static Optional<WebElement> findRadioButtonByText(List<WebElement> radioButtons, String textToSelect) {
            isListOfElementsPresentWithWait(radioButtons);

            return radioButtons.stream()
                    .filter(r -> r.getText().toLowerCase().trim().replace(" ", "").equals(textToSelect.toLowerCase().trim().replace(" ", "")))
                    .findFirst();
        }
    }

    public static class SeleniumReporter {

        private static SeleniumReporter reporter;
        private Scenario scenario;

        public SeleniumReporter() {
        }

        private SeleniumReporter(Scenario scenario) {
            this.scenario = scenario;
        }

        public static void log(String message) {
            reporter.scenario.write(message);
        }

        private static byte[] getScreenshot() {

            try {
                final Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(SeleniumExecutor.getDriver());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(screenshot.getImage(), "png", baos);
                baos.flush();

                return baos.toByteArray();
            } catch (Exception ex) {
                return null;
            }
        }

        private static void embedScreenshot() {
            try {
                //embed all windows
                for (String handle : SeleniumExecutor.getDriver().getWindowHandles()) {
                    util.helpers.SeleniumExtender.SeleniumSwitchWindow.switchToWindow(handle);
                    reporter.scenario.embed(getScreenshot(), "image/png");
                }
            } catch (Exception e) {
                // ignore
            }
        }

        public static void embedScreenshotIfScenarioFailed() {
            if (reporter.scenario.isFailed())
                embedScreenshot();
        }

        public static void init(Scenario scenario) {
            if (reporter == null)
                reporter = new SeleniumReporter(scenario);
        }

        public static void close() {
            reporter = null;
        }
    }

    public static class SeleniumPerform {

        public static void performMouseover(WebElement we) {
            WebDriver driver = SeleniumExecutor.getDriver();
            Actions action = new Actions(driver);
            action.moveToElement(we).perform();
        }

        public static void performMouseoverSubMenu(WebElement mainMenu, WebElement subMenuOption) {
            WebDriver driver = SeleniumExecutor.getDriver();
            Actions action = new Actions(driver);
            action.moveToElement(mainMenu).perform();
            isElementPresentWithWait(subMenuOption);
            action.moveToElement(subMenuOption).clickAndHold().release().perform();
        }

        public static void performMouseoverCentered(WebElement we) {
            WebDriver driver = SeleniumExecutor.getDriver();
            Actions action = new Actions(driver);
            action.moveToElement(we, we.getSize().getWidth() / 2, we.getSize().getHeight() / 2).perform();
        }

        public static void performClick(WebElement we) {
            WebDriver driver = SeleniumExecutor.getDriver();
            Actions action = new Actions(driver);

            action.moveToElement(we).click().perform();
        }
    }

    public static class SeleniumWebElement {

        public static String getHowFromWebElement(WebElement we) {
            String webElementDesc = we.toString();
            String byDesc = webElementDesc.split("->")[1];
            byDesc = StringUtils.removeEnd(byDesc, "]");
            return byDesc.split(":")[0].trim();
        }

        public static String getUsingFromWebElement(WebElement we) {
            String webElementDesc = we.toString();
            String byDesc = webElementDesc.split("->")[1];
            byDesc = StringUtils.removeEnd(byDesc, "]");
            return byDesc.split(":")[1].trim();
        }

        public static void showHiddenWebElement(WebElement we) {
            if (!we.isDisplayed()) {
                String using = getUsingFromWebElement(we);
                JavascriptExecutor js = (JavascriptExecutor) SeleniumExecutor.getDriver();
                js.executeScript("$('#" + using + "').css('display','block')");
            }
        }
    }
}

