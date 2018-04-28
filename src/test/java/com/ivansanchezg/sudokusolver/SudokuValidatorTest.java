package com.ivansanchezg.sudokusolver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SudokuValidatorTest extends TestCase {
    
    public SudokuValidatorTest(String testName) {
        super(testName);
    }


    public static Test suite() {
        return new TestSuite(SudokuValidatorTest.class);
    }

    public void testCheckColumnWithValidColumn() {
        int[][] matrix = {
			{1,0,0,0},
			{0,0,0,0},
			{2,0,0,0},
			{0,0,0,0}
        };
        assertTrue(SudokuValidator.checkColumn(matrix, 1, matrix[0].length, 0, 0));
    }

    public void testCheckColumnWithInvalidColumn() {
        int[][] matrix = {
			{1,0,0,0},
			{0,0,0,0},
			{1,0,0,0},
			{0,0,0,0}
        };
        assertFalse(SudokuValidator.checkColumn(matrix, 1, matrix[0].length, 0, 0));
    }

    public void testCheckRowWithValidRow() {
        int[][] matrix = {
			{1,2,3,0},
			{0,0,0,0},
			{0,0,0,0},
			{0,0,0,0}
        };
        assertTrue(SudokuValidator.checkRow(matrix, 1, matrix[0].length, 0, 0));
    }

    public void testCheckRowWithInvalidRow() {
        int[][] matrix = {
			{1,2,1,0},
			{0,0,0,0},
			{0,0,0,0},
			{0,0,0,0}
        };
        assertFalse(SudokuValidator.checkRow(matrix, 1, matrix[0].length, 0, 0));
    }

    public void testCheckSquareWithValidSquare() {
        int[][] matrix = {
			{2,0,0,0},
			{0,1,0,0},
			{0,0,0,0},
			{0,0,0,0}
        };
        assertTrue(SudokuValidator.checkSquare(matrix, 1, matrix[0].length, 1, 1));
    }

    public void testCheckSquareWithInvalidSquare() {
        int[][] matrix = {
			{1,0,0,0},
			{0,1,0,0},
			{0,0,0,0},
			{0,0,0,0}
        };
        assertFalse(SudokuValidator.checkSquare(matrix, 1, matrix[0].length, 1, 1));
    }

    public void testCheckSquareWithValidSquare9x9() {
        int[][] matrix = {
			{0,0,0,5,0,3,0,0,0},
			{0,0,0,0,2,0,0,0,0},
			{0,0,0,1,0,4,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
        };
        assertTrue(SudokuValidator.checkSquare(matrix, 1, matrix[0].length, 2, 3));
    }

    public void testCheckSquareWithInvalidSquare9x9() {
        int[][] matrix = {
			{0,0,0,5,0,3,0,0,0},
			{0,0,0,0,2,1,0,0,0},
			{0,0,0,1,0,4,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
        };
        assertFalse(SudokuValidator.checkSquare(matrix, 1, matrix[0].length, 2, 3));
    }

    public void testIsValidMatrixWithValidMatrix() {
        int[][] matrix = {
			{0,0,0,2,6,0,7,0,1},
			{6,8,0,0,7,0,0,9,0},
			{1,9,0,0,0,4,5,0,0},
			{8,2,0,1,0,0,0,4,0},
			{0,0,4,6,0,2,9,0,0},
			{0,5,0,0,0,3,0,2,8},
			{0,0,9,3,2,0,0,7,4},
			{0,4,0,0,5,0,0,3,6},
			{7,0,3,0,1,8,0,0,0}
        };
        assertTrue(SudokuValidator.isValidMatrix(matrix));
    }

    public void testIsValidMatrixWithNullMatrix() {
        int[][] matrix = null;
        assertFalse(SudokuValidator.isValidMatrix(matrix));
    }

    public void testIsValidMatrixWithNxMMatrix1() {
        int[][] matrix = {
			{0,0,0,2,6,0,7,0,1},
			{6,8,0,0,7,0,0,9,0},
			{1,9,0,0,0,4,5,0,0},
        };
        assertFalse(SudokuValidator.isValidMatrix(matrix));
    }

    public void testIsValidMatrixWithNxMMatrix2() {
        int[][] matrix = {
			{0,0,0,2,6,0},
			{6,8,0,0,7,0},
			{1,9,0,0,0,4},
			{8,2,0,1,0,0},
			{0,0,4,6,0,2},
			{0,5,0,0,0,3},
			{0,0,9,3,2,0},
			{0,4,0,0,5,0},
			{7,0,3,0,1,8}
        };
        assertFalse(SudokuValidator.isValidMatrix(matrix));
    }

    public void testIsValidMatrixWithNumberAboveRange() {
        int[][] matrix = {
			{0,0,0,2,6,0,7,0,1},
			{6,8,0,0,7,0,0,9,0},
			{1,9,0,0,0,4,5,0,0},
			{8,2,0,1,11,0,0,4,0},
			{0,0,4,6,0,2,9,0,0},
			{0,5,0,0,0,3,0,2,8},
			{0,0,9,3,2,0,0,7,4},
			{0,4,0,0,5,0,0,3,6},
			{7,0,3,0,1,8,0,0,0}
        };
        assertFalse(SudokuValidator.isValidMatrix(matrix));
    }

    public void testIsValidMatrixWithNumberBelowRange() {
        int[][] matrix = {
			{0,0,0,2,6,0,7,0,1},
			{6,8,0,0,7,0,0,9,0},
			{1,9,0,0,0,4,5,0,0},
			{8,2,0,1,-1,0,0,4,0},
			{0,0,4,6,0,2,9,0,0},
			{0,5,0,0,0,3,0,2,8},
			{0,0,9,3,2,0,0,7,4},
			{0,4,0,0,5,0,0,3,6},
			{7,0,3,0,1,8,0,0,0}
        };
        assertFalse(SudokuValidator.isValidMatrix(matrix));
    }
}