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
*              Name: MessagePanel.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for displaying messages
*      				 to the user in the UI Frame.  This class
*      				 extends the javax.swing.JPanel class and
*      				 implements the java.awt.event.ActionListener interface
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.view;

// Import necessary packages
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;

public class MessagePanel extends JPanel implements ActionListener{
    // Declare private variables and objects
	private static int MESSAGE_DELAY = 10000;
	private static final long serialVersionUID = 1L;
	private SudokuUIFrame sudokuUIFrame;
	private JLabel label;
    private Timer messageTimer;
	
	// MessagePanel Constructor
	public MessagePanel(SudokuUIFrame sudokuUIFrame)
	{	 
		this.sudokuUIFrame = sudokuUIFrame;  // UI Frame
		messageTimer = new Timer(MESSAGE_DELAY, this);  // Create Timer and set delay and handler
		messageTimer.setRepeats(false); //Don't repeat messages
		label = new JLabel();  // Generate a new label object to hold message		 
		add(label);            // Add the label to the MessagePanel		 
	}   // MessagePanel
	
	// Function to set the Text on the label for an Info Message
	public void setMessageText(String string)
	{
		label.setText(string);             // Set the text	
	}   // setInfoText
	
	// Function to set foreground color of the label
	public void setForegroundColor(Color color)
	{
		label.setForeground(color);
	}  // setForegroundColor 

	// Function to show the label text in a timed message that will hide after the delayTime
	public void showTimedMessage()
	{
		// Add MessagePanel to the UI Frame and Pack the frame so it resizes to fit the message
		sudokuUIFrame.addPanel(this);		
		
		// Start the Timer
		// When the timer finishes it will call the actionPerformed method
		messageTimer.restart();
        //The below line can be enabled if you want the messages to be printed to the console
		//System.out.println(label.getText());
	}   // showTimedMessage
	 
	// Function inherited from ActionListener interface that is invoked when the Timer runs out
	public void actionPerformed(ActionEvent e)
	{
		// Remove the panel from the frame and resize the frame		
		sudokuUIFrame.removePanel(this);	
		
	}  // actionPerformed	 
}   // MessagePanel
