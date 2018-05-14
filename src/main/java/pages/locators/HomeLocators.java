package pages.locators;


import org.openqa.selenium.By;


public class HomeLocators {

    public By taskLink(String taskNumber) {
        if (taskNumber.equals("6")) {
            return By.xpath(String.format("//a[@href='/task_%s/logged']", taskNumber));
        }
        return By.xpath(String.format("//a[@href='/task_%s']", taskNumber));
    }
}
