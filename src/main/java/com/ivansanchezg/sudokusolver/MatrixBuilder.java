package com.ivansanchezg.sudokusolver;

import com.google.gson.stream.JsonReader;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MatrixBuilder {

    public static int[][] buildMatrixFromInput() {
        Scanner scanner = new Scanner(System.in);
        int gridSize = getGridSizeFromInput(scanner);
        int[][] matrix = new int[gridSize][];
        System.out.println(
            "Insert the sudoku by rows. " +
            "Empty tiles should be filled with zeros and numbers must be separated by an empty space"
        );
        for (int index = 0; index < gridSize; index++) {
            System.out.print("Insert row number " + (index + 1) + ": ");
            matrix[index] = getRowFromInput(gridSize, scanner);
        }
        scanner.close();
        return matrix;
    }

    private static int getGridSizeFromInput(Scanner scanner) {
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

    private static int[] getRowFromInput(int gridSize, Scanner scanner) {
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

    public static List<List<Integer>> jsonFileToList(String filePath) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        JsonReader jsonReader = null;
        List<List<Integer>> matrixList = new ArrayList<>();

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
                matrixList.add(row);
            }
            jsonReader.endArray();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
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
        
        return matrixList;
    }

    public static int[][] convertListIntoMatrix(List<List<Integer>> matrixList) {
        if (matrixList == null) {
            return null;
        }
        
        // Check that the column and row length is the same
        if (matrixList.size() != matrixList.get(0).size()) {
            return null;
        }
        
        int rowSize = matrixList.get(0).size();
        int[][] matrix = new int[rowSize][];

        int columnIndex = 0;
        for (List<Integer> row : matrixList) {
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
}
