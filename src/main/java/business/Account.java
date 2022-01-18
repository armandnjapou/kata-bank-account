package business;

import java.math.BigDecimal;

public class Account {
    private Balance balance;

    public Account() {
        balance = new Balance(BigDecimal.ZERO);
    }

    public Account(Balance balance) {
        this.balance = balance;
    }

    public Balance getBalance() {
        return balance;
    }

    public void deposit(Amount amount) {
        balance = balance.add(amount);
    }

    public void withdrawal(Amount amount) {
        balance = balance.substract(amount);
    }
}
