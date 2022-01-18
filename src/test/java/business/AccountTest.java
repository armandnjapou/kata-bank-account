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
}