@Task1

Feature: Add and remove products from basket

  As a user
  I want to be able to add and remove products from basket

  Background:
    Given I'm on page with task number "1"

  Scenario: User is able to add products to basket
    When I add product "Okulary" with quantity of "20"
    And I add product "Kamera" with quantity of "5"
    Then I can see total quantity of products in basket
    And I can see total price to pay in basket

  Scenario: User is able to remove products from basket
    When I add product "Kubek" with quantity of "10"
    And I add product "Aparat" with quantity of "11"
    Then I can see "2" products in basket
    And I remove product "Kubek" from basket
    And I can see "1" products in basket

  Scenario: User tries to add over 100 products to basket
    When I add product "Okulary" with quantity of "70"
    And I add product "Kamera" with quantity of "31"
    Then I can see alert with message "Łączna ilość produktów w koszyku nie może przekroczyć 100."