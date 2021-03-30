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
*              Name: SudokuBuilder.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for evaluating the user
*       			 input into the UI and invoking SudokuGrid to update
*                    the SudokuComponents.  This class reports
*                    its results to the user via the MessagePanel.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.model;

//Import necessary packages

public class SudokuBuilder 
{
	// Declare private variables and objects
	private SudokuGrid sudokuGrid;
	private int gridSize;
	
	// SudokuBuilder Constructor
	public SudokuBuilder(SudokuGrid sudokuGrid)
	{
		this.sudokuGrid = sudokuGrid;	  // Grid Components
		this.gridSize = sudokuGrid.getGridSize();		
	}   // SudokuBuilder
	
	
	//  Function to set the adjust the SudokuComponents through the SudokuGrid
	public boolean buildSudokuGrid(int gridSquare[][])
	{		
		int blockNum = 0;  	   // used to store the index of the Sudoku Block in the grid
		int gridValue = 0;     // Used to store the grid value on the UI Text Fields
		int blockIndex = 0;    // Used to store the index of square within the Sudoku Block
		int squareIndex = 0;   // Used to store the index of the square (0 - 80)  
		// Go through each text field of the UI Grid
	    // Store the value of the text field in the appropriate Sudoku Components
	    // Go through each row of the UI Grid
	  	for(int row = 0; row < gridSize; row++ )
	  	{
	  		// Go through each row of the UI Grid
	  		for(int col = 0; col < gridSize; col++ )
	  		{  			
	  			// Determine the index of the individual square in the Grid
	  			squareIndex = sudokuGrid.getSudokuSquareIndex(row,col);
  			  
	  			// Determine the index of the square within the Sudoku Block 
	  			blockIndex = sudokuGrid.getSudokuBlockIndex(row,col);
  			  
	  			// Determine the block num in the Grid
	  			blockNum = sudokuGrid.getSudokuBlockNum(row,col);			   	  	  			 
  			  
	  			// Get the value of the Text Field in the UI to store into the Sudoku Components
	  			// If the grid square is blank, set the value to 0, otherwise use the value entered by the user 		  
	  			gridValue = gridSquare[row][col];
	  					
	  			// Populate value of the Sudoku Row 
	  			sudokuGrid.setSudokuRowValue(row,col,gridValue);
  		 	  
	  			// Populate the value of the Sudoku Column
	  			sudokuGrid.setSudokuColumnValue(col,row,gridValue);

	  			// Populate the value of the Sudoku Block 
	  			sudokuGrid.setSudokuBlockValue(blockNum,blockIndex,gridValue);
  		 	  
	  			// Populate the value of the Sudoku Square
	  			sudokuGrid.setSudokuSquareValue((squareIndex),gridValue);
  		 	  
	  			// We must determine if we need to lock the value in a square
	  			// If the value of the GUI grid is greater than 0 then we must lock it
	  			// This tells the solver that it can not adjust that value
	  			if ( gridValue > 0 ) 
	  			{
	  				sudokuGrid.setSudokuSquareLock(squareIndex, true);
	  			}   // if gridValue > 0
	  			else
	  			{
	  				sudokuGrid.setSudokuSquareLock(squareIndex, false);
	  			}  // if gridValue <= 0  			
	  		}  // col loop   		    		  
	  	}  // row loop
  	  
	  	// Print Sudoku Grid For Testing
	  	// SudokuGrid.printSudokuGrid();   // Used for Testing to Validate Component Values after populated 
	  	  
	  	// Once we are finished populating the Sudoku Component values, then validate them 
	  	// Report the results back to the Sudoku Controller	
	  	return ( sudokuGrid.validateSudokuGrid() );

	}   // buildSudokuGrid	
}  //SudokuBuilder
