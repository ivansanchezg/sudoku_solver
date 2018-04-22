package com.ivansanchezg.sudokusolver;

import java.util.Scanner;

public class MatrixBuilder {

    public static int[][] buildMatrixFromInput() {
        Scanner scanner = new Scanner(System.in);
        int gridSize = getGridSizeFromInput(scanner);
        int[][] matrix = new int[gridSize][];
        System.out.println("Insert the sudoku by rows. " +
                "Empty tiles should be filled with zeros and numbers must be separated by an empty space");
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

}