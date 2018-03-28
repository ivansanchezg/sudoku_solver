package com.ivansanchezg.sudokusolver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SudokuValidatorTest extends TestCase {
    
    public SudokuValidatorTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
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
}