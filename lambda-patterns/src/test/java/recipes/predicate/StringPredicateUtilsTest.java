package recipes.predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static recipes.predicate.StringPredicateUtils.LONG_LENGTH;
import static recipes.predicate.StringPredicateUtils.STARTS_WITH_HASH;

class StringPredicateUtilsTest {
    private StringPredicateUtils utils = new StringPredicateUtils();
    private String[] names;

    @BeforeEach
    public void setUp() {
        names = Stream.of("#Mall", "#Wash", "Kaylee", "Inara", "Zoë",
                "Jayne", "Simon", "#River", "Shepherd Book")
                .sorted()
                .toArray(String[]::new);
    }

    @Test
    public void getNamesOfConcreteLength() throws Exception {
        assertEquals("#Mall, #Wash, Inara, Jayne, Simon",
                utils.getNamesOfLength(5, names));
    }

    @Test
    public void getNamesStartingWithS() throws Exception {
        assertEquals("Shepherd Book, Simon",
                utils.getNamesStartingWith("S", names));
    }

    @Test
    public void getNamesStartingWithHash() throws Exception {
        assertEquals("#Mall, #River, #Wash",
                utils.getNamesStartingWith("#", names));
    }

    @Test
    public void getNamesSatisfyingCondition() throws Exception {
        assertEquals("#River, Kaylee, Shepherd Book",
                utils.getNamesSatisfyingCondition(s -> s.length() >= 6, names));
        assertEquals("#Mall, #River, #Wash",
                utils.getNamesSatisfyingCondition(s -> s.startsWith("#"),
                        names));
        assertEquals("#River, Kaylee, Shepherd Book",
                utils.getNamesSatisfyingCondition(LONG_LENGTH, names));
        assertEquals("#Mall, #River, #Wash",
                utils.getNamesSatisfyingCondition(STARTS_WITH_HASH, names));
    }

    @Test
    public void composedPredicate() throws Exception {
        assertEquals("#River",
                utils.getNamesSatisfyingCondition(
                        LONG_LENGTH.and(STARTS_WITH_HASH), names));
        assertEquals("#Mall, #River, #Wash, Kaylee, Shepherd Book",
                utils.getNamesSatisfyingCondition(
                        LONG_LENGTH.or(STARTS_WITH_HASH), names));
        assertEquals("#Mall, #Wash, Inara, Jayne, Simon, Zoë",
                utils.getNamesSatisfyingCondition(LONG_LENGTH.negate(), names));
    }
}