@Regression @TC10
Feature: Test Case 10 - Verify Subscription in Home Page

  Scenario Outline: Subscribe to the website in home page
    Given I launched website
    And I verify that home page is visible
    And I scroll to the bottom of the page
    And I verify that subscription heading is displayed
    When I subscribe to the website with <email>
    Then I verify that successful subscription message is displayed
    And I close the browser

  	Examples: 
      | email                 |
      | sample_mvnc@email.com |
