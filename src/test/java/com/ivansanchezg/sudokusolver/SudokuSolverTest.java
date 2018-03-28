package com.ivansanchezg.sudokusolver;

import java.io.File;

import com.ivansanchezg.sudokusolver.SudokuSolver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SudokuSolverTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SudokuSolverTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( SudokuSolverTest.class );
    }

    public void testBlankMatrix() {

        int[][] matrix = {
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
        };
        
        assertTrue(SudokuSolver.solve(matrix));
    }

    public void testMatrixWithSolution1() {

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
        
        assertTrue(SudokuSolver.solve(matrix));
    }

    public void testMatrixWithSolution2() {

        int[][] matrix = {
			{1,0,0,4,8,9,0,0,6},
			{7,3,0,0,0,0,0,4,0},
			{0,0,0,0,0,1,2,9,5},
			{0,0,7,1,2,0,6,0,0},
			{5,0,0,7,0,3,0,0,8},
			{0,0,6,0,9,5,7,0,0},
			{9,1,4,6,0,0,0,0,0},
			{0,2,0,0,0,0,0,3,7},
			{8,0,0,5,1,2,0,0,4}
		};
        
        assertTrue(SudokuSolver.solve(matrix));
    }

    public void testMatrixWithSolution3() {

        int[][] matrix = {
			{0,0,0,6,0,0,4,0,0},
			{7,0,0,0,0,3,6,0,0},
			{0,0,0,0,9,1,0,8,0},
			{0,0,0,0,0,0,0,0,0},
			{0,5,0,1,8,0,0,0,3},								
			{0,0,0,3,0,6,0,4,5},
			{0,4,0,2,0,0,0,6,0},
			{9,0,3,0,0,0,0,0,0},
			{0,2,0,0,0,0,1,0,0}
		};
        
        assertTrue(SudokuSolver.solve(matrix));
    }
	
	public void testMatrixWithNoSolution() {
		
		int[][] matrix = {
			{0,0,6,6,0,0,4,0,0},
			{7,0,0,0,0,3,6,0,0},
			{0,0,0,0,9,1,0,8,0},
			{0,0,0,0,0,0,0,0,0},
			{0,5,0,1,8,0,0,0,3},								
			{0,0,0,3,0,6,0,4,5},
			{0,4,0,2,0,0,0,6,0},
			{9,0,3,0,0,0,0,0,0},
			{0,2,0,0,0,0,1,0,0}
		};
		
		assertFalse(SudokuSolver.solve(matrix));
    }
    
    public void testMatrixWithSolutionUsingJson() {

        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/src/test/java/com/ivansanchezg/sudokusolver/sudoku.json");
        assertTrue(SudokuSolver.solveWithJson(filePath));

    }
}
