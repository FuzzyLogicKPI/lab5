package ua.kpi.cad.linguisticvar.domain;

import lombok.Getter;
import ua.kpi.cad.linguisticvar.domain.term.Term;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class LinguisticVariable {
    private String name;
    private List<Term> terms;
    private Interval interval;

    private Set<String> termsRegistry;

    LinguisticVariable(String name, List<Term> terms, Interval interval) {
        this.interval = interval;
        this.name = name;
        this.terms = terms;
        this.termsRegistry = new HashSet<>();

        terms.forEach(term -> termsRegistry.add(term.getName().toLowerCase()));
    }

    public boolean isTermPresent(String termName) {
        return termsRegistry.contains(termName);
    }

    public Term getTermByName(String name) {
        return terms.stream()
                .filter(t -> t.getName().equals(name.toLowerCase()))
                .findAny()
                .orElseThrow(() -> new NoSuchTermException("For name " + name));
    }

    private class NoSuchTermException extends RuntimeException {
        public NoSuchTermException(String name) {
            super(name);
        }
    }
}
