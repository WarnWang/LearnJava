package LeetCode;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

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

    /**
     * Original solver by myself, will cost 56ms to solve the problem
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        initState(board);
        int unchanged = 0;
        for (Integer index : possibleValue.keySet()) {
            if (!checkRule(board, index)) checkPoint.add(index);
        }
        while (!checkPoint.isEmpty()) {
            int checkIndex = checkPoint.remove();
            if (!possibleValue.containsKey(checkIndex)) continue;
            boolean changeValue = checkRule(board, checkIndex);
            changeValue |= inferIndexValue(board, checkIndex);
            if (changeValue) {
                checkPoint.addAll(possibleValue.keySet());
                unchanged = 0;
            } else {
                if (possibleValue.get(checkIndex) <= 0) return;
                unchanged++;
            }
            if (possibleValue.isEmpty() || unchanged > possibleValue.size() * 2) break;
        }
        if (possibleValue.isEmpty() || depth > 1) return;
        int guessIndex = findMinimalPossibleValue();
        Set<Integer> guessValue = getPossibleValueOfIndex(guessIndex);
        possibleValue.remove(guessIndex);
        for (int i : guessValue) {
            Map<Integer, Integer> tempPossibleValue = new HashMap<>();
            tempPossibleValue.putAll(possibleValue);
            checkPoint.clear();
            char[][] tempBoard = new char[9][9];
            for (int j = 0; j < 9; j++) System.arraycopy(board[j], 0, tempBoard[j], 0, 9);
            int x = guessIndex / 10;
            int y = guessIndex % 10;
            board[x][y] = (char) (i + '0');
            depth++;
            solveSudoku(board);
            depth--;
            if (possibleValue.isEmpty()) return;
            possibleValue.clear();
            possibleValue.putAll(tempPossibleValue);
            for (int j = 0; j < 9; j++) System.arraycopy(tempBoard[j], 0, board[j], 0, 9);
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
        AtomicInteger sum = new AtomicInteger((2 << 8) - 1);
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if (board[i][j] == '.') {
                    int index = i * 10 + j;
                    possibleValue.put(index, sum.get());
                }
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
        if (potential <= 0) {
            possibleValue.put(index, potential);
            return false;
        }

        for (int i = 0; i < 9; i++) {
            char temp = board[i][y];
            if (temp != '.') {
                int n = 1 << (temp - '1');
                if ((potential & n) != 0) potential -= n;
            }
        }
        if (potential <= 0) {
            possibleValue.put(index, potential);
            return false;
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
        possibleValue.put(index, potential);
        if (potential <= 0) return false;
        int i = 0;
        while (potential != 0) {
            if ((potential & 1) == 1) return false;
            potential >>= 1;
            i++;
        }
        possibleValue.remove(index);
        board[index / 10][index % 10] = (char) (i + '0');
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
            if (tempSet.isEmpty()) break;
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
            if (tempSet.isEmpty()) break;
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
            if (tempSet.isEmpty()) break;
        }
        if (tempSet.size() == 1) {
            possibleValue.remove(index);
            board[x][y] = (char) ((int) tempSet.toArray()[0] + '0');
            return true;
        }
        return false;
    }

    /**
     * Fast version of Soduku solver only about 24ms
     *
     * @param board board of the Sudoku board
     */
    public void solveSudokuBackTracking(char[][] board) {
        Stack<Integer> empty = new Stack<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') empty.push(i * 10 + j);
            }
        }
        solve(board, empty);
    }

    private boolean solve(char[][] board, Stack<Integer> empty) {
        if (empty.isEmpty()) return true;
        int firstValue = empty.peek();
        int col = firstValue / 10, row = firstValue % 10;
        for (int i = 0; i < 9; i++) {
            char temp = (char) (i + '1');
            if (isSafe(board, firstValue, temp)) {
                board[col][row] = temp;
                empty.pop();
                if (solve(board, empty)) return true;
                empty.push(firstValue);
                board[col][row] = '.';
            }
        }
        return false;
    }

    private boolean isSafe(char[][] board, int index, char temp) {
        int col = index / 10, row = index % 10;
        for (int i = 0; i < 9; i++) {
            if (board[i][row] == temp) return false;
            if (board[col][i] == temp) return false;
        }

        int ceilX = col / 3 * 3, ceilY = row / 3 * 3;
        for (int i = 0; i < 9; i++) {
            if (board[ceilX + i % 3][ceilY + i / 3] == temp) return false;
        }

        return true;
    }
}
