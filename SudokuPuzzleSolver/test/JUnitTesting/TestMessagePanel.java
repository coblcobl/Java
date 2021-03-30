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
*              Name: TestMessagePanel.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the MessagePanel class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.lang.reflect.Field;
import javax.swing.JLabel;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import src.main.view.MessagePanel;
import src.main.view.SudokuUIFrame;

import javax.swing.Timer;

public class TestMessagePanel {
	
	 private static MessagePanel messagePanel;
	 private static SudokuUIFrame sudokuUIFrame; 
	 private JLabel myLabel;
	 private Timer myTimer;
	 private ActionEvent myEvent;
	 private Field field;
	 private long when;
	 
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
	   
	   System.out.println("Start TestMessagePanel"); 	 
		 
	   //Generate objects needed for Message Panel functionality
	   sudokuUIFrame = new SudokuUIFrame("Sudoku Puzzle Solver");
	   messagePanel = new MessagePanel(sudokuUIFrame);
	   
	 }
	 
	 //test the default constructor with no input parameters
	 @Test
	 public void testMessagePanel_DefaultConstructor()
	 {
	 	   
	   //Assert not null
	   assertNotNull(messagePanel);

	 }
	 
	 //test the setMessageText method
	 @Test
	 public void testMessagePanel_setMessageText() throws NoSuchFieldException, IllegalAccessException{
 
	   // Invoke setEnabledSolveButton
	   messagePanel.setMessageText("true");
	   
	   // Validate the method using reflection
	   field = messagePanel.getClass().getDeclaredField("label");
	   field.setAccessible(true);
	   
	   myLabel = (JLabel) field.get(messagePanel);
	   assertEquals(myLabel.getText(),"true");

	 }
	 
	 //test the setForegroundColor method
	 @Test
	 public void testMessagePanel_setForegroundColor() throws NoSuchFieldException, IllegalAccessException{
		 
	   // Invoke setEnabledSolveButton
	   messagePanel.setForegroundColor(Color.red);
	   
	   // Validate the method using reflection
	   field = messagePanel.getClass().getDeclaredField("label");
	   field.setAccessible(true);
	   
	   myLabel = (JLabel) field.get(messagePanel);

	   assertEquals(myLabel.getForeground(),Color.red);
	 }
	 
	 //test the showTimedMessage method 
	 @Test
	 public void testMessagePanel_showTimedMessage() throws NoSuchFieldException, 
	                                                        IllegalAccessException, 
	                                                        IllegalMonitorStateException, InterruptedException{
	   // Invoke setEnabledSolveButton   
	   messagePanel.showTimedMessage();
	   	   
	   // Validate the method using reflection
       field = messagePanel.getClass().getDeclaredField("messageTimer");
	   field.setAccessible(true);
	   
	   myTimer = (Timer) field.get(messagePanel);
	   
	   //Timer should be running
	   assertEquals(myTimer.isRunning(),true);	
	   
	   myTimer.stop();
	   myTimer.removeActionListener(messagePanel);
	   
	   //Force Action Performed to be called
	   //Setting this parameter to true will send alert to the console 
	   //each time a swing timer is up
	   Timer.setLogTimers(false);
	   
	   when  = System.currentTimeMillis();
	   myEvent = new ActionEvent(myTimer, ActionEvent.ACTION_PERFORMED,"",when,0); 
	   messagePanel.actionPerformed(myEvent);

	 } 
	 
	 // Clean up objects
	 @AfterClass
	 public static void cleanUpObjects(){
		 
	   //Clean up objects
	   sudokuUIFrame.dispose(); 
	   
	   System.out.println("End TestMessagePanel"); 
	 }
	 
} //TestMessagePanel
