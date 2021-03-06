@Task7

Feature: Drag and drop operations

  As a user
  I want to drag product and drop in basket

  Background:
    Given I'm on page with task number "7"

  Scenario Outline: User is able to drag product and drop it into the basket
    When I set quantity "<quantity>" of product "<product>"
    And I drag and drop a "<product>" into the basket
    Then I can see total "<quantity>" of product in basket
    And I can see total "<price>" to pay in basket

    Examples:
      | quantity | product  | price      |
      | 20       | Okulary  | 310.80 zł  |
      | 35       | Monitor  | 1880.20 zł |
      | 60       | Poduszka | 3223.20 zł |

