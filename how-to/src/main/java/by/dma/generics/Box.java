package by.dma.generics;

/**
 * Good example to play with ERASURE.
 * The common misconception that erasure is “a dirty hack” generally stems from a lack of awareness of what the true
 * costs of the alternative would have been, both in engineering effort, time to market, delivery risk, performance,
 * ecosystem impact, and programmer convenience given the large volume of Java code already written and the diverse
 * ecosystem of both JVM implementations and languages running on the JVM.
 *
 * @author dzmitry.marudau
 * @see https://cr.openjdk.java.net/~briangoetz/valhalla/erasure.html
 * @since 2021.1
 */
class Box<T> {

    private final T t;

    public Box(T t) {
        this.t = t;
    }

    public Box<T> copy() {
        return new Box<>(t);
    }

    public T get() {
        return t;
    }

    public static void main(String[] args) {
        Box<String> bs = new Box<>("hi!"); // safe
        Box<?> bq = bs;                       // safe, via subtyping
        Box<Integer> bi = (Box<Integer>) bq;  // unchecked cast -- warning issued
        Integer i = bi.get();                 // ClassCastException in synthetic cast to Integer
    }
}
