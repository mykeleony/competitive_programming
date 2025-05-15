package leetcode.data_structures.heap.find_minimum_time_to_reach_last_room_I;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solution for LeetCode problem #3341. Find Minimum Time to Reach Last Room I
 * <p>
 * There is a dungeon with n x m rooms arranged as a grid.
 * <p>
 * You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start
 * moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room.
 * Moving between adjacent rooms takes exactly one second.
 * <p>
 * Return the minimum time to reach the room (n - 1, m - 1).
 * <p>
 * Two rooms are adjacent if they share a common wall, either horizontally or vertically.
 * Problem Link: <a href="https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/">LeetCode 3341</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new int[][]{{0, 4}, {4, 4}}, 6);
		test(new int[][]{{0, 0, 0}, {2, 2, 1}, {1, 2, 0}}, 4);
		test(new int[][]{{0, 0, 0}, {0, 0, 0}}, 3);
		test(new int[][]{{0, 1}, {1, 2}}, 3);
		test(new int[][]{{0}}, 0);
		test(new int[][]{{0, 5}, {0, 0}}, 2);
		test(new int[][]{{5, 0}, {0, 0}}, 2);
		test(new int[][]{{0, 0}, {5, 0}}, 2);
		test(new int[][]{{0, 0}, {0, 5}}, 6);
	}
	
	/**
	 * Calculates the minimum time to reach the bottom-right cell of the grid.
	 * It uses Dijkstra's algorithm with a priority queue to explore cells with the minimum time.
	 *
	 * @param moveTime the 2D array representing the minimum start time for each cell
	 * @return the minimum time to reach the bottom-right cell, or -1 if unreachable
	 */
	public static int minTimeToReach(int[][] moveTime) {
		int rows = moveTime.length;
		int cols = moveTime[0].length;
		
		int[][] minTimeGrid = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			Arrays.fill(minTimeGrid[i], Integer.MAX_VALUE);
		}
		
		// Store {time, row, col} ordered by time
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(cell -> cell[0]));
		
		minTimeGrid[0][0] = 0;
		pq.offer(new int[]{0, 0, 0});
		
		// Up, down, left, right
		int[] rowDirections = {-1, 1, 0, 0};
		int[] colDirections = {0, 0, -1, 1};
		
		while (!pq.isEmpty()) {
			int[] cell = pq.poll();
			int time = cell[0];
			int row = cell[1];
			int col = cell[2];
			
			// Found a shorter path to the current cell. Skip
			if (time > minTimeGrid[row][col]) {
				continue;
			}
			
			// Reached the destination
			if (row == rows - 1 && col == cols - 1) {
				return time;
			}
			
			// Explore neighbors
			for (int i = 0; i < 4; i++) {
				int neighborRow = row + rowDirections[i];
				int neighborCol = col + colDirections[i];
				
				// Check if the neighbor is within the grid boundaries
				if (neighborRow >= 0 && neighborRow < rows && neighborCol >= 0 && neighborCol < cols) {
					int neighborMinStartTime = moveTime[neighborRow][neighborCol];
					int departureTime = Math.max(time, neighborMinStartTime );
					int arrivalTime = departureTime + 1;
					
					// Found a shorter path to the neighbor. Update the distance and add it to the queue
					if (arrivalTime < minTimeGrid[neighborRow][neighborCol]) {
						minTimeGrid[neighborRow][neighborCol] = arrivalTime ;
						pq.offer(new int[]{arrivalTime, neighborRow, neighborCol});
					}
				}
			}
		}
		
		// Destination is unreachable
		return -1;
	}
	
	private static void test(int[][] moveTime, int expected) {
		int result = minTimeToReach(moveTime);
		System.out.printf("Input: %-40s | Result: %-3d | Expected: %-3d | %s%n",
				Arrays.deepToString(moveTime),
				result,
				expected,
				result == expected ? "✓ PASS" : "✗ FAIL");
	}
}