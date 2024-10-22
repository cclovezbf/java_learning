package com.chenchi.learning.java.extend;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public class MapTest extends AbstractMap {

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
    static class Node<K,V> implements Entry<K, V> {

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }

        @Override
        public V setValue(V value) {
            return null;
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
}
