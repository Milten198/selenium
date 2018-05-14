@Task5

Feature: Upload file from local directory with user data

  Background:
    Given I'm on page with task number "5"

  Scenario: User is able to upload file with correct data
    When I upload file "correct.txt"
    Then Data from file are shown in table

  Scenario: User is able to see alert message when uploading file with to many rows
    When I upload file "toManyRows.txt"
    Then I can see alert with message "Maksymalnie wprowadzić można 20 wierszy."

  Scenario: User is able to see alert message when uploading file with wrong phone number
    When I upload file "wrongPhoneNumber.txt"
    Then I can see alert with message "Numer telefonu może zawierać tylko znaki numeryczne, musi mieć 9 znaków."

  Scenario: User is able to see alert message when file is incorrectly formatted
    When I upload file "wrongDataFormat.txt"
    Then I can see alert with message "Źle sformatowany plik."

  Scenario: User is able to see alert message when file has wrong format
    When I upload file "wrongFileFormat.png"
    Then I can see alert with message "Zły format pliku"

  Scenario: User is able to see alert message when file is empty
    When I upload file "empty.txt"
    Then I can see alert with message "Plik jest pusty, lub źle sformatowany"



    



