@Regression
Feature: Test Case 6 - Contact Us Form

  Scenario Outline: Submit Contact Us Form
    Given I launched website
    And I verify that home page is visible
    And I landed on <header> page
    And I verify that get in touch is visible
    When I fill up contact us text fields with <name>, <email>, <subject>, <message>
    And I upload attachment with <fileName>
    And I submit contact us form
    Then I verify that success message is visible
    And I close the browser

    #Note: addressLine format (values separated by comma with no space) -> address1,address2,country,state,city,zipcode
    Examples: 
      | header     | name        | email                 | subject        | message        | fileName             |
      | Contact Us | Sample MVNC | sample_mvnc@email.com | Sample Subject | Sample Message | SampleAttachment.txt |
