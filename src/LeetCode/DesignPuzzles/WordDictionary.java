package LeetCode.DesignPuzzles;

import java.util.HashMap;

/**
 * Created by warn on 7/6/2016.
 * Design a data structure that supports the following two operations:
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it
 * can represent any one letter.
 * <p>
 * For example:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 */
public class WordDictionary {
    TrieNode root = new TrieNode();

    public static void main(String args[]) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("aaaa");
        wordDictionary.addWord("abc");
        wordDictionary.addWord("def");
        wordDictionary.addWord("acdc");
        wordDictionary.addWord("ade");
        wordDictionary.addWord("d");
        System.out.println(wordDictionary.search("."));
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        if (word == null) root.isEnd = true;
        else {
            TrieNode pointer = root;
            for (int i = 0, n = word.length(); i < n; i++) {
                char c = word.charAt(i);
                if (!pointer.nodeHashMap.containsKey(c)) pointer.nodeHashMap.put(c, new TrieNode());
                pointer = pointer.nodeHashMap.get(c);
            }
            pointer.isEnd = true;
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if (word == null || word.length() == 0) return root.isEnd;
        else return searchRegex(word, 0, root);
    }

    boolean searchRegex(String word, int index, TrieNode node) {
        if (index == word.length()) return node.isEnd;
        char c = word.charAt(index);
        if (c == '.') {
            for (TrieNode pointer : node.nodeHashMap.values()) {
                if (searchRegex(word, index + 1, pointer)) return true;
            }
            return false;
        } else return node.nodeHashMap.containsKey(c) && searchRegex(word, index + 1, node.nodeHashMap.get(c));
    }

    class TrieNode {
        boolean isEnd;
        HashMap<Character, TrieNode> nodeHashMap;

        TrieNode() {
            nodeHashMap = new HashMap<>();
            isEnd = false;
        }
    }
}


// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");