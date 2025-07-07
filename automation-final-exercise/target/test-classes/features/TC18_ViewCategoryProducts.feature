@Regression
Feature: Test Case 18 - View Category Products

  Scenario Outline: Go to any product sub-category under Women
    Given I launched website
    And I verify that home page is visible
    And I verify that categories are visible
    When I navigate to panel <navigation>
    Then I verify that page and products are displayed for <navigation>

    Examples: 
      | navigation           |
      | Category>Women>Dress |

  Scenario Outline: Go to any product sub-category under Men
    Given I navigate to panel <navigation>
    Then I verify that page and products are displayed for <navigation>
    And I close the browser

    Examples: 
      | navigation           |
      | Category>Men>Tshirts |
