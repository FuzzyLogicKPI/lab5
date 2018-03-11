package ua.kpi.cad.linguisticvar.domain.operators;

import java.util.Set;

public interface OperatorsLookup {
    Set<String> getAllOperationNames();
    Operator lookup(String operatorName);
}
