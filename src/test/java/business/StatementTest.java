package business;


import infrastructure.ConsoleStatementPrinter;
import infrastructure.IConsoleStatementPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class StatementTest {

    @Mock
    private IConsoleStatementPrinter statementPrinter;

    @Test
    public void shouldStatementLinesEqualsToOneWhenAddingOneStatementLineToStatementWithNoStatementLines() {
        Statement expectedStatement = new Statement();
        expectedStatement.add(new StatementLine(new Operation(OperationType.DEPOSIT, new Amount(BigDecimal.TEN), LocalDateTime.now()), new Balance(BigDecimal.ZERO)));
        Assertions.assertEquals(expectedStatement.getStatementLines().size(), 1);
    }

    @Test
    public void shouldUseConsoleStatementPrinterPrintMethodWhenInvokePrintMethodOnStatement() {
        Statement statement = new Statement();
        statement.add(new StatementLine(new Operation(OperationType.DEPOSIT, new Amount(BigDecimal.TEN), LocalDateTime.now()), new Balance(BigDecimal.ZERO)));
        statement.print(statementPrinter);
        Mockito.verify(statementPrinter).print(statement);
    }
}