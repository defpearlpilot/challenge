package dsa.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    @Test
    void get() {
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "ONE");
        cache.put(2, "TWO");

        String one = cache.get(1);
        assertEquals("ONE", one);

        cache.put(3, "THREE");

        String one2 = cache.get(1);
        assertNull(one2);
    }

    @Test
    void put() {
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "ONE");
        cache.put(2, "TWO");

        cache.put(1, "UNO");

        String s = cache.get(1);
        assertEquals("UNO", s);
    }
}