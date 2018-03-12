package ua.kpi.cad.linguisticvar.diconfig;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariableCreator;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementParser;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementParserImpl;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementResolver;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementResolverImpl;

public class ApplicationClassesFactory {
    public static final Injector INJECTOR = Guice.createInjector(new AppModule());

    public static LinguisticVariableCreator getLinguisticVariableCreator() {
        return INJECTOR.getInstance(LinguisticVariableCreator.class);
    }

    public static FuzzyStatementParser getFuzzyStatementParser() {
        return INJECTOR.getInstance(FuzzyStatementParserImpl.class);
    }

    public static FuzzyStatementResolver getFuzzyStatementResolver() {
        return INJECTOR.getInstance(FuzzyStatementResolverImpl.class);
    }
}
