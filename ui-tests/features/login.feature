Feature: User login authentication

  Scenario: Verify successful login
    Given I am on login page
    When I login with username "c@g.com" and password "chris"
    Then I see the message "Logged in successfully."