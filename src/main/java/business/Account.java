package business;

import infrastructure.IConsoleStatementPrinter;
import infrastructure.exception.InsufficientBalanceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account {
    private Balance balance;
    private Statement statement = new Statement();
    private LocalDateTime localDateTime;

    public Account() {
        balance = new Balance(BigDecimal.ZERO);
    }

    public Account(Balance balance) {
        this.balance = balance;
    }

    public Account(Balance balance, LocalDateTime localDateTime) {
        this.balance = balance;
        this.localDateTime = localDateTime;
    }

    public Balance getBalance() {
        return balance;
    }

    public void deposit(Amount amount) {
        balance = balance.add(amount);
        statement.add(new StatementLine(new Operation(OperationType.DEPOSIT, amount, LocalDateTime.now()), balance));
    }

    public void withdrawal(Amount amount) throws InsufficientBalanceException {
        balance = balance.substract(amount);
        if(BigDecimal.ZERO.compareTo(balance.value()) > 0){
            throw new InsufficientBalanceException("Insufficient balance for this operation");
        }
        statement.add(new StatementLine(new Operation(OperationType.WITHDRAWAL, amount, LocalDateTime.now()), balance));
    }

    public void print(IConsoleStatementPrinter statementPrinter) {
        statementPrinter.print(statement);
    }
}
