package main;

public class SudokuSolver 
{
	int[][] matrix;
	boolean[][] permanentMatrix;
	int row, column;
	final int length;
	
	public SudokuSolver(int[][] mat)
	{
		this.matrix = mat;
		length = matrix[0].length;
		permanentMatrix = new boolean[length][length];
		BuildPermanentMatrix();
		row = 0;
		column = 0;		
	}
	
	//This matrix will tell if a number has been given as part of the sudoku and cannot be modified.
	private void BuildPermanentMatrix()
	{
		for(int i = 0; i < length; i++)
		{
			for(int j = 0; j < length; j++)
			{
				if(matrix[i][j] == 0)
				{
					permanentMatrix[i][j] = false;
				}
				else
				{
					permanentMatrix[i][j] = true;
				}
			}
		}
	}

	public void Solve()
	{
		
		//The Sudoku will be solved once it passes through all the rows		
		while(row < length)
		{
			//This value cannot be changed, so we move to the next one
			if(permanentMatrix[row][column]) 
			{
				nextPosition();
			}
			else
			{
				//Get the current value and increase it, if it is greater than the length (default: 9), return to the previous position
				int value = matrix[row][column];
				value++;
				if(value > length)
				{
					matrix[row][column] = 0;
					//We return until we find a position a number that is not permanent.
					do
					{
						previousPosition();
					}while(permanentMatrix[row][column]);
				}
				else
				{
					//Set the value on the current position
					matrix[row][column] = value;
					//Check if the number doesn't break the Sudoku rules, if they are not broken, store the number and move to the next location
					if(CheckColumn(value) && CheckRow(value) && CheckSquare(value))
					{
						//The value is valid, and because we already store it, we move to the next position
						nextPosition();
					}
				}
			}
		}
		
		PrintMatrix();
	}
	
	private void nextPosition()
	{
		column++;
		if(column == length)
		{
			column = 0;
			row++;
		}
	}
	
	private void previousPosition()
	{
		column--;
		if(column < 0)
		{
			column = length - 1;
			row--;
		}
	}
	
	private void PrintMatrix()
	{
		for(int i = 0; i < length; i++) 
		{
			for(int j = 0; j < length; j++)
			{
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	private boolean CheckColumn(int value)
	{
		//We iterate through the current column and compare. If the value repeats, it is not valid.
		for(int i = 0; i < length; i++)
		{
			if(matrix[i][column] == value && i != row)
			{
				return false;
			}
		}
		return true;
	}
	
	private boolean CheckRow(int value)
	{
		//We iterate through the current row and compare. If the value repeats, it is not valid.
		for(int i = 0; i < length; i++)
		{
			if(matrix[row][i] == value && i != column)
			{
				return false;
			}
		}
		return true;
	}
	
	private boolean CheckSquare(int value)
	{
		//We get the size of the squares with the square root. 9x9 Sudoku has 3x3 squares, 16x16 Sudoku has 4x4 squares.
		int squareSize = (int) Math.sqrt(length);
		
		//Calculate the initial position of the square by dividing the row and column with the size of the square.
		//Example: If row is 4 and column 8, then the squareStartRow will be 1 and the squareStartColumn will be 2.
		//Then we multiple that by the size of the square, so we get 3 for squareStartRow and 6 for the SquareStartColumn
		//And we iterate through the square by adding the square size to the multiplication. So in the example will be
		//from row 3 to 5 and column 6 to 8
		int squareStartRow = row/squareSize;
		int squareStartColumn = column/squareSize;
		
		for(int i = squareStartRow * squareSize; i < (squareStartRow * squareSize) + squareSize; i++)
		{
			for(int j = squareStartColumn * squareSize; j < (squareStartColumn * squareSize) + squareSize; j++)
			{
				if(matrix[i][j] == value && i != row && j != column)
				{
					return false;
				}
			}
		}
		
		return true;
	}
}
