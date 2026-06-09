@smoke @login
Feature: OrangeHRM Login
  As a user
  I want to login and logout of OrangeHRM
  So that I can access the application securely

  Scenario: TC01_Verify valid login and logout
    Given I am on the OrangeHRM login page
    When I login with valid credentials
    Then I should land on the dashboard
    When I logout from the application

  Scenario Outline: TC02_Verify login with different credentials
    Given I am on the OrangeHRM login page
    When I login with username "<username>" and password "<password>"
    Then I should land on the dashboard

    Examples:
      | username | password  |
      | Admin    | admin123  |
