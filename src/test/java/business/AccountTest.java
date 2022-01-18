package business;

import infrastructure.exception.InsufficientBalanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AccountTest {

    @Test
    public void shouldNewAccountHaveABalanceOfZeroWhenCreatedWithoutBalance() {
        Account newAccount = new Account();
        Assertions.assertEquals(newAccount.getBalance(), new Balance(BigDecimal.ZERO));
    }

    @Test
    public void shouldNewAccountHaveBalanceOfTenWhenCreatedWithBalanceOfTen() {
        Account newAccount = new Account(new Balance(BigDecimal.TEN));
        Assertions.assertEquals(newAccount.getBalance(), new Balance(BigDecimal.TEN));
    }

    @Test
    public void shouldNewAccountWithBalanceofZeroHaveABalanceOfTenWhenDepositAnAmountofTen() {
        Account expectedAccount = new Account();
        expectedAccount.deposit(new Amount(BigDecimal.TEN));
        Assertions.assertEquals(expectedAccount.getBalance(), new Balance(BigDecimal.TEN));
    }

    @Test
    public void shouldAccountWithBalanceOfTenHaveABalanceOfFiftyWhenDepositAnAmountofForty() {
        Account expectedAccount = new Account(new Balance(BigDecimal.TEN));
        expectedAccount.deposit(new Amount(new BigDecimal(40)));
        Assertions.assertEquals(expectedAccount.getBalance(), new Balance(new BigDecimal(50)));
    }

    @Test
    public void shouldAccountWithBalanceOfTenHaveABalanceOfZeroWhenWithdrawalAnAmountOfTen() throws InsufficientBalanceException {
        Account expectedAccount = new Account(new Balance(BigDecimal.TEN));
        expectedAccount.withdrawal(new Amount(BigDecimal.TEN));
        Assertions.assertEquals(expectedAccount.getBalance(), new Balance(BigDecimal.ZERO));
    }

    @Test
    public void shouldAccountWithBalanceOfFiftyHaveABalanceOfThirtyWhenWithdrawalAnAmountOfTwenty() throws InsufficientBalanceException {
        Account expectedAccount = new Account(new Balance(new BigDecimal(50)));
        expectedAccount.withdrawal(new Amount(new BigDecimal(20)));
        Assertions.assertEquals(expectedAccount.getBalance(), new Balance(new BigDecimal(30)));
    }

    @Test
    public void shouldThrowInsufficientBalanceExceptionWhenWithdrawalAnAmountOfTwentyOnAccountWithBalanceOfFifteen() {
        Account expectedAccount = new Account(new Balance(new BigDecimal(15)));
        Throwable expectedThrowable = null;
        try {
            expectedAccount.withdrawal(new Amount(new BigDecimal(20)));
        } catch(Throwable throwable) {
            expectedThrowable = throwable;
        }
        Assertions.assertTrue(expectedThrowable instanceof InsufficientBalanceException);
    }
}