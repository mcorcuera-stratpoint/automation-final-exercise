@Regression
Feature: Test Case 11 - Verify Subscription in Cart Page

  Scenario Outline: Subscribe to the website in cart page
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I scroll to the bottom of the page
    And I verify that subscription heading is displayed
    When I subscribe to the website with <email>
    Then I verify that successful subscription message is displayed
    And I close the browser

  	Examples: 
      | header | email                 |
      | Cart   | sample_mvnc@email.com |