package pages.executors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.locators.UploadFileLocators;
import util.SeleniumExecutor;
import util.helpers.SeleniumExtender;
import util.objects.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UploadFilePage {

    private UploadFileLocators locators;

    public UploadFilePage() {
        locators = new UploadFileLocators();
        PageFactory.initElements(SeleniumExecutor.getDriver(), locators);
    }

    public void uploadFile(String fileToUpload) {
        SeleniumExtender.SeleniumSendKeys.sendKeysWithoutWait(locators.uploadBtn, System.getProperty("user.dir") + "\\src\\test\\resources\\Task5\\" + fileToUpload);
    }

    public boolean compareTwoLists() {
        SeleniumExtender.SeleniumWait.waitForElementToBeDisplayed(SeleniumExecutor.getDriver().findElement(By.xpath("//tbody")));
        List<Person> file = createListOfPeopleFromFile();
        List<Person> table = createListOfPeopleFromTable();

        boolean areListsEqual = true;
        if (table.size() > 0) {
            for (int i = 0; i < file.size(); i++) {
                if (!file.get(i).getName().equals(table.get(i).getName())
                        || !file.get(i).getSurname().equals(table.get(i).getSurname())
                        || !file.get(i).getPhoneNumber().equals(table.get(i).getPhoneNumber()))
                    areListsEqual = false;
            }
        } else {
            areListsEqual = false;
        }
        return areListsEqual;
    }

    private List<Person> createListOfPeopleFromTable() {
        List<Person> peopleWeb = new ArrayList<>();
        List<WebElement> rows = SeleniumExecutor.getDriver().findElements(By.xpath("//tbody/tr"));
        for (int i = 1; i <= rows.size(); i++) {
            List<WebElement> record = SeleniumExecutor.getDriver().findElements(By.xpath(String.format("//tr[%s]/td", i)));
            peopleWeb.add(new Person(record.get(0).getText(), record.get(1).getText(), record.get(2).getText()));
        }
        return peopleWeb;
    }

    private List<Person> createListOfPeopleFromFile() {
        List<Person> peopleFile = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Task5\\" + "correct.txt"));
            while (reader.hasNext()) {
                String[] lineData = reader.nextLine().replaceAll("\\s", "").split(",");
                peopleFile.add(new Person(lineData[0], lineData[1], lineData[2]));
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return peopleFile;
    }
}
