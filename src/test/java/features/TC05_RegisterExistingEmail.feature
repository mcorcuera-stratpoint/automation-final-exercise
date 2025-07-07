@Regression @TC5
Feature: Test Case 5 - Register User with existing email

  Scenario Outline: Create new account using an existing email
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I verify that new user sign up is visible
    When I sign up with <name> and <email>
    Then I verify that existing email address message is visible
    And I close the browser

   Examples: 
      | header       | name        | email                 |
      | Signup/Login | sample_mvnc | sample_mvnc@email.com |
