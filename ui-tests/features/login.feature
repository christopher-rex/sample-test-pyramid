Feature: User login authentication

  Background:
    Given I am on login page

  @e2e
  Scenario: Verify successful login
    When I login with username "sahil@mail.com" and password "sahil"
    Then I see the notification "Logged in successfully"
    And I am on coupon page

  @e2e
  Scenario: Verify invalid login
    When I login with username "chris@mail.com" and password "chris"
    Then I see the error notification "You are not authorized"

  @stub
  Scenario: Verify validation message for missing email
    When I login with username "" and password "chris"
    Then I see text "Please fill out this field"

  @stub
  Scenario: Verify validation message for missing password
    When I login with username "chris@mail.com" and password ""
    Then I see text "Please fill out this field"

  @stub
  Scenario: Verify validation message for invalid email
    When I login with username "chris" and password "chris"
    Then I see text "Please enter an email address"

  @stub
  Scenario: Verify successful login with email verification prompt for user with unverified email
    When I login with username "tom@mail.com" and password "tom"
    Then I see the notification "Please confirm your email"
