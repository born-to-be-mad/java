package streams;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:08
 * @since : 2020.08
 **/
class SpliteratorsTest {

  @Test
  public void diff() {
    Assertions.assertIterableEquals(
        Spliterators.diff(IntStream.of(2, 3, 5, 7, 11, 13, 17, 19).boxed())
                    .collect(toList()),
        Arrays.asList(1, 2, 2, 4, 2, 4, 2));
  }

  @Test
  public void pairs() {
    Map<Integer, Integer> result =
        Spliterators.pairs(IntStream.of(1, 3, 5, 7).boxed())
                    .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue)
                    );
    Map<Integer, Integer> expected = Map.of(1, 3, 3, 5, 5, 7);
    Assertions.assertEquals(result, expected);
  }
}