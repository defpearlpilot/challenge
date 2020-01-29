package dsa.structures;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
//    static class Entry<K, V> {
//        K key;
//        DLNode<V> node;
//
//        Entry(K key, DLNode<V> value) {
//            this.node = value;
//        }
//    }

    static class KVPair<K, V> {
        K key;
        V value;

        KVPair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<K, DLNode<KVPair<K, V>>> map;
    private DEQueue<KVPair<K, V>> queue;

    LRUCache(int size) {
        map = new HashMap<>(size);
        queue = new DEQueue<>(size);
    }

    V get(K key) {
        DLNode<KVPair<K, V>> vEntry = map.get(key);

        if (vEntry != null) {
            DLNode<KVPair<K,V>> pushed = queue.pushFront(vEntry);
            return pushed.getData().value;
        }

        return null;
    }

    V put(K key, V value) {
        DLNode<KVPair<K, V>> vEntry = map.get(key);

        if (vEntry == null) {
            this.addEntry(key, value);
            return null;
        } else {
            queue.removeNode(vEntry);
            this.addEntry(key, value);
            return vEntry.getData().value;
        }
    }

    private void addEntry(K key, V value) {
        DEQueue.DEQUpdate<KVPair<K, V>> update = queue.addFront(new KVPair<>(key, value));

        if (update.removed != null) {
            map.remove(update.removed.getData().key);
        }

        map.put(key, update.added);
    }

}
