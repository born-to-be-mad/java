package recipes.time;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:27
 * @since : 2019.08
 **/
class ConvertDateUtilsTest {

    private ConvertDateUtils utils = new ConvertDateUtils();

    @Test
    void convertFromSqlDateToLocalDate() {
        assertEquals(LocalDate.of(2009, 2, 14),
                utils.convertFromSqlDateToLocalDate(new java.sql.Date(1234567890000L)));
    }

    @Test
    void convertToSqlDateFromLocalDate() {
        Date expected = new Date(1234567890000L);
        expected.setTime(0);
        Date actual = utils.convertToSqlDateFromLocalDate(LocalDate.of(2009, 2, 14));
        actual.setTime(0);
        assertEquals(expected, actual);
    }

    @Test
    void convertUtilDateToLocalDate() {
    }

    @Test
    void convertFromTimestampToLocalDateTime() {
    }

    @Test
    void convertToLocalDateFromUtilDateJava9() {
    }

    @Test
    void convertFromUtilDateToLocalDateTimeUsingString() {
    }

    @Test
    void convertToTimestampFromLocalDateTime() {
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