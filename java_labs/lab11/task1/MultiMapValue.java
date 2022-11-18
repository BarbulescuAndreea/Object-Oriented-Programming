package lab11.task1;

import java.util.*;

public class MultiMapValue<K, V> {
    HashMap<K, ArrayList<V>> map = new HashMap<>();
    public void add(K key, V value) {
        // TODO
        if(map.containsKey(key)){
            map.get(key).add(value);
            return;
        }
        ArrayList<V> list = new ArrayList<>();
        list.add(value);
        map.put(key, list);
    }

    public void addAll(K key, List<V> values) {
        // TODO
        map.put(key, (ArrayList<V>) values);
    }

    public void addAll(MultiMapValue<K, V> map) {
        // TODO
        for(K key : map.map.keySet()){
            this.addAll(key, getValues(key));
        }
    }

    public V getFirst(K key) {
        // TODO
        if(!map.containsKey(key)) {
            return null;
        }else{
            return getValues(key).get(0);
        }
    }

    public List<V> getValues(K key) {
        // TODO
        for (HashMap.Entry<K, ArrayList<V>> entry : map.entrySet()) {
            if(entry.getKey() == key){
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        // TODO
        return map.containsKey(key);
    }

    public boolean isEmpty() {
        // TODO
        return map.isEmpty();
    }

    public List<V> remove(K key) {
        // TODO
        return map.remove(key);
    }

    public int size() {
        // TODO
        return map.size();
    }
}
