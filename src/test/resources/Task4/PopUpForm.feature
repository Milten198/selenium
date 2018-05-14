@Task4

Feature: Popup with form in separate window

  As a user
  I want to fill form in separate window
  and save it

  Background:
    Given I'm on page with task number "4"

  Scenario: User is able to fill form and save
    When I click on Apply button
    And Form in window is open
    And I fill the Name field with "Jan Kowalski"
    And I fill the Email field with "jkowaslki@wp.pl"
    And I fill the Phone number field with "600-100-200"
    And I submit Save button
    Then Confirmation message is displayed

  Scenario: User tries to save form with wrong email and phone number
    When I click on Apply button
    And Form in window is open
    And I fill the Name field with "Jan Kowalski"
    And I fill the Email field with "wrong email"
    And I fill the Phone number field with "600 100 200"
    And I submit Save button
    Then I can see error message for email
    And I can see error message for phone number





