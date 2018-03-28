package com.ivansanchezg.sudokusolver;

import com.google.gson.stream.JsonReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TO DO: Validate initial matrix
// TO DO: Split file into multiple files
public class SudokuSolver {
    private static int[][] resultMatrix;
    private static boolean[][] permanentMatrix;
    private static int row, column;
    private static int length;
    private static Scanner scanner;

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

    private static boolean isValidMatrix(int[][] matrix) {
        return true;
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
        if (!isValidMatrix(matrix)) {
            System.out.println("Invalid matrix, please check your matrix.");
            return false;
        }
        initialize(matrix);
        return solveSudoku();
    }

    public static boolean solve() {
        int[][] matrix = buildMatrixFromInput();
        return solve(matrix);
    }

    private static int getGridSizeFromInput() {
        int gridSize = 9;
        double sqrt;
        do {
            System.out.print("Size of grid (default = 9): ");
            String input = scanner.nextLine();
            if (!input.equals("")) {
                try {
                    gridSize = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. " + input + " is not a number");
                }
            } else {
                System.out.println("Empty input");
            }
            sqrt = Math.sqrt(gridSize);
            if (sqrt != Math.floor(sqrt)) {
                System.out.println("Not a valid grid size, number provided must have perfect square root");
            }
        } while (sqrt != Math.floor(sqrt));
        return gridSize;
    }

    private static int[] getRowFromInput(int gridSize) {
        boolean invalidRow;
        int[] row = new int[0];
        do {
            invalidRow = false;
            String line = scanner.nextLine();
            String[] rowStr = line.trim().split(" ");
            if (rowStr.length != gridSize) {
                System.out.println("Wrong number of values");
                invalidRow = true;
            }
            if (!invalidRow) {
                //Convert string to int
                row = new int[rowStr.length];
                for (int index = 0; index < row.length; index++) {
                    try {
                        row[index] = Integer.parseInt(rowStr[index]);
                        ;
                    } catch (NumberFormatException e) {
                        System.out.println("Found an invalid value. " + rowStr[index] + " is not a number");
                        invalidRow = true;
                        break;
                    }
                }
            }
            if (!invalidRow) {
                //Check that numbers are between 0 and gridSize
                for (int index = 0; index < row.length; index++) {
                    if (row[index] < 0 || row[index] > gridSize) {
                        System.out.println("Values must go from 0 (inclusive) to " + gridSize + " (inclusive)");
                        invalidRow = true;
                        break;
                    }
                }
            }
        } while (invalidRow);
        return row;
    }

    private static int[][] buildMatrixFromInput() {
        scanner = new Scanner(System.in);
        int gridSize = getGridSizeFromInput();
        int[][] matrix = new int[gridSize][];
        System.out.println("Insert the sudoku by rows. " +
                "Empty tiles should be filled with zeros and numbers must be separated by an empty space");
        for (int index = 0; index < gridSize; index++) {
            System.out.print("Insert row number " + (index + 1) + ": ");
            matrix[index] = getRowFromInput(gridSize);
        }
        scanner.close();
        return matrix;
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

    public static List<List<Integer>> readJsonFile(String filePath) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        JsonReader jsonReader = null;
        List<List<Integer>> columns = new ArrayList<>();
        try {
            inputStream = new FileInputStream(filePath);            
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            jsonReader = new JsonReader(inputStreamReader);            
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                jsonReader.beginArray();
                List<Integer> row = new ArrayList<>();
                while (jsonReader.hasNext()) {
                    row.add(jsonReader.nextInt());
                }
                jsonReader.endArray();
                columns.add(row);
            }
            jsonReader.endArray();
            
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
				jsonReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
            try {
				inputStreamReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
            try {
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        return columns;
    }

    public static int[][] convertListIntoMatrix(List<List<Integer>> columns) {
        if (columns == null) {
            return null;
        }
        
        // Check that the column and row length is the same
        if (columns.size() != columns.get(0).size()) {
            return null;
        }
        
        int rowSize = columns.get(0).size();
        int[][] matrix = new int[rowSize][];

        int columnIndex = 0;
        for (List<Integer> row : columns) {
            // Check that all rows are the same size
            if (rowSize != row.size()) {
                return null;
            }
            matrix[columnIndex] = new int[rowSize];
            int rowIndex = 0;
            for(int value : row) {
                matrix[columnIndex][rowIndex] = value;
                rowIndex++;
            }
            columnIndex++;
        }
        return matrix;
    }

    public static boolean solveWithJson(String filePath) {
        List<List<Integer>> jsonMatrix = readJsonFile(filePath);
        int[][] matrix = convertListIntoMatrix(jsonMatrix);
        if (matrix == null) {
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
