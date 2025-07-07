@Regression @TC16
Feature: Test Case 16 - Place Order: Login before Checkout

  Scenario Outline: Precondition - Create new user account
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I verify that new user sign up is visible
    When I sign up with <name> and <email>
    And I verify that enter account information is visible
    And I fill account info with <title>, <name>, <password>, <birthDate>, <news>, <offers>
    And I fill address info with <fName>, <lName>, <company>, <addressLine>, <mobile>
    Then I submit the sign up form
    And I verify that a new user account is created

    #Note: addressLine format (values separated by comma with no space) -> address1,address2,country,state,city,zipcode
    Examples: 
      | header       | name | email | title | password     | birthDate  | news | offers | fName | lName    | company | mobile           | addressLine                                             |
      | Signup/Login | mvnc | auto  | Mr    | Password123! | 01-04-2000 | true | true   | mvnc  | exercise | sPoint  | +63 123 456 7890 | Street 1,Village 1,United States,Illinois,Chicago,12345 |

  Scenario Outline: Precondition - Logout from newly created account
    Given I landed on <header> page
    And I logout from the account

    Examples: 
      | header |
      | Home   |

  Scenario Outline: Login using correct email and password
    Given I landed on <header> page
    And I verify that login to your account is visible
    When I login with <email> and <password>
    Then I verify that logged in as <user> is visible

    Examples: 
      | header       | email | password     | user |
      | Signup/Login | auto  | Password123! | mvnc |
      
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

  Scenario Outline: Proceed to checkout and place order
    Given I click proceed to checkout
    And I verify address details
    And I verify order details
    And I add a <comment> to the order
    When I click place order
    Then I verify that payment page is displayed

    Examples:
      | comment           |
      | Order comment 123 |

  Scenario Outline: Enter Payment Details
    Given I enter payment details with <nameOnCard>, <cardNumber>, <cvc>, <expMonth>, <expYear>
    And I click pay and confirm order
    Then I verify that successful order message is displayed

    Examples: 
      | nameOnCard  | cardNumber | cvc | expMonth | expYear |
      | sample_mvnc |  123456789 | 123 |       06 |    2050 |

  Scenario: Delete account
    Given I delete the account
    Then I verify that account deleted message is visible
    And I close the browser
