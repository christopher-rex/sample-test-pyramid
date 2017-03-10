Feature: User login authentication

  Scenario: Verify successful login
    Given I am on login page
    When I login with username "sahil@mail.com" and password "sahil"
    Then I see the notification "Logged in successfully"

  Scenario: Verify invalid login
    Given I am on login page
    When I login with username "chris@mail.com" and password "chris"
    Then I see the error notification "You are not authorized"

  Scenario: Verify validation message for missing email
    Given I am on login page
    When I login with username "" and password "chris"
    Then I see text "Please fill out this field"

  Scenario: Verify validation message for missing password
    Given I am on login page
    When I login with username "chris@mail.com" and password ""
    Then I see text "Please fill out this field"

  Scenario: Verify validation message for invalid email
    Given I am on login page
    When I login with username "chris" and password "chris"
    Then I see text "Please enter an email address"
