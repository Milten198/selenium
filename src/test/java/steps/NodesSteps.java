package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.executors.NodesPage;

public class NodesSteps {

    private NodesPage nodes;

    public NodesSteps() {
        nodes = new NodesPage();
    }

    @When("^I click root node name$")
    public void iClickRootNodeName() throws Throwable {
        nodes.clickOnRootNodeFolder();
    }

    @Then("^I can see article with header \"([^\"]*)\"$")
    public void iCanSeeArticleWithHeader(String header) throws Throwable {
        Assert.assertEquals("Wrong header", header, nodes.getHeaderText());
    }

    @When("^I click root node arrow$")
    public void iClickRootNodeArrow() throws Throwable {
        nodes.clickRootNodeArrow();
    }

    @When("^I click right button on root node name$")
    public void iClickRightButtonOnRootNodeName() throws Throwable {
        nodes.rightClickOnRootNodeName();
    }

    @And("^I click on change name button$")
    public void iClickOnChangeNameButton() throws Throwable {
        nodes.clickChangeButton();
    }

    @And("^I type new name \"([^\"]*)\"$")
    public void iTypeNewName(String newName) throws Throwable {
        nodes.setNewRootName(newName);
    }

    @Then("^I can see root node name \"([^\"]*)\"$")
    public void iCanSeeRootNodeName(String rootName) throws Throwable {
        Assert.assertEquals("Wrong root name", rootName, nodes.verifyRootNodeNameIsDisplay());
    }

    @Then("^I can see \"([^\"]*)\" child node name \"([^\"]*)\"$")
    public void iCanSeeChildNodeName(String childNumber, String childName) throws Throwable {
        Assert.assertEquals("Wrong child name", childName, nodes.verifyChildNameIsDisplay(childNumber));
    }

    @When("^I double click root node name$")
    public void iDoubleClickRootNodeName() throws Throwable {
        nodes.doubleClickOnRootNodeFolder();
    }
}
