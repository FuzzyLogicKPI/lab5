package ua.kpi.cad.linguisticvar.domain.operators;

import java.util.HashMap;
import java.util.Map;

public class OperatorsLookupTable {
    private static final String SLIGHTLY = "слегка";
    private static final String VERY = "очень";
    private static final String NOT = "не";
    private static final String OR = "или";
    private static final String AND = "и";

    private static Map<String, Operator> lookupTable = new HashMap<>();

    static {
        lookupTable.put(SLIGHTLY, new SlightlyOperator());
        lookupTable.put(VERY, new VeryOperator());
        lookupTable.put(NOT, new NotOperator());
        lookupTable.put(AND, new AndOperator());
        lookupTable.put(OR, new OrOperator());
    }

    public static Operator lookup(String stringRepresentationOfOperator) {
        String lowercaseName = stringRepresentationOfOperator.toLowerCase();
        checkForExistance(lowercaseName);
        return lookupTable.get(lowercaseName);
    }

    private static void checkForExistance(String operation) {
        if (!lookupTable.containsKey(operation)) {
            throw new NotExistingOperationException(operation + " does not exist.");
        }
    }
}
