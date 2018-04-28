package com.ivansanchezg.sudokusolver;

import java.io.File;
import java.util.List;

import com.ivansanchezg.sudokusolver.MatrixBuilder;
import com.ivansanchezg.sudokusolver.SudokuValidator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class MatrixBuilderTest extends TestCase {

    public MatrixBuilderTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MatrixBuilderTest.class);
    }

    public void testJsonFileToListWithValidJsonFile() {
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/src/test/java/com/ivansanchezg/sudokusolver/sudoku.json");
        List<List<Integer>> jsonFileToList = MatrixBuilder.jsonFileToList(filePath);
        assertEquals(9, jsonFileToList.size());
        assertEquals(9, jsonFileToList.get(0).size());
    }

    public void testConvertListIntoMatrixWithValidList() {
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/src/test/java/com/ivansanchezg/sudokusolver/sudoku.json");
        List<List<Integer>> jsonFileToList = MatrixBuilder.jsonFileToList(filePath);
        int[][] matrix = MatrixBuilder.convertListIntoMatrix(jsonFileToList);
        assertTrue(SudokuValidator.isValidMatrix(matrix));
    }
}