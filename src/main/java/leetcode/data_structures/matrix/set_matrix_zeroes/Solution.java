package leetcode.data_structures.matrix.set_matrix_zeroes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Solution for LeetCode problem #73. Set Matrix Zeroes.
 * <p>
 * Given an m x n integer matrix {@code matrix}, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 * <p>
 * Problem Link: <a href="https://leetcode.com/problems/set-matrix-zeroes/">LeetCode 73</a>
 */
public class Solution {
	public static void main(String[] args) {
		test(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}, new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}});
		
		// Edge cases
		test(new int[][]{{1}}, new int[][]{{1}}); // Single element, not zero
		test(new int[][]{{0}}, new int[][]{{0}}); // Single element, zero
		test(new int[][]{{1, 0}}, new int[][]{{0, 0}}); // 1x2 matrix
		test(new int[][]{{1}, {0}}, new int[][]{{0}, {0}}); // 2x1 matrix
		test(new int[][]{{1, 1}, {1, 1}}, new int[][]{{1, 1}, {1, 1}}); // No zeroes
		test(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 9}}, new int[][]{{1, 0, 3}, {0, 0, 0}, {7, 0, 9}});
		test(new int[][]{{1, 1, 1}, {0, 1, 1}, {1, 1, 1}}, new int[][]{{0, 1, 1}, {0, 0, 0}, {0, 1, 1}}); // Zero in first column
		test(new int[][]{{1, 0, 1}, {1, 1, 1}, {1, 1, 1}}, new int[][]{{0, 0, 0}, {1, 0, 1}, {1, 0, 1}}); // Zero in first row
	}
	
	/**
	 * Sets entire rows and columns to 0 if an element in the matrix is 0.
	 * This solution modifies the matrix in-place using the first row and first column as markers,
	 * achieving O(1) extra space complexity.
	 * Time Complexity: O(m*n), where m is the number of rows and n is the number of columns.
	 * Space Complexity: O(1).
	 *
	 * @param matrix the m x n integer matrix to be modified
	 */
	public static void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		
		int numRows = matrix.length;
		int numCols = matrix[0].length;
		
		boolean firstRowShouldBeZero = false;
		boolean firstColShouldBeZero = false;
		
		// Determines whether the first line should be zeroed,
		// as its original version will be overwritten and this information may be lost.
		for (int j = 0; j < numCols; j++) {
			if (matrix[0][j] == 0) {
				firstRowShouldBeZero = true;
				break;
			}
		}
		
		// Determine if first column must be zeroed
		for (int[] rows : matrix) {
			if (rows[0] == 0) {
				firstColShouldBeZero = true;
				break;
			}
		}
		
		// Use first row and column as flags
		// Start from (1,1) to avoid overwriting flags for first row/column themselves.
		for (int i = 1; i < numRows; i++) {
			for (int j = 1; j < numCols; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0; // Marks line to be zeroed using its first cell
					matrix[0][j] = 0; // Marks column to be zeroed using its first cell
				}
			}
		}
		
		// Zeroes submatrix cells based on the flags (ignoring first row and column)
		for (int i = 1; i < numRows; i++) {
			for (int j = 1; j < numCols; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		
		if (firstRowShouldBeZero) {
			for (int j = 0; j < numCols; j++) {
				matrix[0][j] = 0;
			}
		}
		
		if (firstColShouldBeZero) {
			for (int i = 0; i < numRows; i++) {
				matrix[i][0] = 0;
			}
		}
	}
	
	/**
	 * Sets entire rows and columns to 0 if an element in the matrix is 0.
	 * This is a straightforward approach that uses additional space (O(m + n))
	 * to store the indices of rows and columns that need to be zeroed out.
	 *
	 * @param matrix The m x n integer matrix to be modified.
	 */
	public static void setZeroesFirstTry(int[][] matrix) {
		Set<Integer> rowsToFill = new HashSet<>();
		Set<Integer> colsToFill = new HashSet<>();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					rowsToFill.add(i);
					colsToFill.add(j);
				}
			}
		}
		
		for (Integer row : rowsToFill) {
			fillRow(matrix, row);
		}
		
		for (Integer col : colsToFill) {
			fillCol(matrix, col);
		}
	}
	
	public static void fillRow(int[][] matrix, int row) {
		Arrays.fill(matrix[row], 0);
	}
	
	public static void fillCol(int[][] matrix, int col) {
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][col] = 0;
		}
	}
	
	/**
	 * Helper method to test the setZeroes function with expected output.
	 *
	 * @param inputMatrix    the matrix before modification (will be copied for testing)
	 * @param expectedMatrix the expected matrix after modification
	 */
	private static void test(int[][] inputMatrix, int[][] expectedMatrix) {
		// Create a deep copy of the input matrix to avoid modifying the original test data
		int[][] matrixCopy = new int[inputMatrix.length][];
		
		for (int i = 0; i < inputMatrix.length; i++) {
			matrixCopy[i] = Arrays.copyOf(inputMatrix[i], inputMatrix[i].length);
		}
		
		setZeroes(matrixCopy);
		
		System.out.printf("Input: %-30s | Result: %-30s | Expected: %-30s | %s%n",
				Arrays.deepToString(inputMatrix),
				Arrays.deepToString(matrixCopy),
				Arrays.deepToString(expectedMatrix),
				Arrays.deepEquals(matrixCopy, expectedMatrix) ? "✓ PASS" : "✗ FAIL");
	}
}