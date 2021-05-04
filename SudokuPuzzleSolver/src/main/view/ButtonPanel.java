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
*              Name: ButtonPanel.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for managing the buttons
*                    in the button panel in the UI Frame.  This class
*      				 extends the javax.swing.JPanel class
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.view;

// Import necessary packages
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel
{
	// Declare private variables and objects
	private static final long serialVersionUID = 1L;
	
	// Declare the Buttons
	private JButton clearButton;
	private JButton solveButton;
	private JButton validateButton;
	private JButton stopButton;
	private JButton exitButton;
	
	// ButtonPanel constructor
	public ButtonPanel() 
	{
		// Instantiate the buttons
		clearButton = new JButton("Clear");
		solveButton = new JButton("Solve");
		validateButton = new JButton("Validate");
		stopButton = new JButton("Stop");
		exitButton = new JButton("Exit");
		
		// Add the buttons to the panel
		add(clearButton);
		add(validateButton);
		add(solveButton);
		add(stopButton);
		add(exitButton);
		
		setEnabledSolveButton(false);   // Disable the solve button at start up
		setEnabledStopButton(false);	// Disable the stop button at start up	
	}   // ButtonPanel
	
	
	// Function to enable or disable clear button
	public void setEnabledClearButton(boolean enabled)
	{
		clearButton.setEnabled(enabled);		
	}   // setEnabledClearButton
	
	// Function to enable or disable solve button
	public void setEnabledSolveButton(boolean enabled)
	{
		solveButton.setEnabled(enabled);		
	}   // setEnabledSolveButton
	
	// Function to enable or disable stop button
	public void setEnabledStopButton(boolean enabled)
	{
		stopButton.setEnabled(enabled);		
	}   // setEnabledStopButton
	
	// Function to enable or disable validate button
	public void setEnabledValidateButton(boolean enabled)
	{
		validateButton.setEnabled(enabled);	
	}   // setEnabledValidateButton
	
	// Function to add a listener to the new button
	public void addClearActionListener(SudokuButtonListener listener)
	{
		clearButton.addActionListener(listener);
	}   // addClearActionListener
	
	// Function to add a listener to the validate button
	public void addValidateActionListener(SudokuButtonListener listener)
	{
		validateButton.addActionListener(listener);
	}   // addValidateActionListener
	
	// Function to add a listener to the solve button
	public void addSolveActionListener(SudokuButtonListener listener)
	{
		solveButton.addActionListener(listener);		
	}   // addSolveActionListener
	
	// Function to add a listener to the exit button
	public void addExitActionListener(SudokuButtonListener listener)
	{
		exitButton.addActionListener(listener);		
	}   // addExitActionListener
	
	// Function to add a listener to the stop button
	public void addStopActionListener(SudokuButtonListener listener)
	{
		stopButton.addActionListener(listener);		
	}   // addStopActionListener	
	
}   // ButtonPanel
