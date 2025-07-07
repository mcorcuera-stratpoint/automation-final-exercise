@Regression
Feature: Test Case 3 - Login User with Incorrect Email and Password

  Scenario Outline: Login using incorrect email and password
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I verify that login to your account is visible
    When I login with <email> and <password>
    Then I verify that incorrect credentials message is visible
    And I close the browser

    Examples: 
      | header       | email                    | password |
      | Signup/Login | mvnc_incorrect@email.com |      123 |