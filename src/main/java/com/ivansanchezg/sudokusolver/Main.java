package com.ivansanchezg.sudokusolver;

public class Main {

	public static void main(String[] args) 
	{
        if (args.length == 1) {
            SudokuSolver.solveWithJson(args[0], true);
        } else {
            SudokuSolver.solve(true);
        }		
	}
	
}
