@Regression @TC23
Feature: Test Case 23 - Place Order: Register before Checkout

  Scenario Outline: Create new user account
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    When I sign up with <name> and <email>
    And I verify that enter account information is visible
    And I fill account info with <title>, <name>, <password>, <birthDate>, <news>, <offers>
    And I fill address info with <fName>, <lName>, <company>, <addressLine>, <mobile>
    And I submit the sign up form
    Then I verify that a new user account is created
    And I verify that logged in as <name> is visible

    #Note: addressLine format (values separated by comma with no space) -> address1,address2,country,state,city,zipcode
    Examples: 
      | header       | name  | email | title | password     | birthDate  | news | offers | fName   | lName | company   | mobile           | addressLine                                             |
      | Signup/Login | Jhead | auto  | Mr    | Password123! | 01-04-2000 | true | true   | Jughead | Jones | Riverdale | +63 123 456 7890 | Street 1,Village 1,United States,Illinois,Chicago,12345 |

  Scenario Outline: Add products to cart
    Given I hover over a product <firstProduct>
    And I click add to cart on <firstProduct> overlay
    And I click continue shopping button
    And I hover over a product <secondProduct>
    And I click add to cart on <secondProduct> overlay
    And I click continue shopping button
    When I landed on <header> page
    Then I verify that products are added to cart
    
    Examples: 
      | header | firstProduct | secondProduct |
      | Cart   | Blue Top     | Men Tshirt    |

  Scenario: Proceed to checkout and verify address details
    Given I click proceed to checkout
    Then I verify address details

  Scenario: Delete account
    Given I delete the account
    Then I verify that account deleted message is visible
    And I close the browser
