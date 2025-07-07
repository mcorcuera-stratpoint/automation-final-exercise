@Regression @TC20
Feature: Test Case 20 - Search Products and Verify Cart After Login

  Scenario Outline: Search Product
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I verify that all products page is visible
    When I search for a product <productName>
    Then I verify that related products to <productName> are visible

    Examples: 
      | header   | productName |
      | Products | Sleeves     |

  Scenario Outline: Add Products to Cart
    Given I add all related products to cart
    When I landed on <header> page
    Then I verify that products are added to cart

    Examples: 
      | header |
      | Cart   |

  Scenario Outline: Login using correct email and password
    Given I landed on <header> page
    And I verify that login to your account is visible
    When I login with <email> and <password>
    Then I verify that logged in as <user> is visible

    Examples: 
      | header       | email                 | password | user |
      | Signup/Login | sample_mvnc@email.com |      123 | mvnc |

  Scenario Outline: View Cart After Login
    Given I landed on <header> page
    Then I verify that products are added to cart
    And I verify that cart details are correct
    And I empty the cart
    And I close the browser

    Examples: 
      | header |
      | Cart   |
