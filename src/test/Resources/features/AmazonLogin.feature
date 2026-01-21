Feature: FRKQA-2 Amazon Login Automation
  Scenario: Valid Amazon login
    Given Amazon login page is loaded
    When User enters valid credentials
    Then User lands on Amazon homepage
