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
*              Name: SudokuView.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for initiating and managing
*       			 the UI.  It provides public methods for the controller to
*                    access.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.view;

import java.awt.Color;

import src.main.controller.SudokuController;

public class SudokuView {	
    // Declare the attributes used in the SudokuUserInterface
	private static int GRID_SIZE = 9;   //Size of the grid i.e. there are 9 rows, cols, and blocks
	
	// Declare the sudoku controller
	private SudokuController sudokuController;
	
	// Declare the UI Frame that will be used
	private SudokuUIFrame sudokuUIFrame;
	
	// Declare the Panels that will be used in the UI Frame
	private GridPanel gridPanel;  			// Holds UI Grid
	private ButtonPanel buttonPanel;		// Holds UI Buttons
	private MessagePanel infoPanel;			// Holds Panel for info message
	private MessagePanel statusPanel;		// Holds Panel for status message
	private MessagePanel errorPanel;		// Holds Panel for errorPanel message
	
	// Declare Listeners of the UI
	private SudokuButtonListener sudokuButtonListener;  // Listen for UI button actions
	private SudokuWindowListener sudokuWindowListener;  // Listen for UI window actions

	// SudokuView Constructor
	public SudokuView()
	{
		initializeUI();
	} // SudokuView
	
	
	// Function to clear the UI grid
	public void clearGrid()
	{
		gridPanel.clearGrid();	  
	}  // clearGrid
	
	// Function to get the grid panel
	public int[][] getGridSquareValues()
	{
		return gridPanel.getGridSquareValues(); 
	}  // getGridPanel

	// Function to get the size of the grid
	public int getGridSize()
	{
		return GRID_SIZE;
	}  // getGridSize
	
	// Function to display an info message to the UI
	public void showInfoMessage(String message)
	{
		infoPanel.setMessageText(message);
		infoPanel.showTimedMessage();
	}  // showInfoMessage
	
	// Function to display a status message to the UI
	public void showStatusMessage(String message)
	{
		statusPanel.setMessageText(message);
		statusPanel.showTimedMessage();
	}  // showStatusMessage
	
	// Function to display an error message to the UI
	public void showErrorMessage(String message)
	{
		errorPanel.setMessageText(message);
		errorPanel.showTimedMessage();
	} // showErrorMessage

	// Function to enable or disable new button
	public void setEnabledClearButton(boolean enabled)
	{
		buttonPanel.setEnabledClearButton(enabled);		
	}   // setEnabledNewButton
	
	// Function to enable or disable solve button
	public void setEnabledSolveButton(boolean enabled)
	{
		buttonPanel.setEnabledSolveButton(enabled);		
	}   // setEnabledSolveButton
	
	// Function to enable or disable stop button
	public void setEnabledStopButton(boolean enabled)
	{
		buttonPanel.setEnabledStopButton(enabled);		
	}   // setEnabledStopButton
	
	// Function to enable or disable validate button
	public void setEnabledValidateButton(boolean enabled)
	{
		buttonPanel.setEnabledValidateButton(enabled);	
	}   // setEnabledValidateButton
	
	// Function to set a square value on the UI grid
	public void setGridSquareValue(int row, int col, String tempTextValue)
	{
		gridPanel.setGridSquareValue(row, col, tempTextValue);
	}  // setGridSquareValue 
	
	// Function to set the text value of the UI Grid as highlighted to signify they are locked
	public void displayGridSquareHighlighted(int row, int col)
	{
		gridPanel.displayGridSquareHighlighted(row, col);   	
	}  // displayGridSquareHighlighted
	
	// Function to set the text value of the UI Grid as red to signify they were calculated
	public void displayGridSquareCalculated(int row, int col)
	{
		gridPanel.displayGridSquareCalculated(row, col); 	
	}  // displayGridSquareCalculated
	
	// Function to enable or disable the UI grid squares
	public void setEnabledGridSquares(boolean enabled)
	{
		gridPanel.setEnabledGridSquares(enabled);
	}  // setEnabledGridSquares 
	
	// Function to close the window
	public void closeWindow()
	{
		sudokuUIFrame.dispose();
	}  // closeWindow
	
	// Function to add the button action listener to the buttons
	private void addButtonActionListeners(SudokuButtonListener sudokuButtonlistener)
	{	
		buttonPanel.addClearActionListener(sudokuButtonlistener);	
 		buttonPanel.addValidateActionListener(sudokuButtonlistener);	
 		buttonPanel.addSolveActionListener(sudokuButtonlistener);
 		buttonPanel.addStopActionListener(sudokuButtonlistener);
 		buttonPanel.addExitActionListener(sudokuButtonlistener);	
	}  // addButtonActionListeners
	
	// Function to initilialize the UI components
	private void initializeUI()
	{
		// Initializes and Displays everything required for the UI to function
		
		// Initialize the GUI JFrame	
		sudokuUIFrame = new SudokuUIFrame("Sudoku Puzzle Solver");	
		
		// Initialize panels for the buttons and the grid
		buttonPanel = new ButtonPanel();  
		gridPanel = new GridPanel(GRID_SIZE, buttonPanel);  
		infoPanel = new MessagePanel(sudokuUIFrame);
		statusPanel = new MessagePanel(sudokuUIFrame);
		errorPanel = new MessagePanel(sudokuUIFrame);
		
		// Set colors of the message panels
		errorPanel.setForegroundColor(Color.red);

 		// Add panels to frame for display
		sudokuUIFrame.addPanel(gridPanel);
		sudokuUIFrame.addPanel(buttonPanel);
		
		// Show the frame
		sudokuUIFrame.showFrame();  // show the frame 
		
		// Initialize the controller that connects to the back end components
		sudokuController = new SudokuController(this);
		
		// Initialize the listeners
		sudokuButtonListener = new SudokuButtonListener(sudokuController);
		sudokuWindowListener = new SudokuWindowListener(sudokuController);
		
		// Attach the listeners
		addButtonActionListeners(sudokuButtonListener);
		sudokuUIFrame.addWindowListener(sudokuWindowListener);
	}  // InitalizeUI
}  // SudokuView
