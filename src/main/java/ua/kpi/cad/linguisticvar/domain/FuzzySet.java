package ua.kpi.cad.linguisticvar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ua.kpi.cad.linguisticvar.domain.statementresolving.StatementUnit;
import ua.kpi.cad.linguisticvar.domain.statementresolving.StatementUnitType;

@AllArgsConstructor
@Getter
public class FuzzySet implements StatementUnit{
    private double[] membershipFunctionValues;

    @Override
    public final StatementUnitType getType() {
        return StatementUnitType.OPERAND;
    }

    @Override
    public String toString() {
        return "FuzzySet";
    }
}