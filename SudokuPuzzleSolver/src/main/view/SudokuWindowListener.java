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
*              Name: SudokuWindowListener.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for managing any window 
*                    events that occur in the UI frame.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package src.main.view;

// Import necessary packages
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import src.main.controller.SudokuController;

public class SudokuWindowListener implements WindowListener {
	private SudokuController sudokuController;
	
	// SudokuWindowListener Constructor
	public SudokuWindowListener(SudokuController sudokuController)
	{	
		this.sudokuController = sudokuController;
	}  // SudokuWindowListener
	
	// Function inherited from WindowListener interface to capture windowClosed event 
	public void windowClosed(WindowEvent e) 
	{		
    	// If the user closes the window, we want to stop any SudokuSolverWorkers that are trying to solve puzzles 
    	sudokuController.stopSolvingSudoku();    	   	
    }  // windowClosed
	
	// Functions inherited from WindowListener interface, must be defined
	public void windowClosing(WindowEvent e) {}
    public void windowOpened(WindowEvent e){} 
    public void windowIconified(WindowEvent e){} 
    public void windowDeiconified(WindowEvent e){} 
    public void windowActivated(WindowEvent e){} 
    public void windowDeactivated(WindowEvent e){}
    
}  // SudokuWindowListener
