@Regression @TC9
Feature: Test Case 9 - Search Product

  Scenario Outline: Search Product
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I verify that all products page is visible
    When I search for a product <productName>
    Then I verify that related products to <productName> are visible
    And I close the browser

    Examples: 
      | header   | productName |
      | Products | Sleeves     |
