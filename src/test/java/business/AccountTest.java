package business;

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
    public void shouldAccountWithBalanceOfTenHaveABalanceOfZeroWhenWithdrawalAnAmountOfTen() {
        Account expectedAccount = new Account(new Balance(BigDecimal.TEN));
        expectedAccount.withdrawal(new Amount(BigDecimal.TEN));
        Assertions.assertEquals(expectedAccount.getBalance(), new Balance(BigDecimal.ZERO));
    }

    @Test
    public void shouldAccountWithBalanceOfFiftyHaveABalanceOfThirtyWhenWithdrawalAnAmountOfTwenty() {
        Account expectedAccount = new Account(new Balance(new BigDecimal(50)));
        expectedAccount.withdrawal(new Amount(new BigDecimal(20)));
        Assertions.assertEquals(expectedAccount.getBalance(), new Balance(new BigDecimal(30)));
    }
}