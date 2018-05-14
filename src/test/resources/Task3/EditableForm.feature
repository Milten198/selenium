@Task3

Feature: Unlock and edit form

  As a User
  I want to be able to edit form

  Background:
    Given I'm on page with task number "3"

  Scenario: User is able to edit and save form
    When I enable editable mode
    And I fill all the fields with my data
    And I upload my photo
    And I click save button
    Then Confirmation message "Twoje dane zostały poprawnie zapisane" appears
    And All data are correct

  Scenario: Form is not editable
    When I try to modify data in form with no editable mode
    Then Edition is not possible