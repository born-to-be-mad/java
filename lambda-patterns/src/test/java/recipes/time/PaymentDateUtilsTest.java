package recipes.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
class PaymentDateUtilsTest {

    @Test
    void formatWithDaysUntilOEM() {
        PaymentDateBuilder.Builder builder = PaymentDateBuilder.get();

        assertEquals("07.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-06-27")).withDaysUntilEOM(5).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "3 days until EOM"
        );

        assertEquals("07.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-06-26")).withDaysUntilEOM(5).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "4 days until EOM"
        );

        assertEquals("06.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-06-25")).withDaysUntilEOM(5).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "5 days until EOM"
        );

        assertEquals("06.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-06-24")).withDaysUntilEOM(5).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "6 days until EOM"
        );

        assertEquals("08.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-08-13")).withDaysUntilEOM(5).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "Day in the middle of the month"
        );

        assertEquals("08.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-08-01")).withDaysUntilEOM(5).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "Day at the start of the month"
        );

        assertEquals("09.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-08-28")).withDaysUntilEOM(5).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "Day at the end of the month"
        );

        assertEquals("09.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-08-31")).withDaysUntilEOM(5).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "The last day of the month"
        );
    }

    @Test
    void formatWithoutDaysUntilOEM() {
        PaymentDateBuilder.Builder builder = PaymentDateBuilder.get();

        assertEquals("08.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-08-01")).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "Day at the start of the month"
        );

        assertEquals("08.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-08-10")).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "Day in the middle of the month"
        );

        assertEquals("08.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-08-20")).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "Day in the middle of the month"
        );

        assertEquals("08.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-08-27")).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "Day at the end of the month"
        );

        assertEquals("08.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-08-29")).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "Day at the end of the month"
        );

        assertEquals("08.2019", builder
                         .withOriginalDate(java.sql.Date.valueOf("2019-08-31")).build()
                         .withFormatter().withPattern("MM.yyyy").format(),
                     "The last day of the month"
        );

    }
}