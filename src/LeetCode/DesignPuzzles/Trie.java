package LeetCode.DesignPuzzles;

import java.util.HashMap;

/**
 * Created by warn on 6/6/2016.
 * Implement a trie with insert, search, and startsWith methods.
 */
class TrieNode {
    HashMap<Character, TrieNode> childNodeMap;
    boolean isEnd;
    // Initialize your data structure here.
    public TrieNode() {
        childNodeMap = new HashMap<>();
        isEnd = false;
    }
}

public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null) {
            root.isEnd = true;
            return;
        }
        char[] wordChar = word.toCharArray();
        TrieNode pointer = root;
        for (char c: wordChar) {
            if (!pointer.childNodeMap.containsKey(c)) {
                pointer.childNodeMap.put(c, new TrieNode());
            }
            pointer = pointer.childNodeMap.get(c);
        }
        pointer.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null) return root.isEnd;
        char[] wordChar = word.toCharArray();
        TrieNode pointer = root;
        for (char c: wordChar) {
            if (!pointer.childNodeMap.containsKey(c)) return false;
            pointer = pointer.childNodeMap.get(c);
        }
        return pointer.isEnd;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null) return true;
        char[] wordChar = prefix.toCharArray();
        TrieNode pointer = root;
        for (char c: wordChar) {
            if (!pointer.childNodeMap.containsKey(c)) return false;
            pointer = pointer.childNodeMap.get(c);
        }
        return true;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
