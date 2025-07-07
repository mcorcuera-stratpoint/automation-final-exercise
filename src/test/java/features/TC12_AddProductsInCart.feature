@Regression @TC12
Feature: Test Case 12 - Add Products in Cart

  Scenario Outline: Add Products in Cart
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    When I hover over a product <firstProduct>
    And I click add to cart on <firstProduct> overlay
    Then I click continue shopping button
    And I hover over a product <secondProduct>
    And I click add to cart on <secondProduct> overlay

    Examples: 
      | header   | firstProduct | secondProduct |
      | Products | Blue Top     | Men Tshirt    |

  Scenario: View Cart
    Given I click view cart
    Then I verify that products are added to cart
    And I verify that cart details are correct
    And I close the browser
