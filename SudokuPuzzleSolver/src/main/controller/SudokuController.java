/*  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*
*                   	 SUDOKU PUZZLE SOLVER
*                   
*  						
*                                                 
*                                                                       
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*   
*              Name: SudokuController.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for controlling the back end 
*                    objects that build, validate, and solve the puzzles
*       			 It provides public methods for the view and other
*                    listener objects to access.  It calls public methods
*                    on the view to make UI changes.  This class implements
*                    the PropertChangeListener interface
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.controller;

// Import necessary packages
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingUtilities;

import src.main.view.SudokuView;
import src.main.model.*;

public class SudokuController implements PropertyChangeListener {
	
	//Declare objects used to build, validate and solve the Sudoku Puzzle
	private SudokuView sudokuView;
	private SudokuGrid sudokuGrid; //  Holding component values
	private SudokuBuilder sudokuBuilder; //  Building and validating the Sudoku Puzzle
	private SudokuSolverWorker sudokuSolverWorker;  // Solving the Sudoku Puzzle
	private int gridSize = 0;  // Holds the size of the grid
	private long solverStartTime = 0;  // captures the start time of the solver
	private long solverEndTime = 0; // captures the end time of the solver
	private boolean solveSuccessful = false;
	private boolean buildSuccessful = false;
	
	// SudokuController Constructor 
	public SudokuController(SudokuView sudokuView)
	{	
		this.sudokuView = sudokuView;
		this.gridSize = sudokuView.getGridSize();
	
		// Initialize Sudoku Objects used to store, build and solve puzzles
		sudokuGrid = new SudokuGrid(gridSize);							

	}  // SudokuController

	// Function to initiate the sudokuBuilder to build the backend grid objects
	public void buildSudoku()
	{		
		// Get the grid panel for the Sudoku Builder
		int gridSquare[][] = sudokuView.getGridSquareValues();
		
		sudokuBuilder = new SudokuBuilder(sudokuGrid);
		buildSuccessful = false;
		
		buildSuccessful = sudokuBuilder.buildSudokuGrid(gridSquare);
		
		// Display results back to UI
		if( buildSuccessful )
		{
			sudokuView.setEnabledSolveButton(true);
			showInfoMessage("Puzzle is Valid.  Click 'Solve' button to solve");		
		}
		else
		{
			showErrorMessage("Error: Sudoku Puzzle is Invalid! \n Please correct and click 'Validate' button again.");
		}
		
		// set sudokuBuilder to null to be cleaned up by GC
		sudokuBuilder = null;
	}  // buildSudoku
	
	// Function to initiate the sudokuSolver to solve the puzzle
	public void solveSudoku()
	{
		
		// Disable the clear, validate, and solve buttons, while the solver is working
		sudokuView.setEnabledClearButton(false);
    	sudokuView.setEnabledValidateButton(false);
    	sudokuView.setEnabledSolveButton(false);
    	
    	// Enable the stop button while the solver is working so the user can stop the solver
    	sudokuView.setEnabledStopButton(true);
    	
    	// Disable the UI grid
    	sudokuView.setEnabledGridSquares(false);
    	
    	// Capture the start time of the operation
    	solverStartTime = System.currentTimeMillis();
		
		// Create a new SudokuSolverWorker object and execute it
    	sudokuSolverWorker = new SudokuSolverWorker(sudokuGrid);
    	sudokuSolverWorker.execute();   
    	
    	// Add a property change listener to listen for status updates
    	sudokuSolverWorker.addPropertyChangeListener(this);
		
	}  // solveSudoku
	
    // Function to capture property changes reported by the SudokuSolverworker
	// This method is inherited from the PropertyChangeListener interface
	public void propertyChange(PropertyChangeEvent evt)
	{		
		// Invoke using invokeLater for thread safety 
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				handlePropertyChange(evt);
			}
		});
	}  // propertyChange 
			
	// Function to stop the SudokuSolverWorker's execution if it is currently running
	public void stopSolvingSudoku()
	{	
		// Check to see if the SudokuSolverWorker object has been instantiated 
		if( sudokuSolverWorker!=null )
		{
	    	// Check to see if the SudokuSolverWorker is running
			if( !sudokuSolverWorker.isDone() )
	    	{
	    		sudokuSolverWorker.cancel(true);  //Cancel the execution
	    	}
		}   // if ( SudokuSolverWorker!=null )   	
	}  // stopSolvingSudoku
	
	// Function to close the UI window
    public void closeWindow()
    {
    	sudokuView.closeWindow();
    }  // closeWindow
    
    // Function to clear the UI Grid
    public void clearGrid()
    {
    	sudokuView.clearGrid();
    	
    	// Enable the validate button now that the grid has been cleared and disable the solve
    	sudokuView.setEnabledValidateButton(true);
    	sudokuView.setEnabledSolveButton(false);
    	
       //  Display message to user
       showInfoMessage("Sudoku Puzzle Cleared.");	
    }  // clearGrid
    
	// Function to perform the required UI changes when a propert change occurs
	private void handlePropertyChange(PropertyChangeEvent evt)
	{	
		String propertyName = "";
		String newPropertyValue = "";
		
		propertyName = evt.getPropertyName();
		newPropertyValue = evt.getNewValue().toString();
		
		switch ( propertyName )
		{
			//Handle state change 	
			case "updateUI":
				updateUIGrid();
				updateUIStatus( newPropertyValue );
				break;
			case "state":
				handleStateChange();
				break;
			default:
				break;
		}
	}  // handlePropertyChange
	
	
	// Function to handle a state change of the sudoku solver worker
	private void handleStateChange()
	{
		// check to see if worker is done
		if( sudokuSolverWorker.isDone() == true)
		{			
		    // Check to see if worker was cancelled		
			if( sudokuSolverWorker.isCancelled() )
			{
				showInfoMessage("Solver was cancelled.");
			}  // worker cancelled
			else
			{		
				// Get whether or not the solver found a solution
	        	try
	        	{
	        		solveSuccessful = sudokuSolverWorker.get();  // Get the result of the Worker
	        	}
	        	catch( Exception e )
	        	{
	        		// Catch any errors and display an Error Message to the user
	        		showErrorMessage("An error occurred when trying to get the status of the solver.");	        		
	        	}  // any exceptions
	        	
	        	// Display appropriate message
	        	if ( solveSuccessful )
	        	{
	        		showInfoMessage("Solver found a solution!");
	        	} // solution
	        	else
	        	{
	        		showInfoMessage("Solver did not find a solution!");
	        	}  // no solution
			}  // worker wasn't cancelled
			// Set the buttons appropriately		
			sudokuView.setEnabledClearButton(true);
	    	sudokuView.setEnabledStopButton(false);  
	    	
	    	// Enable the UI grid 
	    	sudokuView.setEnabledGridSquares(true);
		}  // is done		
	}  //handleStateChange
	
	// Function to update the UI grid with current calculated values
	private void updateUIGrid()
    {
    	int squareIndex = 0;
    	String tempTextValue = "";
    	
    	// Go through the rows of the SudokuGrid
    	for( int row = 0; row < gridSize; row++ )
    	{
    		// Go through the columns of the SudokuGrid
    		for ( int col = 0; col < gridSize; col++ )
    		{
    			// Determine the index of the individual square in the Grid
	  			squareIndex = sudokuGrid.getSudokuSquareIndex(row,col);
	  			
	  			// Get the value of the Sudoku Square and convert it to String
	  			// so it can be writen to the TextField
	  			tempTextValue = Integer.toString(sudokuGrid.getSudokuSquareValue(squareIndex));
	  		    
	  			// Update the UI Grid with the current value of the Square
	  			sudokuView.setGridSquareValue(row, col, tempTextValue);
		
    			// Adjust the color scheme of sudoku squares if they are locked or unlocked
    			if( sudokuGrid.getSudokuSquareLocked(squareIndex) )
    			{
    				// Highlight the grid square value to show that it is locked
    				sudokuView.displayGridSquareHighlighted(row, col);		
    			}   // if square is locked
    			else
    			{
    				// Show that the grid square value has been calculated
    				sudokuView.displayGridSquareCalculated(row, col);			
    			}   // if square is not locked		
    		}  // col loop
    	}  // row loop   	   	
    }   // updateUIGrid
		
	// Function to display the status of the SudokuSolverWorker back to the UI grid
    private void updateUIStatus(String iterations)
    {  	
    	// Calculate the elapsed time
    	solverEndTime = System.currentTimeMillis() - solverStartTime; 
    	// Display status to users
    	showStatusMessage(" Time Elapsed: " + solverEndTime + " ms " + " Iterations: " + iterations);
    	
    }  // updateUIStatus
    
    // Function to display an info message back to the UI
	private void showInfoMessage(String message)
	{
		sudokuView.showInfoMessage(message);
	} // showInfoMessage
	
	// Function to display a status message back to the UI
	private void showStatusMessage(String message)
	{
		sudokuView.showStatusMessage(message);
	}  // showStatusMessage
	
	// Function to display an error message back to the UI
	private void showErrorMessage(String message)
	{
		sudokuView.showErrorMessage(message);
	}  // showErrorMessage
} // SudokuController
