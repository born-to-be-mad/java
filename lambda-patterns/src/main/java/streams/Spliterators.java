package streams;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import streams.spliterator.PairSpliterator;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:45
 * @since : 2020.08
 **/
public class Spliterators {

  public static Stream<Integer> diff(Stream<Integer> stream) {
    return pairMap(stream, (a, b) -> b - a);
  }

  public static <T> Stream<Map.Entry<T, T>> pairs(Stream<T> stream) {
    return pairMap(stream, AbstractMap.SimpleImmutableEntry::new);
  }

  public static <T, R> Stream<R> pairMap(Stream<T> stream, BiFunction<T, T, R> mapper) {
    return StreamSupport.stream(new PairSpliterator<>(mapper, stream.spliterator()),
                                stream.isParallel())
                        .onClose(stream::close);
  }
}
