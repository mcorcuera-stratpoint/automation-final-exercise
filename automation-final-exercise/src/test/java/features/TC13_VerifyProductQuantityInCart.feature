@Regression
Feature: Test Case 13 - Verify Product Quantity In Cart

  Scenario Outline: View Product
    Given I launched website
    And I verify that home page is visible
    And I click on view product of the <ordinal> product
    And I verify that viewed product information is visible
    And I increase product quantity to <amount>
    And I click add to cart via product detail
    When I click view cart
    Then I verify that cart details are correct
    And I close the browser

    #Note: Ordinal values are in string (First, Second, etc.)
    Examples: 
      | ordinal | amount |
      | First   |      4 |
