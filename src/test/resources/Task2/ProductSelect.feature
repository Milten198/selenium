@Task2

Feature: Select products by category

  Background:
    Given I'm on page with task number "2"

  Scenario Outline: User is able to select products by category
    When I expand dropdown list of categories
    And I select my category "<category>"
    Then I can see all products for this category "<category>"

    Examples:
      | category       |
      | Sport          |
      | Elektronika    |
      | Firma i usługi |

  Scenario Outline: User is able to search for his category
    When I expand dropdown list of categories
    And I type some characters "<characters>" to search box
    Then I can see category "<category>" matched for this characters in dropdown

    Examples:
      | characters | category       |
      | el         | Elektronika    |
      | ort        | Sport          |
      | a i usł    | Firma i usługi |