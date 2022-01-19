package features;

import business.Account;
import business.Amount;
import business.Balance;
import infrastructure.exception.InsufficientBalanceException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

public class AccountStepDefinitions {

    private Account account;

    @Given("account with balance equal to 1500")
    public void account_with_balance_equal_to_1500() {
        account = new Account(new Balance(new BigDecimal(1500)));
    }
    @When("deposit an amount of 250")
    public void deposit_an_amount_of_250() {
        account.deposit(new Amount(new BigDecimal(250)));
    }
    @Then("account balance should be equal to 1750")
    public void account_balance_should_be_equal_to_1750() {
        Assertions.assertEquals(new Balance(new BigDecimal(1750)), account.getBalance());
    }

    @Given("account with balance equal to 700")
    public void account_with_balance_equal_to_700() {
        account = new Account(new Balance(new BigDecimal(700)));
    }
    @When("withdrawal an amount of 500")
    public void withdrawal_an_amount_of_500() throws InsufficientBalanceException {
        account.withdrawal(new Amount(new BigDecimal(500)));
    }
    @Then("account balance equal to 200")
    public void account_balance_equal_to_200() {
        Assertions.assertEquals(new Balance(new BigDecimal(200)), account.getBalance());
    }
}
