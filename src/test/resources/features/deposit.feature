Feature: In order to save money, a client make a deposit on his account
  Scenario: Deposit 250 on an account with balance equal to 1500
    Given account with balance equal to 1500
    When deposit an amount of 250
    Then account balance should be equal to 1750