package ua.kpi.cad.linguisticvar.diconfig;

import com.google.inject.AbstractModule;
import ua.kpi.cad.linguisticvar.domain.operators.DefaultOperatorsLookupTable;
import ua.kpi.cad.linguisticvar.domain.operators.OperatorsLookup;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementParser;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementParserImpl;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementResolver;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementResolverImpl;
import ua.kpi.cad.linguisticvar.domain.term.TermBuilder;
import ua.kpi.cad.linguisticvar.domain.term.TriangularMembershipFunctionTermBuilder;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TermBuilder.class).to(TriangularMembershipFunctionTermBuilder.class);
        bind(OperatorsLookup.class).to(DefaultOperatorsLookupTable.class);
        bind(FuzzyStatementParser.class).to(FuzzyStatementParserImpl.class);
        bind(FuzzyStatementResolver.class).to(FuzzyStatementResolverImpl.class);
    }
}
