@Regression
Feature: Test Case 26 - Verify Scroll Up Without Using Arrow and Scroll Down Functionality

  Scenario: Scroll Down and Scroll Up in Home Page
    Given I launched website
    And I verify that home page is visible
    And I scroll to the bottom of the page
    And I verify that subscription heading is displayed
    When I scroll to the top of the page
    Then I verify that full-fledged text is displayed
    And I close the browser