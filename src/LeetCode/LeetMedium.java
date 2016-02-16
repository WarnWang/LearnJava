package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by warn on 16/2/2016.
 */
public class LeetMedium {
    Map<Integer, Set<Integer>> prerequisiteMap;
    Set<Integer> prerequisite;

    public static void main(String[] args) {
        // put your codes here
        LeetMedium test = new LeetMedium();
//        int[][] courseInfo = {{1, 0}, {0, 3}, {0, 2}, {3, 2}, {3, 1}};
        int[][] courseInfo = {{1, 0}, {0, 1}};
        System.out.println(test.canFinish(4, courseInfo));
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
}