package by.dma.parallelism;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Parallelism for Streams.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class ParallelismDemo {

    public static void main(String... args) {
        // Create sample data
        List<Person> persons = Person.createRoster();

        System.out.println("Contents of persons:");
        persons.stream().forEach(Person::printPerson);
        System.out.println();

        System.out.println("##################################################");
        System.out.println("### 1. Average age of male members in parallel ###");
        double average = persons.parallelStream()
                                .filter(p -> p.getGender() == Person.Sex.MALE)
                                .mapToInt(Person::getAge)
                                .average()
                                .getAsDouble();

        System.out.println("Average age of male members in parallel: " + average);

        System.out.println();
        System.out.println("#######################################");
        System.out.println("### 2. Concurrent reduction example ###");
        ConcurrentMap<Person.Sex, List<Person>> byGenderParallel =
                persons.parallelStream()
                       .collect(Collectors.groupingByConcurrent(Person::getGender));

        List<Map.Entry<Person.Sex, List<Person>>> byGenderList = new ArrayList<>(byGenderParallel.entrySet());
        System.out.println("Group members by gender:");
        byGenderList.stream()
                    .forEach(entry -> {
                        System.out.println("Gender: " + entry.getKey());
                        entry.getValue().stream()
                             .map(Person::getName)
                             .forEach(System.out::println);
                    });

        System.out.println();
        System.out.println("###############################################");
        System.out.println("### 3. Examples of ordering and parallelism ###");
        System.out.println("Examples of ordering and parallelism:");
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));

        System.out.println("listOfIntegers:");
        listOfIntegers.stream().forEach(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("listOfIntegers sorted in reverse order:");
        Comparator<Integer> normal = Integer::compare;
        Comparator<Integer> reversed = normal.reversed();
        Collections.sort(listOfIntegers, reversed);
        listOfIntegers.stream().forEach(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("Parallel stream");
        listOfIntegers.parallelStream().forEach(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("Another parallel stream:");
        listOfIntegers.parallelStream().forEach(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("With forEachOrdered:");
        listOfIntegers.parallelStream().forEachOrdered(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println();
        System.out.println("##################################");
        System.out.println("### 4. Example of interference ###");
        try {
            List<String> listOfStrings = new ArrayList<>(Arrays.asList("one", "two"));
            // This will fail as the peek operation will attempt to add the
            // string "three" to the source after the terminal operation has commenced.
            String concatenatedString = listOfStrings.stream()
                                                     // Don't do this! Interference occurs here.
                                                     .peek(s -> listOfStrings.add("three"))
                                                     .reduce((a, b) -> a + " " + b)
                                                     .get();

            System.out.println("Concatenated string: " + concatenatedString);

        } catch (ConcurrentModificationException e) {
            System.out.println("Exception caught: " + e.toString());
        }

        System.out.println();
        System.out.println("###############################################");
        System.out.println("### 5. Stateful lambda expressions examples ###");
        List<Integer> serialStorage = new ArrayList<>();

        System.out.println("Serial stream:");
        listOfIntegers.stream()
                      // Don't do this! It uses a stateful lambda expression.
                      .map(number -> {
                          serialStorage.add(number);
                          return number;
                      })
                      .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println();

        serialStorage.stream().forEachOrdered(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("Parallel stream:");
        List<Integer> parallelStorage = Collections.synchronizedList(new ArrayList<>());
        listOfIntegers.parallelStream()
                      // Don't do this! It uses a stateful lambda expression.
                      .map(number -> {
                          parallelStorage.add(number);
                          return number;
                      })
                      .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println();

        parallelStorage.stream().forEachOrdered(e -> System.out.print(e + " "));
        System.out.println();
    }

}

class Person {
    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    Person(String nameArg, LocalDate birthdayArg, Sex genderArg, String emailArg) {
        name = nameArg;
        birthday = birthdayArg;
        gender = genderArg;
        emailAddress = emailArg;
    }

    public int getAge() {
        return birthday.until(IsoChronology.INSTANCE.dateNow()).getYears();
    }

    public void printPerson() {
        System.out.println(name + ", " + this.getAge());
    }

    public Sex getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    public static List<Person> createRoster() {

        List<Person> roster = new ArrayList<>();
        roster.add(new Person("Fred", IsoChronology.INSTANCE.date(1980, 6, 20), Person.Sex.MALE, "fred@example.com"));
        roster.add(new Person("Jane", IsoChronology.INSTANCE.date(1990, 7, 15), Person.Sex.FEMALE, "jane@example.com"));
        roster.add(
                new Person("George", IsoChronology.INSTANCE.date(1991, 8, 13), Person.Sex.MALE, "george@example.com"));
        roster.add(new Person("Bob", IsoChronology.INSTANCE.date(2000, 9, 12), Person.Sex.MALE, "bob@example.com"));

        return roster;
    }
}


