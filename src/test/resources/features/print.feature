Feature: In order to check operations
  As a bank client
  I want to see the history of my operations
  Scenario: Print statement of an account with 2 statement lines
    Given new account with balance of 100
    When deposit an amount of 50
    When withdrawal an amount of 75
    When print account
    Then console print 2 statement lines matching deposit of 50 and withdrawal of 75