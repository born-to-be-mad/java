package java9;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:43
 * @since : 2019.10
 **/
public class DateStreamsDemo {
    public static void main(String[] args) {
        DateStreamsDemo demo = new DateStreamsDemo();
        //4 days
        System.out.println(
                demo.getProblematicRangeOfDaysJava8(
                        LocalDate.of(2017, Month.JUNE, 10),
                        LocalDate.of(2017, Month.JUNE, 13)));
        //should be 30 days, but []
        System.out.println(
                demo.getProblematicRangeOfDaysJava8(
                        LocalDate.of(2017, Month.JUNE, 10),
                        LocalDate.of(2017, Month.JULY, 10)));

        //30 days
        System.out.println(
                demo.getCorrectRangeOfDaysJava8(
                        LocalDate.of(2017, Month.JUNE, 10),
                        LocalDate.of(2017, Month.JULY, 10)));

        System.out.println("#### JAVA9 ####");
        System.out.println(
                demo.getRangeOfMonthsJava9(
                        LocalDate.of(2017, Month.JUNE, 10),
                        LocalDate.of(2017, Month.DECEMBER, 10)));
        System.out.println(
                demo.getRangeOfDaysJava9(
                        LocalDate.of(2017, Month.JUNE, 10),
                        LocalDate.of(2017, Month.JULY, 10)));
    }

    public List<LocalDate> getProblematicRangeOfDaysJava8(LocalDate start, LocalDate end) {
        Period period = start.until(end);
        return IntStream.range(0, period.getDays())
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }

    public List<LocalDate> getCorrectRangeOfDaysJava8(LocalDate start, LocalDate end) {
        return LongStream.range(0, ChronoUnit.DAYS.between(start, end))
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }

    public List<LocalDate> getDaysByIterateFromStart(LocalDate start, int days) {
        return Stream.iterate(start, date -> date.plusDays(1))
                .limit(days)
                .collect(Collectors.toList());
    }

    public List<LocalDate> getRangeOfDaysJava9(LocalDate start, LocalDate end) {
        return start.datesUntil(end)
                .collect(Collectors.toList());
    }

    public List<LocalDate> getRangeOfMonthsJava9(LocalDate start, LocalDate end) {
        return start.datesUntil(end, Period.ofMonths(1))
                .collect(Collectors.toList());
    }
}
