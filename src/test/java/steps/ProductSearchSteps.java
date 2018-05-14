package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.executors.ProductSearchPage;

public class ProductSearchSteps {

    private ProductSearchPage productSearch;

    public ProductSearchSteps() {
        productSearch = new ProductSearchPage();
    }

    @When("^I expand dropdown list of categories$")
    public void iExpandDropdownListOfCategories() throws Throwable {
        productSearch.expandListOfCategories();
    }

    @And("^I select my category \"([^\"]*)\"$")
    public void iSelectMyCategory(String category) throws Throwable, Exception {
        productSearch.pickCategory(category);
    }

    @Then("^I can see all products for this category \"([^\"]*)\"$")
    public void iCanSeeAllProductsInThis(String category) throws Throwable {
        Assert.assertTrue("At least one product does not match this category", productSearch.verifyProductListOfSelectedCategory(category));
    }

    @And("^I type some characters \"([^\"]*)\" to search box$")
    public void iTypeSomeCharactersToSearchBox(String characters) throws Throwable {
        productSearch.typeCharactersToSearchInput(characters);
    }

    @Then("^I can see category \"([^\"]*)\" matched for this characters in dropdown$")
    public void iCanSeeCategoryMatchedForThisCharactersInDropdown(String category) throws Throwable {
        Assert.assertTrue("Searched category is wrong", productSearch.verifySearchedCategory(category));
    }
}
