package LeetCode;

import java.util.*;

/**
 * Created by warn on 16/2/2016.
 */
public class LeetMedium {
    Map<Integer, Set<Integer>> prerequisiteMap;
    Set<Integer> prerequisite;

    public static void main(String[] args) {
        // put your codes here
        LeetMedium test = new LeetMedium();
//        int[][] courseInfo = {{1, 0}, {0, 3}, {0, 2}, {3, 2}};
//        int[][] courseInfo = {{1, 0}};
//        int[][] courseInfo = {{1, 0}, {0, 1}};
//        int[][] courseInfo = {{0, 1}, {3, 1}, {1, 2}, {3, 2}, {0, 2}};
//        System.out.println(test.canFinishBFS(4, courseInfo));
//        System.out.println(test.findMin(new int[]{10, 20, 40, 60, 0, 1, 4, 6}));
//        System.out.println(test.minPatches2(new int[]{1, 2, 9}, 11));
        System.out.println(test.missingNumber2(new int[]{1, 0}));
    }

    private void getAllPrerequisiteCourse(Integer key) {
        if (!prerequisiteMap.containsKey(key)) return;
        Set<Integer> prerequisitesCourses = prerequisiteMap.get(key);
        for (Integer course : prerequisitesCourses) {
            if (prerequisite.contains(course)) return;
            prerequisite.add(course);
            getAllPrerequisiteCourse(course);
        }
        return;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        prerequisiteMap = new HashMap<>();
        for (int[] i : prerequisites) {
            if (!prerequisiteMap.containsKey(i[1])) prerequisite = new HashSet<>();
            else prerequisite = prerequisiteMap.get(i[1]);
            prerequisite.add(i[0]);
            getAllPrerequisiteCourse(i[0]);
            if (prerequisite.contains(i[1])) return false;
            Set<Integer> newPrerequisite = new HashSet<>();
            newPrerequisite.addAll(prerequisite);
            prerequisiteMap.put(i[1], newPrerequisite);
        }
        return true;
    }

    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> prerequisiteMap = new HashMap<>();
        for (int[] i : prerequisites) {
            if (!prerequisiteMap.containsKey(i[0])) prerequisiteMap.put(i[0], new HashSet<>());
            Set<Integer> prerequisite = prerequisiteMap.get(i[0]);
            prerequisite.add(i[1]);
            Stack<Integer> valueToExpand = new Stack<>();
            Set<Integer> expandedValue = new HashSet<>();
            valueToExpand.addAll(prerequisite);
            while (!valueToExpand.isEmpty()) {
                int course = valueToExpand.pop();
                if (expandedValue.contains(course)) continue;
                else expandedValue.add(course);
                if (prerequisiteMap.containsKey(course)) {
                    valueToExpand.addAll(prerequisiteMap.get(course));
                    prerequisite.addAll(prerequisiteMap.get(course));
                }
                if (valueToExpand.contains(i[0])) return false;
            }
            if (expandedValue.contains(i[0])) return false;
        }
        prerequisiteMap.forEach((k, v) -> System.out.println("key_" + k + ":" + v.toString()));
        return true;
    }

    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites.length == 0) return true;
        int[] courseCounter = new int[numCourses];
        for (int[] i : prerequisites) courseCounter[i[0]]++;

        LinkedList<Integer> courseInfo = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (courseCounter[i] == 0) courseInfo.add(i);

        int numPreCourses = courseInfo.size();
        while (!courseInfo.isEmpty()) {
            int top = courseInfo.remove();
            for (int[] i : prerequisites) {
                if (i[1] == top) {
                    courseCounter[i[0]]--;
                    if (courseCounter[i[0]] == 0) {
                        courseInfo.add(i[0]);
                        numPreCourses++;
                    }
                }
            }
        }
        return numCourses == numPreCourses;
    }

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            throw new IllegalArgumentException("illegal prerequisites array");
        }

        int len = prerequisites.length;

        if (numCourses == 0 || len == 0) {
            return true;
        }

        //track visited courses
        int[] visit = new int[numCourses];

        // use the map to store what courses depend on a course
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] a : prerequisites) {
            if (map.containsKey(a[1])) {
                map.get(a[1]).add(a[0]);
            } else {
                ArrayList<Integer> l = new ArrayList<Integer>();
                l.add(a[0]);
                map.put(a[1], l);
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if (!canFinishDFS(map, visit, i))
                return false;
        }

        return true;
    }

    private boolean canFinishDFS(HashMap<Integer, ArrayList<Integer>> map, int[] visit, int i) {
        if (visit[i] == -1)
            return false;
        if (visit[i] == 1)
            return true;

        visit[i] = -1;
        if (map.containsKey(i)) {
            for (int j : map.get(i)) {
                if (!canFinishDFS(map, visit, j))
                    return false;
            }
        }

        visit[i] = 1;

        return true;
    }


    public int findMin(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) return nums[i + 1];
        }
        return nums[0];
    }

    public Set<Integer> getNSum(Set<Integer> nums, int n) {
        Set<Integer> result = new HashSet<>();
        if (n == 1) {
            result.addAll(nums);
        } else {
            for (int i : nums) {
                Set<Integer> temp = new HashSet<>();
                temp.addAll(nums);
                temp.remove(i);
                Set<Integer> tempResult = getNSum(temp, n - 1);
                temp.clear();
                tempResult.forEach(num -> temp.add(num + i));
                result.addAll(temp);
            }
        }
        return result;
    }

    public int minPatches(int[] nums, int n) {
        if (n <= 0) return 0;
        int result = 0;
        Set<Integer> currentSum = new HashSet<>();

        for (int i = 1; i < nums.length; i++) {
            Set<Integer> temp = new HashSet<>();
            for (int num : nums) temp.add(num);
            currentSum.addAll(getNSum(temp, i));
        }

        for (int i = 1; i < n; i++) {
            if (currentSum.contains(i)) continue;

        }
        return result;
    }

    public int minPatches2(int[] nums, int n) {
        int count = 0, i = 0;
        for (long covered = 0; covered < n; ) {
            if ((i < nums.length && nums[i] > covered + 1) || i >= nums.length) {
                // at this moment, we need (covered+1), patch it.
                covered += covered + 1;
                ++count;
            } else {
                covered += nums[i++];
            }
        }
        return count;
    }

    public int missingNumber(int[] nums, int startIndex, int endIndex) {
        if (nums[startIndex] > startIndex) return startIndex;
        if (nums[endIndex] == endIndex) return endIndex + 1;
        int missing;
        int midIndex = (startIndex + endIndex) / 2;
        if (nums[midIndex] > midIndex) {
            missing = missingNumber(nums, startIndex, midIndex);
        } else {
            missing = missingNumber(nums, midIndex + 1, endIndex);
        }
        return missing;
    }

    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        return missingNumber(nums, 0, nums.length - 1);
    }

    public int missingNumber2(int[] nums) {
        int sumArray = 0;
        for (int num : nums) sumArray += num;
        return nums.length * (nums.length + 1) / 2 - sumArray;
    }

    public int searchInsert(int[] nums, int target) {
        return searchInsert(nums, target, 0, nums.length);
    }

    public int searchInsert(int[] nums, int target, int startIndex, int endIndex) {
        if (nums[endIndex - 1] < target) return endIndex;
        else if (nums[startIndex] >= target) return startIndex;
        else if (nums[endIndex - 1] == target) return endIndex - 1;
        else if (endIndex - startIndex <= 1) {
            return startIndex;
        }
        int midIndex = (startIndex + endIndex) / 2;
        if (nums[midIndex] == target) return midIndex;
        else if (nums[midIndex] < target) return searchInsert(nums, target, midIndex + 1, endIndex);
        else return searchInsert(nums, target, startIndex, midIndex);
    }


    public int countDigitOne(int n) {
        int count = 0;
        int max = Integer.min(n, 9);
        for (int i = 1; i <= max; i++) {
            int temp = i;
            while (temp > 0) {
                if (temp % 10 == 1) count++;
                temp /= 10;
            }
        }
        if (10 > n) return count;
        int div = 10;
        while (n / div >= 10) {
            count = div + 10 * count;
            div *= 10;
        }
        count *= n / div;
        count += Integer.min(div, n - div + 1);
        count += countDigitOne(n - n / div * div);
        return count;
    }

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length < 1) return;
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroColumns = new HashSet<>();
        for (int i = 0, n = matrix.length, m = matrix[i].length; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    zeroColumns.add(j);
                    zeroRows.add(i);
                }
            }
        }

        for (int[] i : matrix) System.out.println(Arrays.toString(i));

        for (int row : zeroRows) Arrays.fill(matrix[row], 0);

        for (int col : zeroColumns) for (int i = 0; i < matrix.length; i++) matrix[i][col] = 0;
    }

    public void setZeroesO1(int[][] matrix) {
        if (matrix == null || matrix.length < 1) return;
        boolean row0 = false, col0 = false;
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) row0 = true;
                    if (j == 0) col0 = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if ((i == 0 && row0) || i != 0 && matrix[i][0] == 0 || j == 0 && col0 || j != 0 && matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }
    }

    public List<Integer> majorityElement(int[] nums) {
        int[] majorityNum1 = new int[2];
        int[] majorityNum2 = new int[2];
        int minMajority = nums.length / 3;
        for (int i : nums) {
            if (i == majorityNum1[0]) majorityNum1[1]++;
            else if (i == majorityNum2[0]) majorityNum2[1]++;
            else if (majorityNum1[1] == 0) {
                majorityNum1[0] = i;
                majorityNum1[1] = 1;
            } else if (majorityNum2[1] == 0) {
                majorityNum2[0] = i;
                majorityNum2[1] = 1;
            } else {
                majorityNum1[1]--;
                majorityNum2[1]--;
            }
        }
        List<Integer> majority = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (num == majorityNum1[0]) count1++;
            else if (num == majorityNum2[0]) count2++;
        }
        if (count1 > minMajority) majority.add(majorityNum1[0]);
        if (count2 > minMajority) majority.add(majorityNum2[0]);
        return majority;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> orderList = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) return orderList;
        int n = matrix[0].length;
        int[] direction = new int[]{0, 1};
        int tempX = 0;
        int tempY = 0;
        int[] bound = {1, m - 1, 0, n - 1, n};
        orderList.add(matrix[0][0]);
        do {
            if (direction[0] == 0) {
                for (tempY += direction[1]; tempY != bound[4]; tempY += direction[1]) {
                    orderList.add(matrix[tempX][tempY]);
                }
                tempY -= direction[1];
            } else {
                for (tempX += direction[0]; tempX != bound[4]; tempX += direction[0]) {
                    orderList.add(matrix[tempX][tempY]);
                }
                tempX -= direction[0];
            }
            System.out.println(Arrays.toString(bound));
        } while (getDirection(direction, bound, tempX, tempY));
        return orderList;
    }

    public boolean getDirection(int[] direction, int[] bound, int positionX, int positionY) {
        boolean changed = false;
        if (direction[0] == 0 && direction[1] > 0) {
            if (positionY == bound[3] && positionX != bound[1]) {
                direction[0] = 1;
                direction[1] = 0;
                bound[3]--;
                changed = true;
                bound[4] = bound[1] + 1;
            }
        } else if (direction[1] == 0 && direction[0] > 0) {
            if (positionX == bound[1] && positionY != bound[2]) {
                bound[1]--;
                direction[0] = 0;
                direction[1] = -1;
                changed = true;
                bound[4] = bound[2] - 1;
            }
        } else if (direction[0] == 0 && direction[1] < 0) {
            if (positionY == bound[2] && positionX != bound[0]) {
                bound[2]++;
                direction[0] = -1;
                direction[1] = 0;
                changed = true;
                bound[4] = bound[0] - 1;
            }
        } else {
            if (positionX == bound[0] && positionY != bound[3]) {
                bound[0]++;
                direction[0] = 0;
                direction[1] = 1;
                changed = true;
                bound[4] = bound[3] + 1;
            }
        }
        return changed;
    }

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        int lastPositive, lastNegative;
        if (max > 0) {
            lastNegative = 0;
            lastPositive = max;
        } else {
            lastNegative = max;
            lastPositive = 0;
        }

        for (int i = 1; i < n; i++) {
            int temp = nums[i];
            if (temp > 0) {
                lastNegative *= temp;
                lastPositive = Integer.max(lastPositive, 1) * temp;
            } else {
                int tempPositive = lastNegative * temp;
                lastNegative = Integer.max(lastPositive, 1) * temp;
                lastPositive = tempPositive;
            }

            max = Integer.max(lastPositive, max);
        }
        return max;
    }

    // recursively
    Map<Integer, Integer> pathInfo = new HashMap<>();

    public int uniquePaths(int m, int n) {
        int key1 = m * 1000 + n;
        int key2 = n * 1000 + m;
        if (m == 1 || n == 1) {
            return 1;
        } else if (m == 2) {
            return n;
        } else if (n == 2) return m;
        else if (pathInfo.containsKey(key1)) return pathInfo.get(key1);
        else if (pathInfo.containsKey(key2)) return pathInfo.get(key2);
        else {
            pathInfo.put(key1, uniquePaths(m - 1, n) + uniquePaths(m, n - 1));
            return pathInfo.get(key1);
        }
    }

    // Dynamic Programming
    public int uniquePathsDP(int m, int n) {
        int[][] path = new int[m][n];
        for (int i = 0; i < m; i++) {
            path[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            path[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                path[i][j] = path[i - 1][j] + path[i][j - 1];
            }
        }
        return path[m - 1][n - 1];
    }

    // Total path is m - n - 2, and if we can select n - 1 steps then the m - 1 step is definitely
    public int uniquePathsSelect(int m, int n) {
        int total = m + n - 2;
        int select = Integer.min(m, n) - 1;
        long result = 1L;
        for (int i = 1; i <= select; i++) {
            result *= total--;
            result /= i;
        }
        return (int) result;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        if (n == 0) return 0;
        int[][] path = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            if (i == m - 1 || path[i + 1][n - 1] != 0) path[i][n - 1] = 1 - obstacleGrid[i][n - 1];
            else path[i][n - 1] = 0;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1 || path[m - 1][i + 1] != 0) path[m - 1][i] = 1 - obstacleGrid[m - 1][i];
            else path[m - 1][i] = 0;
        }
        for (int i = m - 2; i >= 0; i--)
            for (int j = n - 2; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) path[i][j] = 0;
                else path[i][j] = path[i + 1][j] + path[i][j + 1];
            }
        return path[0][0];
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutedNum = new ArrayList<>();
        if (nums == null || nums.length == 0) return permutedNum;
        if (nums.length == 1) {
            List<Integer> permuteTemp = new ArrayList<>();
            permuteTemp.add(nums[0]);
            permutedNum.add(permuteTemp);
            return permutedNum;
        }
        int n = nums.length;
        int num = nums[n - 1];
        int[] tempNums = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            tempNums[i] = nums[i];
        }
        List<List<Integer>> tempPermutedNum = permute(tempNums);
        for (List<Integer> i : tempPermutedNum) {
            for (int j = 0; j < i.size(); j++) {
                List<Integer> newPermute = new ArrayList<>();
                newPermute.addAll(i);
                newPermute.add(j, num);
                permutedNum.add(newPermute);
            }
            i.add(num);
            permutedNum.add(i);
        }
        return permutedNum;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutedNum = new ArrayList<>();
        if (nums == null || nums.length == 0) return permutedNum;
        if (nums.length == 1) {
            List<Integer> permuteTemp = new ArrayList<>();
            permuteTemp.add(nums[0]);
            permutedNum.add(permuteTemp);
            return permutedNum;
        }
        int n = nums.length;
        int num = nums[n - 1];
        int[] tempNums = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            tempNums[i] = nums[i];
        }
        List<List<Integer>> tempPermutedNum = permuteUnique(tempNums);
        Set<Integer> containsList = new HashSet<>();
        int hashCode;
        for (List<Integer> i : tempPermutedNum) {
            int nI = i.size();
            for (int j = 0; j < nI; j++) {
                List<Integer> newPermute = new ArrayList<>();
                for (int k = 0; k < nI; k++) {
                    if (k == j) newPermute.add(num);
                    newPermute.add(i.get(k));
                }
                hashCode = newPermute.hashCode();
                if (!containsList.contains(hashCode)) {
                    permutedNum.add(newPermute);
                    containsList.add(hashCode);
                }
            }
            i.add(num);
            hashCode = i.hashCode();
            if (!containsList.contains(hashCode)) {
                permutedNum.add(i);
                containsList.add(hashCode);
            }
        }
        return permutedNum;
    }
}