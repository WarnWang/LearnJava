package LeetCode.WeeklyContest.SmarkingAlgorithmContest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by warn on 23/10/2016.
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * <p>
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than
 * 20,100.
 * <p>
 * The order of output does not matter.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * Output:
 * [0, 6]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * <p>
 * Input:
 * s: "abab" p: "ab"
 * <p>
 * Output:
 * [0, 1, 2]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * https://leetcode.com/contest/10/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagramsinAString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> anagramIndex = new ArrayList<>();
        if (s == null || s.length() == 0) return anagramIndex;
        else if (p == null || p.length() == 0) {
            for (int i = 0; i < s.length(); i++) {
                anagramIndex.add(i);
            }
            return anagramIndex;
        } else if (s.length() < p.length()) return anagramIndex;

        HashMap<Character, Integer> pMap = new HashMap<>();
        int nP = p.length(), nS = s.length();
        for (int i = 0; i < nP; i++) {
            char c = p.charAt(i);
            if (pMap.containsKey(c)) pMap.put(c, pMap.get(c) + 1);
            else pMap.put(c, 1);
        }

        int[] charS = new int[26];
        int subLen = 0;
        for (int i = 0; i < nS; i++) {
            char c = s.charAt(i);
            if (!pMap.containsKey(c)) {
                subLen = 0;
                Arrays.fill(charS, 0);
            } else {
                charS[c - 'a']++;
                subLen++;
                if (subLen > nP) {
                    charS[s.charAt(i - nP) - 'a']--;
                    subLen--;
                }
                if (subLen==nP && isEAnagrams(pMap, charS)) {
                    anagramIndex.add(i - nP + 1);
                }
            }
        }
        return anagramIndex;
    }

    private boolean isEAnagrams(HashMap<Character, Integer> a, int[] b) {
        for (char c: a.keySet()) {
            if (a.get(c) != b[c - 'a']) return false;
        }
        return true;
    }
}
