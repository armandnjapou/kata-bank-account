package features;

import business.*;
import infrastructure.ConsoleStatementPrinter;
import infrastructure.IConsoleStatementPrinter;
import infrastructure.exception.InsufficientBalanceException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountStepDefinitions {

    private Account account;
    Throwable throwable = null;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final IConsoleStatementPrinter consoleStatementPrinter = new ConsoleStatementPrinter();

    void setup() {
        System.setOut(new PrintStream(outContent));
    }

    void restore() {
        System.setOut(originalOut);
    }

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

    @Given("account with balance of 300")
    public void account_with_balance_of_300() {
        account = new Account(new Balance(new BigDecimal(300)));
    }

    @When("withdrawal an amount of 400")
    public void withdrawal_an_amount_of_400() {
        try {
            account.withdrawal(new Amount(new BigDecimal(400)));
        } catch(Throwable throwable) {
            this.throwable = throwable;
        }
    }

    @Then("denied operation")
    public void denied_operation() {
        Assertions.assertTrue(throwable instanceof InsufficientBalanceException);
    }

    @Given("new account with balance of 100")
    public void new_account_with_balance_of_100() {
        account = new Account(new Balance(new BigDecimal(100)));
    }

    @When("deposit an amount of 50")
    public void deposit_an_amount_of_50() {
        account.deposit(new Amount(new BigDecimal(50)));
    }

    @When("withdrawal an amount of 75")
    public void withdrawal_an_amount_of_75() throws InsufficientBalanceException {
        account.withdrawal(new Amount(new BigDecimal(75)));
    }

    @When("print account")
    public void print_account() {
        setup();
        account.print(consoleStatementPrinter);
    }

    @Then("console print 2 statement lines matching deposit of 50 and withdrawal of 75")
    public void console_print_statement_lines_matching_deposit_of_50_and_withdrawal_of_75() {
        String separator = " || ";
        String expectedOut = "OPERATION" + separator + "AMOUNT"+ separator +"BALANCE" + separator + "DATE";
        expectedOut = expectedOut.concat("\nDEPOSIT" + separator + "50" + separator + "150" + separator + LocalDateTime.now().format(formatter));
        expectedOut = expectedOut.concat("\nWITHDRAWAL" + separator + "75" + separator + "75" + separator + LocalDateTime.now().format(formatter));
        Assertions.assertEquals(expectedOut, outContent.toString().trim());
        restore();
    }
}
