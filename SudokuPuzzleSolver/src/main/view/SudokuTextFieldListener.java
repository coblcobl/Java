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
*              Name: SudokuTextFieldListener.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for inserts, updates,
*       			 and removals that occur the Sudoku UI Text Fields.
*       			 This class implements the javax.swing.event.DocumentListener.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.view;

//Import the necessary packages
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SudokuTextFieldListener implements DocumentListener {
	// Declare private objects
	private ButtonPanel buttonPanel;
	
	// SudokuTextFieldListener Constructor
	public SudokuTextFieldListener(ButtonPanel buttonPanel)
	{
       this.buttonPanel = buttonPanel;      
	}   // SudokuTextFieldListener 
		
	// Function inherited from DocumentListener Interface 
	// Function Is called when an update is inserted into the UI Text Fields
    public void insertUpdate(DocumentEvent e) 
	{
	   buttonPanel.setEnabledSolveButton(false); // Disable the solveButton when a field is changed 	   
    }   // insertUpdate

	// Function inherited from DocumentListener Interface 
    // Function is called when a value is removed from the UI Text Fields
    public void removeUpdate(DocumentEvent e) 
    {
    	buttonPanel.setEnabledSolveButton(false); // Disable the solveButton when a field is changed    
    }   // removeUpdate

    // Function inherited from DocumentListener Interface 
    // Function is called when a value is changed in the UI Text Fields
    public void changedUpdate(DocumentEvent e) 
    {
    	buttonPanel.setEnabledSolveButton(false); // Disable the solveButton when a field is changed       
    }   // changedUpdate    
}   // SudokuTextFieldListener
