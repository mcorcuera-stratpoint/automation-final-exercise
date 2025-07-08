@Regression @TC4
Feature: Test Case 4 - Logout User

  Scenario Outline: Login using correct email and password
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I verify that login to your account is visible
    And I login with <email> and <password>
    And I verify that logged in as <user> is visible
    When I logout from the account
    Then I verify that login to your account is visible
    And I close the browser

    Examples:
      | header       | email                 | password | user        |
      | Signup/Login | sample_mvnc@email.com | 123      | sample_mvnc |
