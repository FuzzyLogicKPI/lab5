package ua.kpi.cad.linguisticvar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ua.kpi.cad.linguisticvar.domain.term.Term;

import java.util.List;
import java.util.Set;

@Getter
public class LinguisticVariable {
    private String name;
    private List<Term> terms;
    private Interval interval;

    private Set<String> termsRegistry;

    public LinguisticVariable(String name, List<Term> terms, Interval interval) {
        this.interval = interval;
        this.name = name;
        this.terms = terms;

        terms.forEach(term -> termsRegistry.add(term.getName()));
    }

    public boolean isTermPresent(String termName) {
        return termsRegistry.contains(termName);
    }
}
