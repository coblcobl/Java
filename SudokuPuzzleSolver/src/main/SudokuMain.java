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
*              Name: SudokuMain.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for starting the SudokuView.
*       			 It is the main class of the Sudoku Puzzle Solver.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main;

// Import necessary packages
import javax.swing.SwingUtilities;

import src.main.view.SudokuView;


public class SudokuMain {

	public static void main(String[] args) 
	{
	    SwingUtilities.invokeLater(() -> {
	        // Initiate SudokuView
	    	new SudokuView();
	    });
	}
		    
}  // SudokuMain
