package dsaaij;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by warn on 11/3/2016.
 */
public class TextProcessing {
    public static void main(String[] args) {
        String text = "asafderasdfzxcveqr";
        String pattern = "asa";
        System.out.println(findKMP(text.toCharArray(), pattern.toCharArray()));
        System.out.println(text.indexOf(pattern));
    }

    public static int findBrute(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        for (int i = 0; i <= n - m; i++) {
            for (int j = 0; j < m; j++) {
                if (pattern[j] != text[i + j]) break;
                if (j == m - 1) return i;
            }
        }
        return -1;
    }

    public static int findBoyerMoore(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        if (m == 0) return 0;
        if (n == 0 || n < m) return -1;
        Map<Character, Integer> lastOccurence = new HashMap<>();
        for (char aText : text) lastOccurence.put(aText, -1);
        for (int i = 0; i < m; i++) {
            if (!lastOccurence.containsKey(pattern[i])) return -1;
            lastOccurence.put(pattern[i], i);
        }

        for (int i = 0; i <= n - m; i++) {
            for (int j = m - 1; j >= 0; j--) {
                if (text[j + i] == pattern[j]) {
                    if (j == 0) return i;
                } else {
                    if (lastOccurence.get(text[j + i]) == -1) {
                        i += j;
                        break;
                    } else if (lastOccurence.get(text[j + i]) > j) {
                        i += m - j - 1;
                        break;
                    } else {
                        i += m - lastOccurence.get(text[j + i]) - 2;
                        break;
                    }
                }
            }
        }
        return -1;
    }

    public static int[] computeFailureKMP(char[] pattern) {
        int m = pattern.length;
        int[] fail = new int[m];
        for (int i = 0, j = 1; j < m; ) {
            if (pattern[j] == pattern[i]) {
                fail[j++] = ++i;
            } else if (i > 0) i = fail[i - 1];
            else j++;
        }
        return fail;
    }

    public static int findKMP(char[] text, char[] pattern) {
        int m = pattern.length;
        int n = text.length;
        int[] fail = computeFailureKMP(pattern);
        for (int i = 0, j = 0; i < n; i++) {
            if (text[i] == pattern[j]) {
                if (j == m - 1) return i - m + 1;
                j++;
            } else if (j > 0) {
                j = fail[j - 1];
                i--;
            }
        }
        return -1;
    }

    public static int[][] matrixCHain(int[] d) {
        int n = d.length - 1;
        int[][] N = new int[n][n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                int k = i + j;
                N[j][k] = Integer.MAX_VALUE;
                for (int l = j; l < k; l++) {
                    N[j][k] = Integer.min(N[j][k], N[j][l] + N[l + 1][j] + d[j] * d[l + 1] + d[k + 1]);
                }
            }
        }
        return N;
    }

    public static int[][] LCS(char[] X, char[] Y) {
        int n = X.length;
        int m = Y.length;
        int[][] L = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (X[i] == Y[j]) L[i + 1][j + 1] = L[i][j] + 1;
                else L[i + 1][j + 1] = Integer.max(L[i][j + 1], L[i + 1][j]);
            }
        }
        return L;
    }

    public static char[] reconstructLCS(char[] X, char[] Y, int[][] L) {
        int i = X.length;
        int j = Y.length;
        int k = L[i][j];
        char[] solution = new char[k];
        while (k > 0) {
            if (X[i - 1] == Y[j - 1]) {
                solution[--k] = X[i - 1];
                i--;
                j--;
            } else if (L[i - 1][j] > L[i][j - 1]) i--;
            else j--;
        }
        return solution;
    }
}
