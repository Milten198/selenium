package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.executors.LoggedPage;
import pages.executors.LoginPage;
import util.helpers.FileHelper;


public class LoginAndDownloadSteps {

    private LoginPage loginPage;
    private LoggedPage loggedPage;

    public LoginAndDownloadSteps() {
        this.loginPage = new LoginPage();
        this.loggedPage = new LoggedPage();
    }

    @When("^I fill the Login field with \"([^\"]*)\"$")
    public void iFillTheLoginFieldWith(String login) throws Throwable {
        loginPage.setLogin(login);
    }

    @And("^I fill the Password field with \"([^\"]*)\"$")
    public void iFillThePasswordFieldWith(String password) throws Throwable {
        loginPage.setPassword(password);
    }

    @And("^I submit Login button$")
    public void iSubmitLoginButton() throws Throwable {
        loginPage.clickLoginBtn();
    }

    @Then("^I can see file download link$")
    public void iCanSeeFileDownloadLinkOnLoggedPage() throws Throwable {
        loggedPage.downloadLinkIsVisible();
    }

    @And("^I am on Logged page$")
    public void iAmOnLoggedPage() throws Throwable {
        Assert.assertTrue("Logged page is not displayed", loggedPage.checkLoggedPageIsVisible());
    }

    @Then("^I can see wrong login data error message$")
    public void iCanSeeWrongLoginDataErrorMessage() throws Throwable {
        Assert.assertTrue("Login error message is not displayed", loginPage.checkLoginErrorMessage());
    }

    @And("^I am on Login page$")
    public void iAmOnLoginPage() throws Throwable {
        Assert.assertTrue("Login page is not displayed", loginPage.checkLoginPageIsVisible());
    }

    @Then("^I can click on logout link$")
    public void iCanClickOnLogoutLink() throws Throwable {
        loggedPage.clickLogoutLink();
    }

    @And("^I click download link$")
    public void iClickDownloadLink() throws Throwable, Exception {
        loggedPage.clickDownloadLink();
    }

    @Then("^File is downloaded$")
    public void fileIsDownloaded() throws Throwable {
        Assert.assertTrue("There is no downloaded file", FileHelper.isAnyFileDownloaded());
        Assert.assertTrue("File was not downloaded or wrong file downloaded", loggedPage.checkIfFileWasDownloaded());
    }

    @And("^I clean download directory$")
    public void iCleanDownloadDirectory() throws Throwable {
        loggedPage.cleanDownloadFolder();
    }
}
