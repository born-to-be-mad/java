package recipes.time;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:27
 * @since : 2019.08
 **/
class ConvertDateUtilsTest {

    private static final LocalDate SAMPLE_LOCAL_DATE = LocalDate.of(2009, 2, 14);
    private static final java.sql.Date SAMPLE_SQL_DATE = new java.sql.Date(1_234_562_400_000L);
    private static final java.util.Date SAMPLE_UTIL_DATE = new java.util.Date(1_234_562_400_000L);
    private static final java.util.Date SAMPLE_UTIL_DATE_WITH_TIME = new java.util.Date(1_234_567_895_678L);
    private static final LocalDateTime SAMPLE_LOCAL_DATE_TIME = LocalDateTime.of(2009, 2, 14, 1, 31, 35, 678_000_000);
    private static final Timestamp SAMPLE_TIMESTAMP = new Timestamp(1_234_567_895_678L);

    private ConvertDateUtils utils = new ConvertDateUtils();

    @Test
    void convertFromSqlDateToLocalDate() {
        assertEquals(SAMPLE_LOCAL_DATE, utils.convertFromSqlDateToLocalDate(SAMPLE_SQL_DATE));
    }



    @Test
    void convertToSqlDateFromLocalDate() {
        assertEquals(SAMPLE_SQL_DATE, utils.convertToSqlDateFromLocalDate(SAMPLE_LOCAL_DATE));
    }

    @Test
    void convertUtilDateToLocalDate() {
        assertEquals(SAMPLE_LOCAL_DATE, utils.convertUtilDateToLocalDate(SAMPLE_SQL_DATE));
        assertEquals(SAMPLE_LOCAL_DATE, utils.convertUtilDateToLocalDate(SAMPLE_UTIL_DATE));
    }

    @Test
    void convertToLocalDateFromUtilDateJava9() {
        assertEquals(SAMPLE_LOCAL_DATE, utils.convertToLocalDateFromUtilDateJava9(SAMPLE_UTIL_DATE));
    }

    @Test
    void convertFromTimestampToLocalDateTime() {
        assertEquals(SAMPLE_LOCAL_DATE_TIME, utils.convertFromTimestampToLocalDateTime(SAMPLE_TIMESTAMP));
    }

    @Test
    void convertFromUtilDateToLocalDateTimeUsingString() {
        assertEquals(SAMPLE_LOCAL_DATE_TIME,
                utils.convertFromUtilDateToLocalDateTimeUsingString(SAMPLE_UTIL_DATE_WITH_TIME));
    }

    @Test
    void convertToTimestampFromLocalDateTime() {
        assertEquals(SAMPLE_TIMESTAMP, utils.convertToTimestampFromLocalDateTime(SAMPLE_LOCAL_DATE_TIME));
    }

    @Test
    void convertToZonedDateTimeFromCalendar() {
    }

    @Test
    void convertToZonedDateTimeFromGregorianCalendar() {
    }

    @Test
    void convertToLocalDateTimeFromCalendar() {
    }
}