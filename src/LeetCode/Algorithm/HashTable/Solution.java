package LeetCode.Algorithm.HashTable;

import java.util.*;

/**
 * Created by warn on 27/7/2016.
 * Solution to problems with tag hash table
 */
public class Solution {
    /**
     * Given an array of strings, group anagrams together.
     * <p>
     * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Return:
     * <p>
     * [
     * ["ate", "eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * Note: All inputs will be in lower-case.
     *
     * @param strs an array of strings only contains lower-case letter
     * @return an array list
     */
    public List<List<String>> groupAnagrams(String[] strs) {
//        List<List<String>> resultList = new ArrayList<>();
        HashMap<String, List<String>> anagramMap = new HashMap<>();

        if (strs != null)
            for (String word: strs) {
                char[] wordArray = word.toCharArray();
                Arrays.sort(wordArray);
                String anagram = new String(wordArray);
                if (anagramMap.containsKey(anagram)) anagramMap.get(anagram).add(word);
                else anagramMap.put(anagram, new ArrayList<>(Collections.singletonList(word)));
            }
        return new ArrayList<>(anagramMap.values());
    }
}
