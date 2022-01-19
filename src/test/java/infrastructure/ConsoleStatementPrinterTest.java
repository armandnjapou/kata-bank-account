package infrastructure;

import business.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

class ConsoleStatementPrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String separator = " || ";

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(new PrintStream(originalOut));
    }

    @Test
    public void shouldPrintOneStatementLineWhenInvokePrintMethodOnStatementWithOneStatementline() {
        Statement statement = new Statement();
        statement.add(new StatementLine(new Operation(OperationType.DEPOSIT, new Amount(BigDecimal.TEN), LocalDateTime.now()), new Balance(BigDecimal.TEN)));

        String expectedOut = "OPERATION" + separator + "AMOUNT"+ separator +"BALANCE" + separator + "DATE";
        expectedOut = expectedOut.concat("\nDEPOSIT" + separator + "10" + separator + "10" + separator + LocalDateTime.now().format(formatter));

        IConsoleStatementPrinter consoleStatementPrinter = new ConsoleStatementPrinter();
        consoleStatementPrinter.print(statement);
        Assertions.assertEquals(expectedOut, outContent.toString().trim());
    }

    @Test
    public void shouldPrintAllStatementLinesOfStatementInConsoleWhenInvokePrintMethod() {
        Statement statement = new Statement();
        statement.add(new StatementLine(new Operation(OperationType.DEPOSIT, new Amount(BigDecimal.TEN), LocalDateTime.now()), new Balance(BigDecimal.TEN)));
        statement.add(new StatementLine(new Operation(OperationType.DEPOSIT, new Amount(new BigDecimal(25)), LocalDateTime.now().plusHours(2)), new Balance(new BigDecimal(20))));
        statement.add(new StatementLine(new Operation(OperationType.WITHDRAWAL, new Amount(new BigDecimal(15)), LocalDateTime.now().plusDays(2)), new Balance(new BigDecimal(45))));

        IConsoleStatementPrinter consoleStatementPrinter = new ConsoleStatementPrinter();
        consoleStatementPrinter.print(statement);

        AtomicInteger cpt = new AtomicInteger();
        statement.getStatementLines().forEach(statementLine -> {
            String line = "\n" + statementLine.operation().operationType().toString()
                    .concat(separator)
                    .concat(statementLine.operation().amount().value().toString())
                    .concat(separator)
                    .concat(statementLine.balance().value().toString())
                    .concat(separator)
                    .concat(statementLine.operation().localDateTime().format(formatter));
            if(!(outContent.toString().contains(line))){
                cpt.getAndIncrement();
            }
        });
        Assertions.assertEquals(0, cpt.get());
    }
}