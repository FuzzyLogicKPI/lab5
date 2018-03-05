package ua.kpi.cad.linguisticvar.domain;

import com.google.inject.Inject;
import ua.kpi.cad.linguisticvar.domain.term.Term;
import ua.kpi.cad.linguisticvar.domain.term.TermBuilder;
import ua.kpi.cad.linguisticvar.domain.term.TermInfo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LinguisticVariableCreator {
    @Inject
    private TermBuilder termBuilder;

    public LinguisticVariableCreator() {}

    public LinguisticVariable create(String name, List<String> termNames,
                                            double leftBoundary, double rightBoundary) {
        List<Term> terms = createTerms(termNames);
        return new LinguisticVariable(name, terms, new Interval(leftBoundary, rightBoundary));
    }

    private List<Term> createTerms(List<String> termNames) {
        int totalNumberOfTerms = termNames.size();
        return IntStream.range(0, totalNumberOfTerms)
                .mapToObj(
                        indexOfTerm -> buildTerm(indexOfTerm, totalNumberOfTerms, termNames.get(indexOfTerm))
                )
                .collect(Collectors.toList());
    }

    private Term buildTerm(int indexOfTerm, int totalNumberOfTerms, String termName) {
        return termBuilder.buildTerm(new TermInfo(termName, indexOfTerm, totalNumberOfTerms));
    }
}
