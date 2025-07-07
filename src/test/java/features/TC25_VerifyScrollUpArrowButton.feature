@Regression
Feature: Test Case 25 - Verify Scroll Up Using Arrow and Scroll Down Functionality

  Scenario: Click Scroll Up Arrow in Home Page
    Given I launched website
    And I verify that home page is visible
    And I scroll to the bottom of the page
    And I verify that subscription heading is displayed
    When I click the scroll up arrow
    Then I verify that full-fledged text is displayed
    And I close the browser