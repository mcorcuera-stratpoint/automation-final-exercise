@Regression
Feature: Test Case 21 - Add Review on Product

  Scenario Outline: View Product
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I verify that all products page is visible
    When I click on view product of the <ordinal> product
    Then I verify that viewed product information is visible

    Examples: 
      | header   | ordinal |
      | Products | First   |

  Scenario Outline: Write Review
    Given I verify that write your review is visible
    When I fill review details with <name>, <email>, and <message>
    And I submit the review form
    Then I verify that successful review message is displayed
    And I close the browser

    Examples: 
      | name        | email                 | message            |
      | sample_mvnc | sample_mvnc@email.com | Review message 123 |
