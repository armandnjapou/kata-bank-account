Feature: In order to retrieve some or all of my savings
  As a bank client
  I want to make a withdrawal from my account
  Scenario: Withdrawal 500 from an account with balance equals to 700
    Given account with balance equal to 700
    When withdrawal an amount of 500
    Then account balance equal to 200
  Scenario: Withdrawal 400 from an account with balanace of 300
    Given account with balance of 300
    When withdrawal an amount of 400
    Then denied operation