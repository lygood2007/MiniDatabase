/*
 * Pair: used for storing <key,val>
 * 
 */
public class Pair<K, V> {

    private final K _first;
    private final V _second;

    public static <K, V> Pair<K, V> createPair(K first, V second) {
        return new Pair<K, V>(first, second);
    }

    public Pair(K first, V second) {
        this._first = first;
        this._second = second;
    }

    public K getKey() {
        return _first;
    }

    public V getValue() {
        return _second;
    }
}
