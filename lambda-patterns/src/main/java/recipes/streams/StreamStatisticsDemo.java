package recipes.streams;

import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * @author : Dzmitry Marudau
 * @created at : 15:17
 * @since : 2019.07
 **/
public class StreamStatisticsDemo {

    public static void main(String[] args) {
        long countStrings = Stream.of("Dima", "Stanislav", "Herman", "Serg", "Clemens", "Jurgen")
                .count();
        System.out.printf("There are %d elements in the stream(Stream.count)%n", countStrings);

        long countStringsViaCollector = Stream.of("Dima", "Stanislav", "Herman", "Serg", "Clemens", "Jurgen")
                .collect(Collectors.counting());
        System.out.printf("There are %d elements in the stream(Collectors.counting)%n", countStringsViaCollector);

        Stream.of("Dima", "Stanislav", "Herman", "Serg", "Clemens", "Jurgen")
                .collect(Collectors.partitioningBy(name -> name.length() % 2 == 0, Collectors.counting()))
                .forEach((k, v) -> System.out.printf("%5s: %d%n", k, v));

        System.out.println("### STATISTICS ###");
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(100_000)
                .summaryStatistics();
        System.out.println(stats);
        System.out.printf("Statistic for %d numbers were calculated:%n min=%f,%n max=%f,%n sum=%f,%n average=%f%n",
                stats.getCount(), stats.getMin(), stats.getMax(), stats.getSum(), stats.getAverage());

        DoubleSummaryStatistics playersStatisticsViaSummarizin =
                Stream.of(
                        new Player(7_500, "Dzmitry"),
                        new Player(5_120, "Stanislav"),
                        new Player(3_800, "Ivan"),
                        new Player(4_750, "Petr"))
                        .collect(Collectors.summarizingDouble(Player::getSalary));
        System.out.println("Player statistics:" + playersStatisticsViaSummarizin);

        DoubleSummaryStatistics playersStatsViaMapping =
                Stream.of(
                        new Player(7_500, "Dzmitry"),
                        new Player(5_120, "Stanislav"),
                        new Player(3_800, "Ivan"),
                        new Player(4_750, "Petr"))
                        .mapToDouble(Player::getSalary)
                        .collect(DoubleSummaryStatistics::new,
                                DoubleSummaryStatistics::accept,
                                DoubleSummaryStatistics::combine);
        System.out.println("Player statistics:" + playersStatsViaMapping);

    }
}

class Player {
    private double salary;
    private String name;

    Player(double salary, String name) {
        this.salary = salary;
        this.name = name;
    }

    double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Player{" +
                "salary=" + salary +
                ", name='" + name + '\'' +
                '}';
    }
}
