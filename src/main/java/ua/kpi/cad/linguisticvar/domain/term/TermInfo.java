package ua.kpi.cad.linguisticvar.domain.term;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class TermInfo {
    private final String termName;

    private final int termIndex;

    private final int totalNumOfTerms;

    public boolean isLastTerm() {
        return totalNumOfTerms - termIndex == 1;
    }

    public boolean isFirstTerm() {
        return termIndex == 0;
    }

}
