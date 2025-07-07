@Regression @TC24
Feature: Test Case 24 - Download Invoice after purchase order

  Scenario Outline: Go to cart page and checkout
    Given I launched website
    And I verify that home page is visible
    And I hover over a product <firstProduct>
    And I click add to cart on <firstProduct> overlay
    And I click continue shopping button
    And I hover over a product <secondProduct>
    And I click add to cart on <secondProduct> overlay
    And I click continue shopping button
    And I landed on <header> page
    And I verify that products are added to cart
    When I click proceed to checkout
    And I click register or login
    Then I verify that new user sign up is visible

    Examples: 
      | header | firstProduct | secondProduct |
      | Cart   | Blue Top     | Men Tshirt    |

  Scenario Outline: Create new user account
    Given I sign up with <name> and <email>
    And I verify that enter account information is visible
    And I fill account info with <title>, <name>, <password>, <birthDate>, <news>, <offers>
    And I fill address info with <fName>, <lName>, <company>, <addressLine>, <mobile>
    And I submit the sign up form
    Then I verify that a new user account is created
    And I verify that logged in as <name> is visible

    #Note: addressLine format (values separated by comma with no space) -> address1,address2,country,state,city,zipcode
    Examples: 
      | name  | email | title | password     | birthDate  | news | offers | fName   | lName | company   | mobile           | addressLine                                             |
      | Jhead | auto  | Mr    | Password123! | 01-04-2000 | true | true   | Jughead | Jones | Riverdale | +63 123 456 7890 | Street 1,Village 1,United States,Illinois,Chicago,12345 |

  Scenario Outline: Proceed to checkout and place order
    Given I landed on <header> page
    And I verify that products are added to cart
    When I click proceed to checkout
    And I verify address details
    And I verify order details
    And I add a <comment> to the order
    And I click place order
    Then I verify that payment page is displayed

    Examples: 
      | header | comment           |
      | Cart   | Order comment 123 |

  Scenario Outline: Download Invoice after making payment
    Given I enter payment details with <nameOnCard>, <cardNumber>, <cvc>, <expMonth>, <expYear>
    When I click pay and confirm order
    And I verify that successful order message is displayed
		And I click on download invoice
		Then I verify that invoice is downloaded successfully
		
    Examples: 
      | nameOnCard  | cardNumber | cvc | expMonth | expYear |
      | sample_mvnc |  123456789 | 123 |       06 |    2050 |

  Scenario: Delete account
    Given I delete the account
    Then I verify that account deleted message is visible
    And I close the browser
