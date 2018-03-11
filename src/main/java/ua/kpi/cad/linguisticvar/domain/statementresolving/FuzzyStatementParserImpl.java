package ua.kpi.cad.linguisticvar.domain.statementresolving;

import com.google.inject.Inject;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;
import ua.kpi.cad.linguisticvar.domain.operator.Operator;
import ua.kpi.cad.linguisticvar.domain.operator.OperatorsLookup;

import java.util.*;

import static ua.kpi.cad.linguisticvar.domain.statementresolving.StatementUnitType.OPERATOR;

public class FuzzyStatementParserImpl implements FuzzyStatementParser {

    @Inject
    private AvailableSyntaxDictionary dictionary;
    @Inject
    private OperatorsLookup operatorsLookup;

    @Override
    public ParsedStatement parseForVariable(String statement, LinguisticVariable variable) {
        statement = validateWithVariableAndPrepare(statement, variable);

        // since we have all the information available about the statement
        // and the variable it is used against, plus the set of operators
        // is also known we can fill up the dictionary of possible words
        // which can be present in the statement. Using this dictionary we
        // can both validate the statement and parse it.
        //
        // so using variable and operators lookup table to create dictionary of
        // possible "words" which may occur in expression do be parsed
        dictionary.makeUpFor(variable, operatorsLookup);

        // first just convert string to list of StatementUnits
        List<StatementUnit> unitsOrderedAsInString = parseObjectsFromStringSavingOrder(statement, variable);

        return createParsedStatement(unitsOrderedAsInString);
    }

    private ParsedStatement createParsedStatement(List<StatementUnit> unitsOrderedAsInString) {
        // create something like reverse polish notation
        ParsedStatement polishNotatedStatement = new ParsedStatement();
        Deque<Operator> operatorsStack = new LinkedList<>();

        // - if operator - put to stack if no other operators there or if priority
        // is higher than one which is in stack
        // - if operand simply put to output list
        for (StatementUnit unit : unitsOrderedAsInString) {
            switch (unit.getType()) {
                case OPERATOR:
                    handleOperator((Operator) unit, polishNotatedStatement, operatorsStack);
                    break;
                case OPERAND:
                    polishNotatedStatement.add(unit);
            }
        }

        // all that's left in stack should be written in statement
        while (!operatorsStack.isEmpty()) {
            polishNotatedStatement.add(operatorsStack.pop());
        }

        return polishNotatedStatement;
    }

    private void handleOperator(Operator operator, ParsedStatement polishNotatedStatement,
                                Deque<Operator> operatorsStack) {
        if (operatorsStack.isEmpty()) {
            operatorsStack.push(operator);
        } else {
            // if operator which is already in stack has lower priority
            // add current operator to stack
            boolean didNotPush = true;
            while (didNotPush) {
                Operator previousOperator = operatorsStack.pop();
                if (previousOperator.getPriority() > operator.getPriority()) {
                    polishNotatedStatement.add(previousOperator);
                    if (operatorsStack.isEmpty()) {
                        operatorsStack.add(operator);
                        didNotPush = false;
                    }
                } else {
                    operatorsStack.push(previousOperator);
                    operatorsStack.push(operator);
                    didNotPush = false;
                }
            }
        }
    }

    private List<StatementUnit> parseObjectsFromStringSavingOrder(String statement, LinguisticVariable variable) {
        String[] words = statement.trim().split("\\s+");
        List<StatementUnit> listOfUnitsWithSavedOrder = new ArrayList<>();

        for (String unitName : words) {
            StatementUnitType typeOfUnit = dictionary.getTypeIfExists(unitName);
            switch (typeOfUnit) {
                case OPERAND:
                    listOfUnitsWithSavedOrder.add(variable.getTermByName(unitName).getFuzzySet());
                    break;
                case OPERATOR:
                    listOfUnitsWithSavedOrder.add(operatorsLookup.lookup(unitName));
            }
        }

        validateEndingOfStatement(listOfUnitsWithSavedOrder);

        return listOfUnitsWithSavedOrder;
    }

    private void validateEndingOfStatement(List<StatementUnit> listOfUnitsWithSavedOrder) {
        int listSize = listOfUnitsWithSavedOrder.size();
        StatementUnitType typeOfLastUnit = listOfUnitsWithSavedOrder.get(listSize - 1).getType();
        if (typeOfLastUnit.equals(OPERATOR)) {
            throw new StatementValidationException("Parsing error - unfinished statement");
        }
    }

    private String validateWithVariableAndPrepare(String statement, LinguisticVariable variable) {
        final String variableName = variable.getName().toLowerCase();

        statement = prepare(statement);
        validateStatementByVariableName(statement, variableName);
        // let's follow convention that first element of statement is
        // linguistic variable name, after it operation information follows;
        // so once validation is successful we can just remove name from string
        return statement.replace(variableName,"");
    }

    private String prepare(String statement) {
        return statement.toLowerCase().trim();
    }

    private void validateStatementByVariableName(String statement, String name) {
        if (!statement.contains(name)) {
            throw new StatementValidationException("Statement does not match to passed linguistic variable.");
        }
    }

    private class StatementValidationException extends RuntimeException {
        public StatementValidationException(String message) {
            super(message);
        }
    }
}
