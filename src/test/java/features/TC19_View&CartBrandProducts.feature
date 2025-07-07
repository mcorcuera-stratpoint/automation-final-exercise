@Regression @TC19
Feature: Test Case 19 - View & Cart Brands Products

  Scenario Outline: Go to any brands on product page
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I verify that brands are visible
    When I navigate to panel <navigation>
    Then I verify that page and products are displayed for <navigation>

    Examples: 
      | header   | navigation     |
      | Products | Brands>Babyhug |

  Scenario Outline: Go to any brands on current brand page
    Given I navigate to panel <navigation>
    Then I verify that page and products are displayed for <navigation>
    And I close the browser

    Examples: 
      | navigation                |
      | Brands>Allen Solly Junior |
