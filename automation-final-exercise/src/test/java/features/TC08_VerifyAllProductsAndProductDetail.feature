@Regression
Feature: Test Case 8 - Verify All Products and Product Detail Page

  Scenario Outline: View Product
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I verify that all products page is visible
    And I verify that the products list is visible
    When I click on view product of the <ordinal> product
    Then I verify that viewed product information is visible
    And I close the browser
		
    #Note: Ordinal values are in string (First, Second, etc.)
    Examples: 
      | header   | ordinal |
      | Products | First   |
