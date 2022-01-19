package infrastructure;

import business.Statement;

import java.time.format.DateTimeFormatter;

public class ConsoleStatementPrinter implements IConsoleStatementPrinter {
    @Override
    public void print(Statement statement) {
        String separator = " || ";
        StringBuilder statementLinesWithHeader = new StringBuilder("OPERATION || AMOUNT || BALANCE || DATE");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        statement.getStatementLines().forEach(statementLine -> {
            String line = "\n" + statementLine.operation().operationType().toString()
                    .concat(separator)
                    .concat(statementLine.operation().amount().value().toString())
                    .concat(separator)
                    .concat(statementLine.balance().value().toString())
                    .concat(separator)
                    .concat(statementLine.operation().localDateTime().format(formatter));
            statementLinesWithHeader.append(line);
        });
        System.out.println(statementLinesWithHeader);
    }
}
