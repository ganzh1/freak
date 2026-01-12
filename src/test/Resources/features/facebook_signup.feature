Feature: Facebook account signup

  Scenario: Successful signup with valid details
    Given user is on Facebook homepage
    When user clicks Create New Account
    And fills first name last name and selects DOB "January" 15 1990
    And selects gender Female and enters email password
    And clicks signup
    Then should see signup confirmation or next step
