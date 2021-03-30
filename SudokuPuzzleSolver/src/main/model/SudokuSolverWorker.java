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
*              Name: SudokuSolverWorker.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for solving the Sudoku
*       			 Puzzles given to it.  It solves the Sudoku Puzzles 
*                    in the background and returns a value when it is 
*                    finished. This class extends the javax.swing.SwingWorker 
*                    class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.model;

// Import necessary packages
import java.util.List;

import javax.swing.SwingWorker;

public class SudokuSolverWorker extends SwingWorker<Boolean,String> {
	// Declare private variables and objects
	private static int STATUS_MESSAGE_FREQUENCY = 1000000;
    private SudokuGrid sudokuGrid;
	private int numIterations = 0;
	private int gridSize = 0;
	
	// SudokuSolverWorker Constructor
	public SudokuSolverWorker(SudokuGrid SudokuGrid)
	{
		this.sudokuGrid = SudokuGrid;
		this.gridSize = this.sudokuGrid.getGridSize();		
	}   // SudokuSolverWorker
	
	// Function inherited from SwingWorker Class
	// Function is executed when SudokuSolverWorker.execute() 
	// is invoked
	// Returns a boolean value which can be retrieved via
	// SudokuSolverWorker.get();
	@Override
    protected Boolean doInBackground() 
	{		
		// Solve the Puzzle
		return solveSudokuPuzzle();
		
    }   // doInBackGround
	
	// Function is inherited from SwingWorker Class
	// Function is invoked by SwingWorker Class after 
	// a set amount of time after the publish() method is used
	@Override
    protected void process(List<String> chunks) 
    {
    }   // process

	// Function is inherited from SwingWorker Class
	// Function is invoked by SwingWorker Class when it is finished
	// This can occur either when the SwingWorker is finished or cancelled
    @Override
    protected void done() 
    {
    	// We want to fire the property change event when we are done to update the UI
		firePropertyChange("updateUI", 0, numIterations);		
    }   // done
               
	// Function to solve the Sudoku Puzzle using backtracking
	private boolean solveSudokuPuzzle()
	{	
		// Declare local variables
		int nextValue = 0;      // holds nextvalue for a square to test
		int currentValue = 0;   // hold currentvalue for a square
		int blockNum = 0;       // holds the block num of the pertinent Sudoku Block
		int blockIndex = 0;	    // hold the block index of the pertinent square
		int squareIndex = 0;	// used to hold the index of the pertinent square
		int tempRow = 0;	    // used to hold the row during the backtracking process
		int tempCol = 0;		// used to hold the col during the backtracking process
		int tempSquare = 0;     // used to hold the square during the backtracking process
		boolean backtrack = false;  // used to determine when to backtrack
		boolean foundSolution = true;     // used to determine whether a solution was found
		boolean goBackToFirstRow = false;  // used to force the row/col loops to start over at 0
		
		// Iterate through each row up to the grid size
		rowLoop:
		for( int row = 0; row < gridSize; row++ )
		{								
			// Iterate through each col up to the grid size
			colLoop:
			for( int col = 0; col < gridSize; col++ )
			{
				// Reset variables
				goBackToFirstRow = false; 
				nextValue = 0;            
				
				numIterations++;  // Increment numIterations for UI status updates          
				
				// Check to see if the solver worker has been cancelled from the UI
				if( isCancelled() )
				{
					break rowLoop;  // exit if the UI has requested the worker be cancelled
				}   // If cancelled
										
				// Check to see if we should send a status update, via property change
				checkStatusUpdate();
				
				// Get the squareIndex for this row and col
				squareIndex = sudokuGrid.getSudokuSquareIndex(row, col);
				
				// Get the blockNum and blockIndex that this row and col affect
				blockNum = sudokuGrid.getSudokuBlockNum(row, col);
				blockIndex = sudokuGrid.getSudokuBlockIndex(row, col);
				
				// Get the currentValue of the SudokuSquare for this row and col
				currentValue = sudokuGrid.getSudokuSquareValue(squareIndex);
				
				// Check to see if the Sudoku Square for this row and col is locked
				if( sudokuGrid.getSudokuSquareLocked(squareIndex) )
				{
					continue colLoop;
				}
				
				// Get the next value that can be used in this row and col
				nextValue = getNextValue(row, col, currentValue);
								
				// Set the Sudoku Components for this row and col 
				// to the next value that was determined regardless of its value
				// when we come back to these values in future iterations
				// we will know they are at 0 and we can start with the lowest value  
				sudokuGrid.setSudokuSquareValue(squareIndex, nextValue);
				sudokuGrid.setSudokuRowValue(row,col,nextValue);
				sudokuGrid.setSudokuColumnValue(col,row,nextValue);
				sudokuGrid.setSudokuBlockValue(blockNum,blockIndex,nextValue);
							
				// If we couldn't find a value to use then we must backtrack 
				if( nextValue == 0 )
				{
					backtrack = true;   // Set backtrack flag
					tempRow = row;  	// Set our starting row to the current row
					tempCol = col;  	// Set our starting col to the current col
			 
					while( backtrack )  // Continue to backtrack while backtrack
					{				
						// Continue to backtrack until we find an open square
						// If we are at colToTest 0 then we either need to move up to
						// previous row or stop if we are at row 0
						if( tempCol == 0 )
						{
							// Not at rowToTest 0, we can go back to previous row
							if( tempRow > 0 )
							{
							  tempCol = 8;
							  tempRow = tempRow - 1;
							}
							else
							{
								// We are at rowToTest 0 and colToTest 0 with nowhere to backtrack
								// No Solution Found - Set variables and break out of loop
								tempCol = 0;
								tempRow = 0;
								backtrack = false;
								foundSolution = false;
								break rowLoop;									
							}
						}
						else
						{
							// tempCol is > 0 we can back up 1 column regardless of tempRow
							tempCol = tempCol - 1;
						}
						
						// Set the tempSquare variable to test for locked
						tempSquare = (tempRow * 9) + tempCol;

						// Test the sudoku square to see if it is a locked square
						// If the square is locked continue to backtrack.  
						// If the Square isn't locked, we know which square to go to next
						if( !sudokuGrid.getSudokuSquareLocked(tempSquare) )
						{
							// Square isn't locked, we know which square to go to next
							// Stop the loop
							// Set col = colToTest correctly to iterate to colToTest value
							// Set row = to rowToTest to iterate to rowToTest value
							if( tempCol == 0 )
							{									
								if( tempRow == 0 )
								{
								  col = 0;
								  goBackToFirstRow = true;
								  break colLoop;
								}
								else
								{
								  col = 8;
								  row = tempRow - 1;
								}							
							}
							else
							{
								row = tempRow;
								col = tempCol - 1;
							}		
														
							backtrack = false;  // Turn off backtrack, found the next square					
						}   // backtrack square not locked		
					}   // while backtrack											
				}   // nextValue == 0		
			}  //colLoop
			
		    // Go back to first row, set row = -1, the iteration will bring it back to 0 
		    // and the loop will reset from the first row first col
			if( goBackToFirstRow == true )
			{
				row = -1;
				continue rowLoop; 
			}  // gobacktofirstrow		
		} // rowLoop		
		return foundSolution;		
	}   // solveSudokuPuzzle
	
	// Function gets the next value to use for the row and col passed in
	private int getNextValue(int row, int col, int currentValue)
	{
		
		boolean usedValues[] = new boolean[gridSize];
		
		// Get the used values for this row and col
		usedValues = sudokuGrid.getUsedValues(row, col);
		
		int getNextValue = 0;
		int nextValueLoop = 0;
		
		// Find the next value to put into this square based on the values that are left available 
		nextValueLoop:
		for(nextValueLoop = 1; nextValueLoop <= 9; nextValueLoop++)
		{
			// Only use values higher than the current value of our square 
			if(currentValue > nextValueLoop)
			{
				continue nextValueLoop;
			}
			
			// Check to see if the value is used 
			if(usedValues[nextValueLoop - 1] == true)
			{
				continue nextValueLoop;
			}
			else   
			{
				// Value isn't used, try this value next
				getNextValue = nextValueLoop;
				break nextValueLoop;
			}
		}   // for i loop		
		return getNextValue;		
	}  // getNextValue
	
	// Function to see if we should send a status update
	private void checkStatusUpdate()
	{
		if( numIterations % STATUS_MESSAGE_FREQUENCY == 0 )
		{
			// We want to fire the property change event
			firePropertyChange("updateUI", 0, numIterations);
		}  // if need to update status
	} // checkStatusUpdate		
}  // SudokuSolverWorker
