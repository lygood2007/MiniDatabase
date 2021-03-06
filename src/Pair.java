/*
 * Pair: used for storing <key,val>
 * 
 */
public class Pair<K extends Comparable, V> {

    private final K _first;
    private V _second;

    public static <K extends Comparable, V> Pair<K, V> createPair(K first, V second) {
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
    
    public void setValue(V second){
    	_second = second;
    }
}
