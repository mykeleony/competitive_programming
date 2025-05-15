package leetcode.algorithms.binary_search.maximum_number_of_tasks_you_can_assign;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Solution for LeetCode problem #2071. Maximum Number of Tasks You Can Assign.
 * <p>
 * You have n tasks and m workers. The ith task has a strength requirement tasks[i], and the jth worker has strength workers[j].
 * You also have pills magical pills that can increase a worker's strength by strength. You can use each pill at most once for a worker.
 * <p>
 * You need to assign as many tasks as possible to the workers such that for each assigned task i to a worker j, the worker's strength
 * is greater than or equal to the task's strength (i.e., workers[j] >= tasks[i]). Each worker can be assigned at most one task, and each task
 * can be assigned at most one worker.
 * <p>
 * Return the maximum number of tasks you can assign to the workers.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/">LeetCode 2071</a>
 */
public class Solution {
    public static void main(String[] args) {
        test(new int[]{3, 2, 1}, new int[]{0, 3, 3}, 1, 1, 3);
        test(new int[]{5, 4}, new int[]{0, 0, 0}, 1, 5, 1);
        test(new int[]{1, 2, 3, 4, 5}, new int[]{1, 5}, 1, 3, 2);
        test(new int[]{10, 15, 30}, new int[]{0, 10, 10, 10, 10}, 3, 10, 2);

        // Edge cases
        test(new int[]{}, new int[]{1, 2, 3}, 0, 0, 0);
        test(new int[]{1, 2, 3}, new int[]{}, 0, 0, 0);
        test(new int[]{1, 1, 1}, new int[]{1, 1, 1}, 0, 0, 3);
        test(new int[]{1, 1, 1}, new int[]{2, 2, 2}, 1, 1, 3);
        test(new int[]{5}, new int[]{1}, 1, 10, 1);
        test(new int[]{5}, new int[]{1}, 0, 10, 0);
        test(new int[]{1, 1}, new int[]{1}, 1, 5, 1);
    }

    public static int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);

        int left = 0;
        int right = Math.min(tasks.length, workers.length);
        int maxTasks = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canAssignTasks(mid, tasks, workers, pills, strength)) {
                maxTasks = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return maxTasks;
    }

    private static boolean canAssignTasks(int taskCount, int[] tasks, int[] workers, int pills, int strength) {
        if (taskCount == 0) return true;
        if (taskCount > workers.length) return false;

        TreeMap<Integer, Integer> availableWorkers = new TreeMap<>();

        for (int i = workers.length - taskCount; i < workers.length; i++) {
            availableWorkers.put(workers[i], availableWorkers.getOrDefault(workers[i], 0) + 1);
        }

        int usedPills = 0;

        for (int i = taskCount - 1; i >= 0; i--) {
            int currentTask = tasks[i];
            boolean assigned = false;

            // Try without pill first
            Integer capableWorker = availableWorkers.ceilingKey(currentTask);

            if (capableWorker != null) {
                removeWorker(availableWorkers, capableWorker);
                assigned = true;
            } else if (usedPills < pills) {
                capableWorker = availableWorkers.ceilingKey(currentTask - strength);

                if (capableWorker != null) {
                    removeWorker(availableWorkers, capableWorker);
                    usedPills++;
                    assigned = true;
                }
            }

            if (!assigned) return false;
        }

        return true;
    }

    private static void removeWorker(TreeMap<Integer, Integer> workersMap, int workerStrength) {
        int count = workersMap.get(workerStrength);

        if (count == 1) {
            workersMap.remove(workerStrength);
        } else {
            workersMap.put(workerStrength, count - 1);
        }
    }

    private static void test(int[] tasks, int[] workers, int pills, int strength, int expected) {
        int result = maxTaskAssign(tasks, workers, pills, strength);
        System.out.printf("tasks: %-20s, workers: %-20s, pills: %-2d, strength: %-2d | Result: %-2d | Expected: %-2d | %s%n",
                Arrays.toString(tasks),
                Arrays.toString(workers),
                pills,
                strength,
                result,
                expected,
                result == expected ? "✓ PASS" : "✗ FAIL");
    }
}