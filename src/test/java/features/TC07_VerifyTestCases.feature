@Regression @TC7
Feature: Test Case 7 - Verify Test Cases Page

  Scenario Outline: Navigate to Test Cases Page
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    Then I verify that test cases heading is displayed
    And I close the browser

    Examples:
      | header     |
      | Test Cases |
