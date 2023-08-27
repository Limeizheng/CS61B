package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>  {


    private static class BSTNode<K extends Comparable<K>, V> {
        /** Store K-V pair in this node */
        public K key;
        public V value;
        /** Store references to children in this node. */
        public BSTNode<K, V> left;
        public BSTNode<K, V> right;

        /** Default Constructor. */
        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            // Children will set to null in default.
        }
    }

    private BSTNode<K, V> root;
    private int size;
    public BSTMap() {
        this.size = 0;
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
    * if value is null, but key isn't this should also can work*/
    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    private boolean containsKeyHelper(BSTNode<K, V> node, K key) {
        if (node == null) {
            return false; // Key isn't present
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return true; // Key is found
        } else if (cmp < 0) {
            return containsKeyHelper(node.left, key); // Search in the left subtree
        } else {
            return containsKeyHelper(node.right, key); // Search in the right subtree
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.*/
    @Override
    public V get(K key){
        return getHelper(root, key);
    }

    private V getHelper(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return getHelper(node.left, key);
        } else {
            return getHelper(node.right, key);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return this.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        root = putHelper(root, key, value);
    }

    private BSTNode<K, V> putHelper(BSTNode<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new BSTNode<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.left = putHelper(node.left, key, value);
        } else {
            node.right = putHelper(node.right, key, value);
        }
        return node;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet(){
        Set<K> keySet = new HashSet<>();
        keySetHelper(root, keySet);
        return keySet;
    }

    private void keySetHelper(BSTNode<K, V> node, Set<K> keySet) {
        if (node == null) {
            return;
        }
        keySet.add(node.key);
        keySetHelper(node.left, keySet);
        keySetHelper(node.right, keySet);
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        V value = get(key);
        if (value != null) {
            root = removeHelper(root, key);
            size--;
        }
        return value;
    }

    private BSTNode<K, V> removeHelper(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null){
                return node.left;
            } else {
                BSTNode<K, V> successor = findSuccessor(node.right);
                successor.left = node.left;
                successor.right = removeHelper(node.right, successor.key);
                return successor;
            }
        } else if (cmp < 0) {
            node.left = removeHelper(node.left, key);
        } else {
            node.right = removeHelper(node.right, key);
        }
        return node;
    }

    private BSTNode<K, V> findSuccessor(BSTNode<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return findSuccessor(node.left);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. */
    @Override
    public V remove(K key, V value){
        V oldValue = get(key);
        if (oldValue != null && oldValue.equals(value)) {
            root = removeHelper(root, key);
            size--;
            return oldValue;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}