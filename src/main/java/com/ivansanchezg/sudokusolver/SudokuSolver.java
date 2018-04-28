package com.ivansanchezg.sudokusolver;

import java.util.List;

// TO DO: Validate initial matrix
public class SudokuSolver {
    private static int[][] resultMatrix;
    private static boolean[][] permanentMatrix;
    private static int row, column;
    private static int length;

    //This matrix will tell if a number has been given as part of the sudoku and cannot be modified.
    private static void buildPermamentMatrix() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (resultMatrix[i][j] == 0) {
                    permanentMatrix[i][j] = false;
                } else {
                    permanentMatrix[i][j] = true;
                }
            }
        }
    }

    private static void initialize(int[][] matrix) {
        resultMatrix = matrix;
        length = resultMatrix[0].length;
        permanentMatrix = new boolean[length][length];
        buildPermamentMatrix();
        row = 0;
        column = 0;
    }

    public static boolean solve(int[][] matrix) {
        if (!SudokuValidator.isValidMatrix(matrix)) {
            System.out.println("Invalid matrix, please check your matrix.");
            return false;
        }
        initialize(matrix);
        return solveSudoku();
    }

    public static boolean solve() {
        int[][] matrix = MatrixBuilder.buildMatrixFromInput();
        return solve(matrix);
    }

    private static boolean solveSudoku() {
        try {
            //The Sudoku will be solved once it passes through all the rows		
            while (row < length) {
                //This value cannot be changed, so we move to the next one
                if (permanentMatrix[row][column]) {
                    nextPosition();
                } else {
                    //Get the current value and increase it, if it is greater than the length (default: 9), return to the previous position
                    int value = resultMatrix[row][column];
                    value++;
                    if (value > length) {
                        resultMatrix[row][column] = 0;
                        //We return until we find a position a number that is not permanent.
                        do {
                            previousPosition();
                        } while (permanentMatrix[row][column]);
                    } else {
                        //Set the value on the current position
                        resultMatrix[row][column] = value;
                        //Check if the number doesn't break the Sudoku rules, if they are not broken, store the number and move to the next location
                        if (SudokuValidator.checkColumn(resultMatrix, value, length, row, column) &&
                            SudokuValidator.checkRow(resultMatrix, value, length, row, column) &&
                            SudokuValidator.checkSquare(resultMatrix, value, length, row, column)) {
                            //The value is valid, and because we already store it, we move to the next position
                            nextPosition();
                        }
                    }
                }
            }
            printMatrix();
            return true;
        } catch (ArrayIndexOutOfBoundsException iob) {
            return false;
        }
    }

    public static boolean solveWithJson(String filePath) {
        List<List<Integer>> jsonMatrix = MatrixBuilder.jsonFileToList(filePath);
        int[][] matrix = MatrixBuilder.convertListIntoMatrix(jsonMatrix);
        if (matrix == null || !SudokuValidator.isValidMatrix(matrix)) {
            return false;
        }
        return solve(matrix);
    }

    private static void nextPosition() {
        column++;
        if (column == length) {
            column = 0;
            row++;
        }
    }

    private static void previousPosition() {
        column--;
        if (column < 0) {
            column = length - 1;
            row--;
        }
    }

    private static void printMatrix() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    
}
