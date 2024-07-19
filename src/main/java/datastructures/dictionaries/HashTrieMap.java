package datastructures.dictionaries;
import cse332.interfaces.trie.TrieMap;
import cse332.types.BString;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }
        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
        }
    }
    public HashTrieNode root;

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        HashTrieNode temp = root; //create temp HashTrieNode to iterate
        V returnValue = null;
        if (root == null) {
            root = new HashTrieNode();
        }
        for (A character : key) {
            if (!temp.pointers.containsKey(character)) { //if pointers doesn't have next character
                temp.pointers.put(character, new HashTrieNode()); // add character
            }
            temp = temp.pointers.get(character); //move to next node
        }
        returnValue = temp.value; //save prev value
        temp.value = value; //set new value
        if (returnValue == null) {
            size++; //increment size
        }
        return returnValue; //return previous value
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (!findPrefix(key)) {
            return null;
        }
        if (key.size() == 0) {
            return root.value;
        }
        HashTrieNode temp = root; //create temp HashTrieMap to iterate
        for (A character : key) { //while key has next
            if (temp.pointers.containsKey(character)) { //if pointers has next character, keep going
                temp = temp.pointers.get(character); //move to next node
            }
            else {
                return null;
            }
        }
        return temp.value; //return value at end of key
    }

    @Override
    public boolean findPrefix(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (this.size == 0) {
            return false;
        }
        HashTrieNode temp = root; //create temp HashTrieMap to iterate
        for (A character : key) { //while key has next
            if (temp.pointers.containsKey(character)) { //if pointers has next character, keep going
                temp = temp.pointers.get(character); //move to next node
            } else { // didn't have next character
                return false; //trie does not contain key, return false
            }
        }
        return true; //trie contains key, return true
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        //System.out.println("beginning of delete size:" + size);
        if (key.size() == 0) { //if key is empty string,
            root.value = null; //delete root value
        }
        if (find(key) != null) { //if the key exists in the trie
            Iterator<A> keyItr = key.iterator(); //create iterator over key
            deleteHelper(keyItr, root, 0, key); //call recursive helper method
            size--;
        }
        //System.out.println("end of delete size:" + size);
    }

    private HashTrieNode deleteHelper(Iterator<A> itr, HashTrieNode copy, int count, K key) {
        //copy is a copy of the HashTrieMap starting at root
        //count is how many nodes deep in the tree we are
        if (count == key.size()) { //base case: reached end of key
            if (copy.pointers.size() == 0) { //if no children at last node in key, delete it
                copy = null; //remove node from trie
            }
            else { //if children, then cant delete, but set value to null
                copy.value = null;
            }
            return copy; //exit the helper method
        }
        A character = itr.next(); //store next char
        HashTrieNode temp = deleteHelper(itr, copy.pointers.get(character), count + 1, key); //recurse to end of key with temp
        if (temp == null) { //if temp is null remove pointer to it
            copy.pointers.remove(character); // remove pointer to temp from previous node (copy)
        }
        else { //otherwise update reference to temp
            copy.pointers.put(character, temp); //
        }
        if (copy.pointers.size() == 0 && copy.value == null) {//if current node has no children and value is null
            copy = null; //remove node from trie
        }
        return copy; //exit the helper method
    }

    @Override
    public void clear() {
        root = new HashTrieNode();
        size = 0;
        System.out.println("clear, size: " + size);
    }
}
