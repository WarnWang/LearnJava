package javabasic;

class TrieNode {
    char c;
    TrieNode left;
    TrieNode mid;
    TrieNode right;
    boolean value;

    // Initialize your data structure here.
    public TrieNode() {
    }

    public TrieNode(char c) {
        this.c = c;
    }
}

/**
 * Corner cases:
 * * empty string
 * * null
 */
public class Trie {
    private TrieNode root;
    private boolean emptyWord = false;

    public static void main(String args[]) {
        Trie test = new Trie();
        test.insert("abc");
        test.insert("def");
        test.insert("adbc");
        System.out.println(test.search("dbc"));
    }

    public Trie() {
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if ( word.length() == 0 ) {
            emptyWord = true;
            return;
        }
        root = insert(root, word, 0);
    }

    private TrieNode insert(TrieNode x, String word, int d) {
        char c = word.charAt(d);
        if ( x == null ) {
            x = new TrieNode(c);
        }

        if ( c < x.c ) {
            x.left = insert(x.left, word, d);
        } else if ( c > x.c ) {
            x.right = insert(x.right, word, d);
        } else if ( d < word.length() - 1) {
            x.mid = insert(x.mid, word, d + 1);
        } else {
            x.value = true;
        }
        return x;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if ( word.length() == 0 ) { return emptyWord; }
        TrieNode node = search(root, word, 0);
        return node != null && node.value;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if ( prefix.length() == 0 ) { return emptyWord || root != null; }
        TrieNode node = search(root, prefix, 0);
        return node != null;
    }

    private TrieNode search(TrieNode x, String word, int d) {
        if ( x == null ) return null;
        char c = word.charAt(d);
        int comp = x.c - c;
        if ( c < x.c )      return search(x.left, word, d);
        else if ( c > x.c ) return search(x.right, word, d);
        else if ( d < word.length() - 1 )
            return search(x.mid, word, d + 1);
        return x;
    }
}