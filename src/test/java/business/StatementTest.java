package business;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class StatementTest {

    @Test
    public void shouldStatementLinesEqualsToOneWhenAddingOneStatementLineToStatementWithNoStatementLines() {
        Statement expectedStatement = new Statement();
        expectedStatement.add(new StatementLine(new Operation(OperationType.DEPOSIT, new Amount(BigDecimal.TEN), LocalDateTime.now()), new Balance(BigDecimal.ZERO)));
        Assertions.assertEquals(expectedStatement.getStatementLines().size(), 1);
    }
}