@Task6

Feature: Login and download file

  As a user
  I want to login and download file

  Background:
    Given I'm on page with task number "6"

  Scenario: User is able to login and download file
    When I fill the Login field with "tester"
    And I fill the Password field with "123-xyz"
    And I submit Login button
    And I clean download directory
    And I click download link
    Then File is downloaded

  Scenario: User is unable to login with incorrect data
    When I fill the Login field with "wrong login"
    And I fill the Password field with "wrong password"
    And I submit Login button
    Then I can see wrong login data error message
    And I am on Login page

  Scenario: User is able to logout
    When I fill the Login field with "tester"
    And I fill the Password field with "123-xyz"
    And I submit Login button
    And I am on Logged page
    Then I can click on logout link
    And I am on Login page
