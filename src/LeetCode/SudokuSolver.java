package LeetCode;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

/**
 * Created by warn on 28/2/2016.
 */
public class SudokuSolver {
    Map<Integer, Integer> possibleValue = new HashMap<>();
    Queue<Integer> checkPoint = new ConcurrentLinkedDeque<>();
    int depth = 0;

    public static void main(String[] args) {
        SudokuSolver test = new SudokuSolver();
        String[] tempBoard = new String[]{"..9748...", "7........", ".2.1.9...", "..7...24.", ".64.1.59.", ".98...3..",
                "...8.3.2.", "........6", "...2759.."};
        char[][] board = new char[9][9];
        for (int i = 0; i < tempBoard.length; i++) {
            int k = 0;
            for (char j : tempBoard[i].toCharArray()) {
                board[i][k] = j;
                k++;
            }
        }
        test.solveSudoku(board);
        System.out.println(false);
        for (char[] i : board) System.out.println(Arrays.toString(i));
    }

    public void solveSudoku(char[][] board) {
        initState(board);
        checkPoint.addAll(possibleValue.keySet().stream().collect(Collectors.toList()));
        int unchanged = 0;
        while (!checkPoint.isEmpty()) {
            int checkIndex = checkPoint.remove();
            if (!possibleValue.containsKey(checkIndex)) continue;
            boolean changeValue = checkRule(board, checkIndex);
            changeValue |= inferIndexValue(board, checkIndex);
            if (changeValue) {
//                System.out.println(true);
//                for (char[] k : board) System.out.println(Arrays.toString(k));
                checkPoint.addAll(possibleValue.keySet().stream().collect(Collectors.toList()));
                unchanged = 0;
            } else unchanged++;
            if (isSolved() || unchanged > possibleValue.size() * 2) break;
            if (!isValid(board)) return;
        }
        if (isSolved() || depth > 1) return;
        int guessIndex = findMinimalPossibleValue();
        Set<Integer> guessValue = getPossibleValueOfIndex(guessIndex);
        possibleValue.remove(guessIndex);
        for (int i : guessValue) {
            Map<Integer, Integer> tempPossibleValue = new HashMap<>();
            tempPossibleValue.putAll(possibleValue);
            checkPoint.clear();
            char[][] tempBoard = new char[9][9];
            for (int j = 0; j < 9; j++) {
                System.arraycopy(board[j], 0, tempBoard[j], 0, 9);
            }
            int x = guessIndex / 10;
            int y = guessIndex % 10;
            board[x][y] = (char) (i + '0');
            depth++;
            solveSudoku(board);
            depth--;
            System.out.println(true);
            for (char[] k : board) System.out.println(Arrays.toString(k));
            if (isSolved()) return;
            possibleValue.clear();
            possibleValue.putAll(tempPossibleValue);
            for (int j = 0; j < 9; j++) {
                System.arraycopy(tempBoard[j], 0, board[j], 0, 9);
            }
        }
    }

    private Set<Integer> getPossibleValueOfIndex(int index) {
        Set<Integer> values = new HashSet<>();
        if (possibleValue.containsKey(index)) {
            int temp = possibleValue.get(index);
            int i = 1;
            while (temp != 0) {
                if ((temp & 1) == 1) values.add(i);
                i++;
                temp >>= 1;
            }
        }
        return values;
    }

    private int findMinimalPossibleValue() {
        int minN = 10;
        int index = -1;
        for (int i : possibleValue.keySet()) {
            int temp = possibleValue.get(i);
            int n = 0;
            while (temp != 0) {
                if ((temp & 1) == 1) n++;
                temp >>= 1;
            }
            if (n == 2) {
                index = i;
                break;
            } else if (minN > n) {
                minN = n;
                index = i;
            }
        }
        return index;
    }

    private void initState(char[][] board) {
        int sum = (2 << 8) - 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    int index = i * 10 + j;
                    possibleValue.put(index, sum);
                    checkPoint.add(index);
                }
            }
        }
    }

    public boolean isSingleValue(int index, char[][] board) {
        if (!possibleValue.containsKey(index)) return true;
        else {
            int value = possibleValue.get(index);
            int i = 0;
            while (value != 1) {
                if ((value & 1) == 1) return false;
                value >>= 1;
                i++;
            }
            possibleValue.remove(index);
            board[index / 10][index % 10] = (char) (i + '1');
            return true;
        }
    }

    public boolean isSolved() {
        return possibleValue.isEmpty();
    }

    private boolean checkRule(char[][] board, int index) {
        if (!possibleValue.containsKey(index)) return false;
        int potential = possibleValue.get(index);
        int x = index / 10;
        int y = index % 10;
        for (int i = 0; i < 9; i++) {
            char temp = board[x][i];
            if (temp != '.') {
                int n = 1 << (temp - '1');
                if ((potential & n) != 0) potential -= n;
            }
        }

        for (int i = 0; i < 9; i++) {
            char temp = board[i][y];
            if (temp != '.') {
                int n = 1 << (temp - '1');
                if ((potential & n) != 0) potential -= n;
            }
        }

        int ceilX = x / 3 * 3;
        int ceilY = y / 3 * 3;
        for (int i = 0; i < 9; i++) {
            char temp = board[ceilX + i / 3][ceilY + i % 3];
            if (temp != '.') {
                int n = 1 << (temp - '1');
                if ((potential & n) != 0) potential -= n;
            }
        }
        if (potential == 0) return false;
        possibleValue.put(index, potential);
        return isSingleValue(index, board);
    }

    public boolean isValid(char[][] board) {
        int[] temp;
        for (int i = 0; i < board.length; i++) {
            temp = new int[9];
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != '.' && ++temp[board[i][j] - '1'] > 1) return false;
            }

            temp = new int[9];
            for (char[] aBoard : board) {
                if (aBoard[i] != '.' && ++temp[aBoard[i] - '1'] > 1) return false;
            }

            temp = new int[9];
            int tempX = i / 3 * 3, tempY = i % 3 * 3;
            for (int j = 0; j < board.length; j++) {
                if (board[tempX + j % 3][tempY + j / 3] != '.' && ++temp[board[tempX + j % 3][tempY + j / 3] - '1'] > 1)
                    return false;
            }
        }
        return true;
    }

    private boolean inferIndexValue(char[][] board, int index) {
        if (!possibleValue.containsKey(index)) return false;
        int x = index / 10;
        int y = index % 10;
        Set<Integer> valueRange = getPossibleValueOfIndex(index);
        Set<Integer> tempSet = new HashSet<>();
        tempSet.addAll(valueRange);
        for (int i = 0; i < 9; i++) {
            int tempIndex = x * 10 + i;
            if (tempIndex == index || !possibleValue.containsKey(tempIndex)) continue;
            tempSet.removeAll(getPossibleValueOfIndex(tempIndex));
            if (tempSet.size() == 0) break;
        }
        if (tempSet.size() == 1) {
            possibleValue.remove(index);
            board[x][y] = (char) ((int) tempSet.toArray()[0] + '0');
            return true;
        }

        tempSet.addAll(valueRange);
        for (int i = 0; i < 9; i++) {
            int tempIndex = i * 10 + y;
            if (tempIndex == index || !possibleValue.containsKey(tempIndex)) continue;
            tempSet.removeAll(getPossibleValueOfIndex(tempIndex));
            if (tempSet.size() == 0) break;
        }
        if (tempSet.size() == 1) {
            possibleValue.remove(index);
            board[x][y] = (char) ((int) tempSet.toArray()[0] + '0');
            return true;
        }

        int ceilX = x / 3 * 3;
        int ceilY = y / 3 * 3;
        tempSet.addAll(valueRange);
        for (int i = 0; i < 9; i++) {
            int tempIndex = (ceilX + i % 3) * 10 + ceilY + i / 3;
            if (tempIndex == index || !possibleValue.containsKey(tempIndex)) continue;
            tempSet.removeAll(getPossibleValueOfIndex(tempIndex));
            if (tempSet.size() == 0) break;
        }
        if (tempSet.size() == 1) {
            possibleValue.remove(index);
            board[x][y] = (char) ((int) tempSet.toArray()[0] + '0');
            return true;
        }

        return false;
    }
}
