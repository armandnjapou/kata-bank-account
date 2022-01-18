package business;

import java.util.ArrayList;
import java.util.List;

public class Statement {
    private final List<StatementLine> statementLines = new ArrayList<>();

    public List<StatementLine> getStatementLines() {
        return statementLines;
    }

    public void add(StatementLine statementLine) {
        statementLines.add(statementLine);
    }
}
