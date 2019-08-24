package recipes.time;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @author : Dzmitry Marudau
 * @created at : 23:56
 * @since : 2019.08
 **/
public class DateTimeBetweenDemo {
    public static void main(String... args) {
        Instant start = Instant.now();

        LocalDate foundationDateOfBelarus = LocalDate.of(1990, Month.JULY, 27);
        LocalDate today = LocalDate.now();
        System.out.printf("%d days as Belarus became independent %n",
                ChronoUnit.DAYS.between(foundationDateOfBelarus, today));

        System.out.printf("%d years as Belarus became independent %n",
                ChronoUnit.YEARS.between(foundationDateOfBelarus, today));

        Period until = foundationDateOfBelarus.until(today);
        System.out.printf("Period#until: %d year(s), %d month(s), and %d day(s)%n",
                until.getYears(), until.getMonths(), until.getDays());

        Period between = Period.between(foundationDateOfBelarus, today);
        System.out.printf("Period#between: %d year(s), %d month(s), and %d day(s)%n",
                between.getYears(), between.getMonths(), between.getDays());

        Instant end = Instant.now();
        System.out.printf("Duration of the main method execution: %s %n", Duration.between(start, end));
    }
}
