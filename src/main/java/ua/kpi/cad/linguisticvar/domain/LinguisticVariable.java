package ua.kpi.cad.linguisticvar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class LinguisticVariable {
    private String name;
    private List<Term> terms;
    private Interval interval;

}
