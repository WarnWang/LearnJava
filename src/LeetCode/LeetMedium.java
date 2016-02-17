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
        System.out.println(test.findMin(new int[]{10, 20, 40, 60, 0, 1, 4, 6}));
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
}