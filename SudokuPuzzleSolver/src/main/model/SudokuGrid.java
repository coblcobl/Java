/*  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*
*                   	 SUDOKU PUZZLE SOLVER
*                   
*  						NDSU MSE Final Project
*                                                 
*                                                                       
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*   
*              Name: SudokuGrid.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for populating,
*                    updating, and validating the SudokuComponents.  
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.model;

// Import necessary packages
import java.util.Arrays;

public class SudokuGrid 
{
	// Declare private variables and objects 
	private int gridSize = 0;
	private int numSquares = 0;
	private SudokuComponent sudokuRow[];
	private SudokuComponent sudokuColumn[];
	private SudokuComponent sudokuBlock[];
	private SudokuSquare sudokuSquare[];
	private boolean usedValues[];  // used to hold which values are used of each component

	// SudokuGrid Constructor
	public SudokuGrid(int gridSize)
	{
		this.gridSize = gridSize;
		this.numSquares = gridSize * gridSize;
		buildComponents();	
	}   // SudokuGrid
	
	
	// Function to get the gridSize
	public int getGridSize()
	{
		return gridSize;		
	}  // getGridSize
		
	// Function to get the SudokuRow Component Values
	public int getSudokuRowValue(int componentId, int componentIndex)
	{
		return sudokuRow[componentId].getComponentValue(componentIndex);		
	}   // getSudokuRowValue
	
	// Function to get the SudokuColumn Component Values
	public int getSudokuColumnValue(int componentId, int componentIndex)
	{
		return sudokuColumn[componentId].getComponentValue(componentIndex);		
	}   // getSudokuColumnValue
	
	// Function to get the SudokuBlock Component Values  
	public int getSudokuBlockValue(int componentId, int componentIndex)
	{
		return sudokuBlock[componentId].getComponentValue(componentIndex);		
	}   // getSudokuBlockValue
	
	// Function to get the SudokuSquare Component Value
	public int getSudokuSquareValue(int componentId)
	{
		return sudokuSquare[componentId].getSquareValue();	
	}  // getSudokuSquareValue
	
	// Function to get the SudokuSquare isLocked value
	public boolean getSudokuSquareLocked(int componentId)
	{
		return sudokuSquare[componentId].getLocked();		
	}  // getSudokuSquareLocked
		
	// Function to get the Block num that a square belongs to
	public int getSudokuBlockNum(int row, int col)
	{		
		// Example:
		// Below are individual square indexes, this function returns which block the row and 
		// column are a part of  
		//         C0 C1 C2  ...
		// Row 0 - 0  1  2 | 3  4  5 | 6  7  8
		//  ...	   ...
		// Row 0 Col 0 above would belong to block 0, Row 0 Col 6 would belong to block 2, etc.
		//
		// block 0 | block 1 | block 2
		// ---------------------------
		// block 3 | block 4 | block 5
		// ---------------------------
		// block 6 | block 7 | block 8
		
		return ( col / 3 ) + row - ( row % 3 ); 				
	}   // getSudokuBlockNum
	
	// Function to get the index of a square within a block that a row and column would have
	public int getSudokuBlockIndex(int row, int col)
	{				 
		// Example:
	    // Below is a Block 
		// This function returns which index within the block a row and column have
		//
		//		 C  C     C
		//       0  1	  4
		//       BLOCK 0  BLOCK 1
		// Row 0 0  1  2  0  1  2
		// Row 1 3  4  5  3  4  5 
		//       6  7  8  6  7  8  ...
		//       BLOCK 3
		// Row 3 0  1  2 
		//       3  4  5 
		//       6  7  8    ...
		//        ...
        int rowModValue = 0;
        int colModValue = 0;
		
		
		// Hold the modded values
		rowModValue = row % 3;
		colModValue = col % 3;
		
		// Every third row, we simply return the column number (row 0, 3, 6)
		if(rowModValue == 0 )
		{
			return colModValue;
			
		}  // row 0, 3, 6
		else if(rowModValue == 1)
		{
			// If we are in rows 1, 4, or 7, return 3, 4, 5 respectively
			return colModValue + 3;
			
		}  // row 1, 4, 7
		else
		{
			// If we are in rows 1, 4, or 7, return 6, 7, 8 respectively
			return colModValue + 6;
			
		}  // row 2, 5, 8
	}   // getSudokuBlockIndex	
	
	// Function to return the Sudoku Square Index based on the row and col
	public int getSudokuSquareIndex(int row, int col)
	{
		// Below are the square indexes of a Sudoku Grid
		// 0  1  2  3  4  5  6  7  8  
		// 10 11 12 13 14 15 16 17 18
		// ...		
		return (row * gridSize) + col;	
	}   // getSudokuSquareIndex
	
	// Function to get the values that are used in all the components
	// that have a correlation with the row and col passed in
	public boolean[] getUsedValues(int row, int col)
	{	
		// record which of the 9 digits are used in a boolean array
		// usedValues[0] == true means that 1 is used
		// usedValues[1] == false means that 1 is not used  ...
		
		int blockNum = 0;
		int usedValue = 0;
		
		// Reset array
		Arrays.fill(usedValues, false);
		
		// Find all the values for the row, col and block that are currently used
		for(int i = 0; i < gridSize; i++)
		{
			// Get the used values of the SudokuRow for the row passed in
			usedValue = sudokuRow[row].getComponentValue(i);
			if( usedValue > 0 )
			{
				// Mark that index in the tempUsedValues as true
				usedValues[usedValue - 1] = true;
			}
			
			// Get the used values of the SudokuColumn for the col passed in
			usedValue = sudokuColumn[col].getComponentValue(i);
			if( usedValue > 0 )
			{
				// Mark that index in the tempUsedValues as true
				usedValues[usedValue - 1] = true;
			}
			
			// Get the block num associated with the row and col
			blockNum = getSudokuBlockNum(row,col);
			
			// Get the used values of the SudokuBlock for the calculated blockNum passed in
			usedValue = sudokuBlock[blockNum].getComponentValue(i);
			if( usedValue > 0 )
			{
				// Mark that index in the tempUsedValues as true
				usedValues[usedValue - 1] = true;
			}
		}   // for grid iterations
		return usedValues;
	}   // getUsedValues
		
	// Function to set the SudokuRow Component Values
	public void setSudokuRowValue(int componentId, int componentIndex, int value)
	{
		sudokuRow[componentId].setComponentValue(componentIndex, value);		
	}   // setSudokuRowValue
	
	// Function to set the SudokuColumn Component Values
	public void setSudokuColumnValue(int componentId, int componentIndex, int value)
	{
		sudokuColumn[componentId].setComponentValue(componentIndex, value);	
	}   // setSudokuColumnValue
	
	// Function to set the SudoukuBlockValue Component Values
	public void setSudokuBlockValue(int componentId, int componentIndex, int value)
	{
		sudokuBlock[componentId].setComponentValue(componentIndex, value);	
	}  // setSudokuBlockValue
	
	// Function to set the SudokuSquare value
	public void setSudokuSquareValue(int componentId, int value)
	{
		sudokuSquare[componentId].setSquareValue(value);	
	}   // setSudokuSquareValue
	
	// Function to set the SudokuSquare isLocked value
	public void setSudokuSquareLock(int componentId,boolean locked)
	{
		sudokuSquare[componentId].setLocked(locked);	
	}   // setSudokuSquareLock
	
	//  Function to validate the SudokuComponents
	public boolean validateSudokuGrid()
	{
		boolean isValid = false;
				
		// Iterate through the grid
		for( int i = 0; i < gridSize; i++ )
		{
			// Test the SudokuRow Component and return if not valid
			isValid = sudokuRow[i].isComponentValid();
			if ( !isValid ) 
			{
				return isValid; 				
			} // if SudokuRow not valid

			// Test the SudokuColumn Component and return if not valid
			isValid = sudokuColumn[i].isComponentValid();
			if ( !isValid ) 
			{
				return isValid;
				
			}  // if SudokuColumn not valid

			// Test the SudokuBlock Component and return if not valid
			isValid = sudokuBlock[i].isComponentValid();
			if ( !isValid ) 
			{
				return isValid; 
			}  // if SudokuBlock not valid
		}  // for grid iterations
		
		return isValid;		
	}   // validateSudokuGrid
	
	// Function to print the Sudoku Grid to console
	public void printSudokuGrid()
	{
		// Print Row Components
		System.out.println("Rows");
		for( int row = 0; row < gridSize; row++ )
		{
			sudokuRow[row].printComponentValue();
		}   // for row
		
		// Print Columns Components
		System.out.println("Columns");
		for( int col = 0; col < gridSize; col++ )
		{
			sudokuColumn[col].printComponentValue();
		}   // for col
		
		// Print Block Components
		System.out.println("Blocks");
		for( int block = 0; block < gridSize; block++ )
		{
			sudokuBlock[block].printComponentValue();
		}   // for block
		
		// Print Squares
		System.out.println("Squares");	
		for( int square = 0; square < numSquares; square++ )
		{
			System.out.println(Integer.toString(sudokuSquare[square].getSquareValue()));
		}   // square
		
	}   // printSudokuGrid
	
	// Function to build Sudoku Components required for the Grid	
	private void buildComponents()
	{
		// Instantiate the Sudoku Components
		sudokuRow = new SudokuComponent[gridSize];
		sudokuColumn = new SudokuComponent[gridSize];
		sudokuBlock = new SudokuComponent[gridSize];
		sudokuSquare = new SudokuSquare[numSquares];
		usedValues = new boolean[gridSize];
				
		// Instantiate each 9 square area component (row, column, 3x3 block)
		for( int i = 0; i < gridSize; i++)
		{
			sudokuRow[i] = new SudokuComponent();
			sudokuColumn[i] = new SudokuComponent();
			sudokuBlock[i] = new SudokuComponent();			
		}   // for  i = 0
		
		// Instantiate each square component
		for( int j = 0; j < numSquares; j++ )
		{
			sudokuSquare[j] = new SudokuSquare();
		} // for j = 0		
	}   // buildComponents
} //SudokuGrid
