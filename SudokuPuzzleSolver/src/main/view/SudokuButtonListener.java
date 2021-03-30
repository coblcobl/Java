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
*              Name: SudokuButtonListener.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for listening for button
*       			 actions from the UI and invoking the appropriate
*       			 method from the sudokuController.  This class
*       			 implements the ActionListener interface
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.view;

// Import necessary packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.main.controller.SudokuController;

public class SudokuButtonListener implements ActionListener{
    private SudokuController sudokuController;
    private String buttonAction;
	
	// ButtonListener Constructor
	public SudokuButtonListener(SudokuController sudokuController)
	{
		this.sudokuController = sudokuController;	
	}  // ButtonListener
	
	// Function inherited from ActionListener interface to capture the buttons being clicked
	@Override
	public void actionPerformed(ActionEvent e)
	{
		buttonAction = e.getActionCommand();  
		
		switch( buttonAction )
		{
			// Determine which button was pressed and invoke the appropriate method
		 	// in the controller
			case "Clear":
				sudokuController.clearGrid();
				break;
			case "Validate":
				sudokuController.buildSudoku();
				break;
			case "Solve":
				sudokuController.solveSudoku();
				break;
			case "Stop":
				sudokuController.stopSolvingSudoku();
				break;
			case "Exit":
				sudokuController.stopSolvingSudoku();
				sudokuController.closeWindow();
				break;
			default:
				break;	
		}  // switch ( buttonAction )	
	}   // actionPerformed	
}  // SudokuButtonListener
