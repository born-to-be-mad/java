package by.dma.commons;

import java.util.Collections;
import java.util.List;

public class Utils {

    public String toJDbcParams(List<Integer> ids) {
        List<String> elements = Collections.nCopies(ids.size(), "?");
        return String.join(",", elements);
    }
}
