package de.undefinedhuman.algovis.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class MultiMap<K, V> {

    private HashMap<K, ArrayList<V>> map = new HashMap<>();

    @SafeVarargs
    public final void addValuesWithKey(K key, V... values) {
        if(map.containsKey(key)) map.get(key).addAll(Arrays.asList(values));
        else map.put(key, new ArrayList<>(Arrays.asList(values)));
    }

    public ArrayList<V> getValuesWithKey(K key) {
        if(!hasKey(key)) return new ArrayList<>();
        return map.get(key);
    }

    public void removeKey(K key) {
        if(!hasKey(key)) return;
        map.get(key).clear();
        map.remove(key);
    }

    public void removeValue(K key, V value) {
        if(!hasKey(key)) return;
        map.get(key).remove(value);
    }

    public boolean hasValue(K key, V value) {
        if(!hasKey(key)) return false;
        return map.get(key).contains(value);
    }

    public boolean hasKey(K key) {
        return map.containsKey(key);
    }

    public ArrayList<V> getAllValues() {
        ArrayList<V> values = new ArrayList<>();
        for(ArrayList<V> valueArray : map.values()) values.addAll(valueArray);
        return values;
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public void clearMap() {
        for(ArrayList<V> list : map.values()) list.clear();
        map.clear();
    }

}
