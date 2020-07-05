package streams;

/**
 * Specific holder for value with index.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class IndexedValue<T> {
    public final int index;
    public final T value;

    public IndexedValue(int index, T value) {
        this.index = index;
        this.value = value;
    }
}
