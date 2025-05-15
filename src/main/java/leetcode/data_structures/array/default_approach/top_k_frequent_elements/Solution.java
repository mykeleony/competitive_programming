package leetcode.data_structures.array.default_approach.top_k_frequent_elements;

import java.util.*;

/**
 * Solution for LeetCode problem #347. Top K Frequent Elements.
 * <p>
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/top-k-frequent-elements/">LeetCode 347</a>
 */
public class Solution {
	
	public static void main(String[] args) {
		test(new int[]{1, 1, 1, 2, 2, 3}, 2, new int[]{1, 2});
		test(new int[]{1}, 1, new int[]{1});
		test(new int[]{1, 2}, 2, new int[]{1, 2});
		test(new int[]{1, 2, 2, 3, 3, 3}, 3, new int[]{3, 2, 1});
		
		// Edge cases
		test(new int[]{}, 0, new int[]{});
		test(new int[]{1, 1, 2, 2}, 1, new int[]{1});
		test(new int[]{1, 1, 2, 2}, 2, new int[]{1, 2});
		test(new int[]{1, 1, 1, 2, 2, 3}, 3, new int[]{1, 2, 3});
	}
	
	/**
	 * Returns the k most frequent elements in the given integer array nums.
	 * This solution uses a frequency map and bucket sort to achieve a time complexity of O(n) in the average case.
	 *
	 * @param nums the input array of integers
	 * @param k    the number of most frequent elements to return
	 * @return an array containing the k most frequent elements
	 */
	public static int[] topKFrequent(int[] nums, int k) {
		// Count frequencies
		Map<Integer, Integer> count = new HashMap<>();
		for (int num : nums) {
			count.put(num, count.getOrDefault(num, 0) + 1);
		}
		
		// Bucket sort based on frequency
		List<Integer>[] buckets = new List[nums.length + 1];
		for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
			int freq = entry.getValue();
			
			if (buckets[freq] == null) {
				buckets[freq] = new ArrayList<>();
			}
			
			buckets[freq].add(entry.getKey());
		}
		
		// Collect the top k frequent elements descending from the buckets
		List<Integer> result = new ArrayList<>();
		for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
			if (buckets[i] != null) {
				result.addAll(buckets[i]);
			}
		}
		
		// Return only the first k elements
		return result.stream().limit(k).mapToInt(Integer::intValue).toArray();
	}
	
	/**
	 * A first attempt at solving the problem, which involves sorting frequencies.
	 * This approach has a time complexity of O(n log k) due to the sorting of frequencies.
	 *
	 * @param nums the input array of integers
	 * @param k    the number of most frequent elements to return
	 * @return an array containing the k most frequent elements
	 */
	public static int[] topKFrequentFirstTry(int[] nums, int k) {
		Map<Integer, Integer> count = new HashMap<>();
		
		for (int num : nums) {
			count.put(num, count.getOrDefault(num, 0) + 1);
		}
		
		List<Integer> result = new ArrayList<>();
		List<Integer> countList = new ArrayList<>(new TreeSet<>(count.values()));
		int j = countList.size();
		
		while (result.size() < k) {
			int topCount = countList.get(--j);
			
			for (Map.Entry<Integer, Integer> countEntry : new HashMap<>(count).entrySet()) {
				if (countEntry.getValue() == topCount) {
					result.add(countEntry.getKey());
					count.remove(countEntry.getKey());
				}
				
				if (result.size() == k) break;
			}
		}
		
		return result.stream().mapToInt(Integer::intValue).toArray();
	}
	
	/**
	 * Helper method to test the topKFrequent function with expected output.
	 *
	 * @param nums     the input array
	 * @param k        the number of top frequent elements to find
	 * @param expected the expected array of top k frequent elements
	 */
	private static void test(int[] nums, int k, int[] expected) {
		int[] result = topKFrequent(nums, k);
		Arrays.sort(result);
		Arrays.sort(expected);
		boolean pass = Arrays.equals(result, expected);
		System.out.printf("Input: %-20s, k: %-2d | Result: %-20s | Expected: %-20s | %s%n",
				Arrays.toString(nums),
				k,
				Arrays.toString(result),
				Arrays.toString(expected),
				pass ? "✓ PASS" : "✗ FAIL");
	}
}