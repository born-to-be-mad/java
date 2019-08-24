package recipes.time;

import java.time.*;
import java.util.Date;
import java.util.Set;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:16
 * @since : 2019.08
 **/
public class JavaTimeDemo {

    public static void main(String[] args) {
        System.out.println("### ###");
        Set<String> regionNames = ZoneId.getAvailableZoneIds();
        System.out.println("There are " + regionNames.size() + " region names");

        LocalDateTime dateTime = LocalDateTime.of(2017, Month.JULY, 4, 13, 20, 10);
        ZonedDateTime nyc = dateTime.atZone(ZoneId.of("Europe/Minsk"));
        System.out.println(nyc);
        ZonedDateTime london = nyc.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println(london);

        System.out.println("### Some methods in the Month enum ###");
        System.out.println("Days in Feb in a leap year: " +
                Month.FEBRUARY.length(true));
        System.out.println("Day of year for first day of Aug (leap year): " +
                Month.AUGUST.firstDayOfYear(true));
        System.out.println("Month.of(1): " + Month.of(1));
        System.out.println("Adding two months: " + Month.JANUARY.plus(2));
        System.out.println("Subtracting a month: " + Month.MARCH.minus(1));
    }

    public LocalDate convertFromUtilDateUsingInstant(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
