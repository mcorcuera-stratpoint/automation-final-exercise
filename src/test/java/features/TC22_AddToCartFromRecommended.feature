@Regression @TC22
Feature: Test Case 22 - Add to cart from Recommended items

  Scenario Outline: Add recommended item to cart
    Given I launched website
    And I verify that home page is visible
    And I verify that recommended items are visible
    When I add to cart a recommended item <product>
    And I click view cart
    Then I verify that products are added to cart
    And I close the browser

    Examples:
      | product  |
      | Blue Top |
