package recipes.time;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:11
 * @since : 2019.08
 **/
public class ConvertDateUtils {
    public LocalDate convertFromSqlDateToLocalDate(java.sql.Date sqlDate) {
        return sqlDate.toLocalDate();
    }

    public java.sql.Date convertToSqlDateFromLocalDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    public LocalDate convertUtilDateToLocalDate(java.util.Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    public LocalDateTime convertFromTimestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public LocalDate convertToLocalDateFromUtilDateJava9(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public LocalDateTime convertFromUtilDateToLocalDateTimeUsingString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return LocalDateTime.parse(df.format(date),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public Timestamp convertToTimestampFromLocalDateTime(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    public ZonedDateTime convertToZonedDateTimeFromCalendar(Calendar cal) {
        return ZonedDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId());
    }

    public ZonedDateTime convertToZonedDateTimeFromGregorianCalendar(Calendar cal) {
        return ((GregorianCalendar) cal).toZonedDateTime();
    }

    public LocalDateTime convertToLocalDateTimeFromCalendar(Calendar cal) {
        return LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId());
    }

}
