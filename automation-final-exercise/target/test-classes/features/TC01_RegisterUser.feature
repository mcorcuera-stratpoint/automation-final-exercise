@Regression
Feature: Test Case 1 - Register User

  Scenario Outline: Create new user account
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I verify that new user sign up is visible
    When I sign up with <name> and <email>
    And I verify that enter account information is visible
    And I fill account info with <title>, <name>, <password>, <birthDate>, <news>, <offers>
    And I fill address info with <fName>, <lName>, <company>, <addressLine>, <mobile>
    And I submit the sign up form
    Then I verify that a new user account is created

    #Note: addressLine format (values separated by comma with no space) -> address1,address2,country,state,city,zipcode
    Examples: 
      | header       | name | email | title | password     | birthDate  | news | offers | fName | lName    | company | mobile           | addressLine                                             |
      | Signup/Login | mvnc | auto  | Mr    | Password123! | 01-04-2000 | true | true   | mvnc  | exercise | sPoint  | +63 123 456 7890 | Street 1,Village 1,United States,Illinois,Chicago,12345 |

  Scenario Outline: Delete account
    Given I landed on <header> page
    And I verify that logged in as <name> is visible
    When I delete the account
    Then I verify that account deleted message is visible
    And I close the browser

    Examples: 
      | header | name |
      | Home   | mvnc |
