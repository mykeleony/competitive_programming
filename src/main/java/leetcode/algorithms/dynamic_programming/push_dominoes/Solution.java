package leetcode.algorithms.dynamic_programming.push_dominoes;

/**
 * Solution for LeetCode problem #838. Push Dominoes.
 * <p>
 * There are n dominoes in a line, and we place each domino vertically upright.
 * In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right. * For the first second, only the initially pushed dominoes start falling. For the second second, the dominoes pushed by the initial falling dominoes start falling. This process continues until no more dominoes are falling.
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 * For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
 * <p>
 * You are given a string dominoes representing the initial state where:
 * <p>
 * '.' means that the domino is standing still.
 * 'L' means that the domino is falling to the left.
 * 'R' means that the domino is falling to the right.
 * <p>
 * Return a string representing the final state.
 * Problem Link: <a href="https://leetcode.com/problems/push-dominoes/">LeetCode 838</a>
 */
public class Solution {
	
	public static void main(String[] args) {
		test("RR.L", "RR.L");
		test(".L.R...LR..L..", "LL.RR.LLRRLL..");
		test("R.R.L", "RRR.L");
		
		// Edge cases
		test("...", "...");
		test("RL", "RL");
		test(".", ".");
		test("R.", "RR");
		test(".L", "LL");
		test("R...L", "RR.LL");
		test("R..", "RRR");
		test("..L", "LLL");
	}
	
	public static String pushDominoes(String dominoes) {
		int n = dominoes.length();
		int[] forces = new int[n];
		
		// Calculate forces from left to right
		int force = 0;
		for (int i = 0; i < n; i++) {
			char ch = dominoes.charAt(i);
			
			if (ch == 'R') force = n;
			else if (ch == 'L') force = 0;
			else force = Math.max(force - 1, 0); // Rightward force decays over time
			
			forces[i] = force;
		}
		
		// Calculate forces from right to left
		force = 0;
		for (int i = n - 1; i >= 0; i--) {
			char ch = dominoes.charAt(i);
			
			if (ch == 'L') force = n;
			else if (ch == 'R') force = 0;
			else force = Math.max(force - 1, 0);
			
			forces[i] -= force;
		}
		
		
		StringBuilder result = new StringBuilder();
		
		for (int f : forces) {
			if (f > 0) {
				result.append('R');
			} else if (f < 0) {
				result.append('L');
			} else {
				result.append('.');
			}
		}
		
		return result.toString();
	}
	
	private static void test(String dominoes, String expected) {
		String result = pushDominoes(dominoes);
		System.out.printf("Input: %-15s | Result: %-15s | Expected: %-15s | %s%n",
				"\"" + dominoes + "\"",
				"\"" + result + "\"",
				"\"" + expected + "\"",
				result.equals(expected) ? "✓ PASS" : "✗ FAIL");
	}
}