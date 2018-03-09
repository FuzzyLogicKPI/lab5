package ua.kpi.cad.linguisticvar.domain.statementresolving;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;
import ua.kpi.cad.linguisticvar.domain.operator.Operator;

import java.util.List;

public class ParsedStatement {
    private List<StatementUnit> statement;

    public void addOperator(Operator operator) {
        statement.add(new StatementUnit(operator));
    }

    public void addOperand(FuzzySet operand) {
        statement.add(new StatementUnit(operand));
    }

    private static class StatementUnit {
        private FuzzySet operand;
        private Operator operator;

        private StatementUnit(Object unit) {
            if (unit instanceof FuzzySet) {
                operand = (FuzzySet) unit;
            } else {
                operator = (Operator) unit;
            }
        }
    }
}
