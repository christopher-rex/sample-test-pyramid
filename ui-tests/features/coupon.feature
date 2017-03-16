Feature: Coupon validation and usage

  Background:
    Given I am on login page

  @e2e
  Scenario: Verify that valid coupon is successfully validated
    Given I login with username "sahil@mail.com" and password "sahil"
    And I am on coupon page
    When I validate coupon-code "ABC123"
    Then I see the notification "Coupon ABC123 is available."

  @e2e
  Scenario: Verify validation error for invalid coupon
    Given I login with username "sahil@mail.com" and password "sahil"
    When I validate coupon-code "ABCXXX"
    Then I see the notification "No coupon found."

  @wip @stub
  Scenario: Verify validation message for missing coupon-code
    Given I login with username "sahil@mail.com" and password "sahil"
    When I validate coupon-code ""
    Then I see the notification "Please fill out this field"

  @wip @stub
  Scenario: Verify that the coupon page is NOT accessible without login
    Given I am not logged in
    When I navigate to coupon page using url "http://localhost:8000/#coupon"
    Then I am redirected to login page

  @wip @stub
  Scenario: Verify auto-truncation of coupon codes greater than 6 characters

  @wip
  Scenario: Verify that expired coupon returns validation error

  @wip @e2e
  Scenario: Verify successful redemption of a coupon