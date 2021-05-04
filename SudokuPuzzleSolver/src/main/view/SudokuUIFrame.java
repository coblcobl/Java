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
*              Name: SudokuUIFrame.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for managing the Main UI Frame
*                    This class extends the javax.swing.JFrame class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.view;

// Import the necessary packages 
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SudokuUIFrame extends JFrame 
{
	// Declare the attributes used in the SudokuUserInterface
	private static final long serialVersionUID = 1L;   //Required for JFrame extension
	
	// SudokuUIFrame Constructor
	public SudokuUIFrame(String appTitle)
	{
		// Set the attribute of the Frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  		  // Set close operation
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));  // Set Layout for frame 
        setTitle(appTitle);    									      // Set the title of frame 	
		setResizable(false);										  // Set resize option of frame 			
	}   // SudokuUIFrame
		
	// Function to show the Frame
	public void showFrame()
	{
		pack();			  // Pack the frame to be the same size as the data created in it		
	    setVisible(true);   // Show the frame
	}  // showFrame
	
	// Function to add a JPanel to the frame
	public void addPanel(JPanel panel)
	{
		add(panel);
		pack();
	}  // addPanel
	
	// Function to remove a JPanel to the frame
	public void removePanel(JPanel panel)
	{
		remove(panel);
		pack();		
	}  // removePanel
}  // SudokuUIFrame
