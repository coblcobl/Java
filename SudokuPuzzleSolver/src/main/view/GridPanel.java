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
*              Name: GridPanel.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for managing the grid
*                    in the grid panel in the UI Frame.  This class
*      				 extends the javax.swing.JPanel class
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.view;

// Import necessary packages
import java.awt.Color;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridPanel extends JPanel 
{
	// Declare the text fields that will allow the user to enter in values into the Sudoku Puzzle
	private static final long serialVersionUID = 1L;
	private static int SQUARE_MIN_ENTRY = 1;   //The min value allowed to be entered in a square
	private static int SQUARE_MAX_ENTRY = 9;   //The max value allowed to be entered in a square
	private JPanel rowPanel[];
	private JFormattedTextField gridSquare[][];
	private int gridSize;
	private ButtonPanel buttonPanel;
	
	//Declare the number format objects that will be used to control the type of user input
	private NumberFormat sudokuFormat;
	private SudokuFormatter sudokuFormatter;
	private SudokuTextFieldListener sudokuTextFieldListener;
	
	// GridPanel Constructor
	public GridPanel(int gridSize, ButtonPanel buttonPanel)
	{
	   this.gridSize = gridSize;
	   rowPanel = new JPanel[gridSize];      
	   gridSquare = new JFormattedTextField[gridSize][gridSize];  // Text Fields
	   this.buttonPanel = buttonPanel;               // Button Panel
	   // Set layout for the grid panel to be a box layout with components moving down the Y AXIS 
	   setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));  
	   
	   initializeNumberFormatter();
	   initializeTextFieldListeners();
	   initializeTextFields();
	}  // GridPanel
	
	// Function to clear the Grid
	public void clearGrid()
	{
		// Go through all rows
		for( int row = 0; row < gridSize; row++ )
 		{
			// Go through all columns
			for( int col = 0; col < gridSize; col++ )
   		  	{
				// Clear the Text field values in the UI and set back to normal colors
				gridSquare[row][col].setText("");
				gridSquare[row][col].setForeground(Color.black);
				gridSquare[row][col].setBackground(Color.white);
				
   		  	}   // for col = 0 to grid_size 		    		  
 		}  // for row = 0 to grid_size 	
	}  // clearGrid
	
	// Function to return the text value of a square in the UI Grid
	public int getGridSquareValue(int row, int col)
	{
		// Return the integer value of a square
		if (gridSquare[row][col].getText() == "" 
					|| gridSquare[row][col].getText() == null
					|| gridSquare[row][col].getText().length() == 0 )
			return 0;
		else
			return Integer.parseInt(gridSquare[row][col].getText());
	}  // getGridSquareValue 
	
	// Function to build an integer double array of the UI grid 
	public int[][] getGridSquareValues()
	{
		int tempGridSquares[][] = new int[gridSize][gridSize];
		// Go through all textfields and build a new int array
		for( int row = 0; row < gridSize; row++ )
		{
			for (int col = 0; col < gridSize; col++ )
			{
				tempGridSquares[row][col] = getGridSquareValue(row, col);
			}
		}
		return tempGridSquares;
	}  // getGridSquareValues
	
	// Function to set the text value of the UI Grid
	public void setGridSquareValue(int row, int col, String value)
	{
		gridSquare[row][col].setText(value);		
	}  // setGridSquareValue
	
	// Function to set the text value of the UI Grid as highlighted to signify they are locked
	public void displayGridSquareHighlighted(int row, int col)
	{
		gridSquare[row][col].setForeground(Color.black);
		gridSquare[row][col].setBackground(Color.yellow);		
	}  // displayGridSquareHighlighted
	
	// Function to set the text value of the UI Grid as red to signify they were calculated
	public void displayGridSquareCalculated(int row, int col)
	{
		gridSquare[row][col].setForeground(Color.red);	
	}  // displayGridSquareCalculated
	
	// Function to enable or disable the grid square from UI input
	public void setEnabledGridSquares(boolean enabled)
	{
		// Go through all rows
		for( int row = 0; row < gridSize; row++ )
 		{
			// Go through all columns
			for( int col = 0; col < gridSize; col++ )
   		  	{
				// Clear the Text field values in the UI and set back to normal colors
				gridSquare[row][col].setEnabled(enabled);
				
   		  	}   // for col = 0 to grid_size 		    		  
 		}  // for row = 0 to grid_size 	
	}  // setEnabledGridSquares 
	
	// Function to instantiate the NumberFormatter object
	private void initializeNumberFormatter()
	{
		// Instantiate the NumberFormatter to prevent invalid entries into the grid 
		sudokuFormat = NumberFormat.getInstance();
		sudokuFormatter = new SudokuFormatter(sudokuFormat, 
											  SQUARE_MIN_ENTRY, 
											  SQUARE_MAX_ENTRY);	
	}   // initializeNumberFormatter
	
	// Function to instantiate the TextFieldListener object
	private void initializeTextFieldListeners()
	{
		// Instantiate the SudokuTextFieldListener to listen for text field changes
		sudokuTextFieldListener = new SudokuTextFieldListener(buttonPanel);		
	}  // initializeTexFieldListeners
	
	// Function to build the grid and text fields used for UI input
	private void initializeTextFields()
	{
		// Declare variables for function
		int modValue = 0;
		int colWidth = 1;
		
		// Go through an iteration to define each row 
		for( int row = 0; row < gridSize; row++ )
		{
			// Set the value to be used when the mod function occurs
			modValue = row + 1;  
			
			// For each row in the grid, generate a new rowPanel to place the text fields into 
			rowPanel[row] = new JPanel();
			
			// If this is the 3rd iteration of the row loop then add a blank space
			// This will segregate the spacing of the Sudoku blocks for user friendliness
			if ( modValue % 3 == 0 )
			{
				rowPanel[row].setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
			}   // if modValue % 3
			
			// Repeat an iteration for the columns in each row
			for( int col = 0; col < gridSize; col++ )
			{
				// Set the value to be used when the mod function occurs
				modValue = col + 1;  
				
				// Generate a JFormattedTextField for each Grid Square and assign the NumberFormatter
				gridSquare[row][col] = new JFormattedTextField(sudokuFormatter);
				
				// Set the width of the column of the Text Field
				gridSquare[row][col].setColumns(colWidth);
				
				// Add a document listener to see when the grid squares are updated
				gridSquare[row][col].getDocument().addDocumentListener(sudokuTextFieldListener);
				
				// Add the Text field to the row panel 
				rowPanel[row].add(gridSquare[row][col]);
				
				// If this is the 3rd iteration of the col loop then add a blank space into the panel.
				// This will segregate the spacing of the Sudoku blocks for user friendliness
				if( modValue % 3 == 0 )
				{
					rowPanel[row].add(new JLabel(" "));
				}   // if modValue % 3
				
			} // for int col = 0 to GRID_SIZE
		
			// At the end of each row in the grid, add the rowPanel to the entire gridPanel
		    add(rowPanel[row]);	
		}  // for int row = 0 to GRID_SIZE		
	}   // initializeTextFields
}  // GridPanel
