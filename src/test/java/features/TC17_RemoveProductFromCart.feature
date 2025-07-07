@Regression @TC17
Feature: Test Case 17 - Remove Products From Cart

  Scenario Outline: Add Products to Cart
    Given I launched website
    And I verify that home page is visible
    When I hover over a product <firstProduct>
    And I click add to cart on <firstProduct> overlay
    And I click continue shopping button
    And I hover over a product <secondProduct>
    And I click add to cart on <secondProduct> overlay
    Then I click continue shopping button

    Examples: 
      | firstProduct | secondProduct |
      | Blue Top     | Men Tshirt    |

  Scenario Outline: Remove Product
    Given I landed on <header> page
    And I verify that cart page is displayed
    When I click the X button of <product>
    Then I verify that <product> is removed from the cart
    And I close the browser

    Examples: 
      | header | product  |
      | Cart   | Blue Top |
