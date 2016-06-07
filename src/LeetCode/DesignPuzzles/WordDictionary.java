package LeetCode.DesignPuzzles;

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
    private Trie dictionary = new Trie();

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
        dictionary.addWord(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return dictionary.searchRegex(word);
    }

    private class TrieNode {
        char c;
        boolean isEnd;
        TrieNode son, larger, smaller;

        TrieNode(char c) {
            this.c = c;
            isEnd = false;
        }
    }

    private class Trie {
        TrieNode root;
        boolean hasEmptyString;

        Trie() {
            hasEmptyString = false;
        }

        void addWord(String word) {
            if (word == null || word.length() == 0) hasEmptyString = true;
            else {
                root = addWord(word, 0, root);
            }
        }

        private TrieNode addWord(String word, int index, TrieNode node) {
            char c = word.charAt(index);
            if (node == null) {
                node = new TrieNode(c);
            }
            if (c > node.c) {
                node.larger = addWord(word, index, node.larger);
            } else if (c < node.c) {
                node.smaller = addWord(word, index, node.smaller);
            } else {
                if (index + 1 == word.length()) node.isEnd = true;
                else node.son = addWord(word, index + 1, node.son);
            }
            return node;
        }

        boolean searchRegex(String word) {
            if (word == null || word.length() == 0) return hasEmptyString;
            else return searchRegex(word, 0, root);
        }

        boolean searchRegex(String word, int index, TrieNode node) {
            char c = word.charAt(index);
            if (node == null) return false;
            if (c == node.c) {
                if (index + 1 == word.length()) return node.isEnd;
                else return searchRegex(word, index + 1, node.son);
            } else if (c == '.') {
                if (index + 1 == word.length()) {
                    return (node.isEnd || searchRegex(word, index, node.larger)
                            || searchRegex(word, index, node.smaller));
                } else {
                    return (searchRegex(word, index + 1, node.son) || searchRegex(word, index, node.larger)
                            || searchRegex(word, index, node.smaller));
                }
            } else if (c > node.c) return searchRegex(word, index, node.larger);
            else return searchRegex(word, index, node.smaller);
        }
    }
}


// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");