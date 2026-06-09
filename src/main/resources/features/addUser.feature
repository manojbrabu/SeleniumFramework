@login
@regression
Feature: Login

  Scenario: Login Scenario
    Given User is on Login Page
    When I login with valid credentials
    Then Dashboard page should be displayed
    When I logout from the application

  Scenario: Login Scenario
    Given User is on Login Page
    When I login with valid credentials
    Then Dashboard page should be displayed
    When I logout from the application



