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
*              Name: TestSudokuButtonListener.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuButtonListener class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;

import src.main.controller.SudokuController;
import src.main.model.SudokuSolverWorker;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import src.main.view.SudokuView;
import src.main.view.ButtonPanel;
import src.main.view.SudokuButtonListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import javax.swing.JButton;

// Order method runs by their name
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSudokuButtonListener {
	
	 private static SudokuView sudokuView;	
	 private static SudokuController sudokuController;	
	 private static SudokuButtonListener sudokuButtonListener;
	 private ActionEvent myEvent;
	 private long when;
	 private Field field;
	 private Field field2;
	 private JButton myButton;
	 private SudokuSolverWorker myWorker;
	 private ButtonPanel myButtonPanel;
	
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
		 
	   System.out.println("Start TestSudokuButtonListener");	 
	   //Generate objects needed for Sudoku Button Listener functionality
	   sudokuView = new SudokuView();
	   sudokuController = new SudokuController(sudokuView);
	   sudokuButtonListener = new SudokuButtonListener(sudokuController);	   
	 }
	 	 
	 //test the default constructor with no input parameters
	 @Test
	 public void test_a_SudokuButtonListener_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuButtonListener);
	 }
	 
	 //test the Clear actionPerformed
	 @Test
	 public void test_b_SudokuButtonListener_ActionPerformedClear() throws NoSuchFieldException, SecurityException,
	                                                               IllegalArgumentException, IllegalAccessException{
     
		 //Disable the validate button
		 sudokuView.setEnabledValidateButton(false);
		 
		 //Manually fire an event
		 when  = System.currentTimeMillis();
		 myEvent = new ActionEvent("button", ActionEvent.ACTION_PERFORMED,"Clear",when,0); 
		 sudokuButtonListener.actionPerformed(myEvent);
		 
		 // Use reflection to validate - get button panel
		 field = sudokuView.getClass().getDeclaredField("buttonPanel");
		 field.setAccessible(true);
	    
		 myButtonPanel = (ButtonPanel) field.get(sudokuView);
		 
		 // get specific button
		 field2 = myButtonPanel.getClass().getDeclaredField("validateButton");
		 field2.setAccessible(true);
		 
		 myButton = (JButton) field2.get(myButtonPanel);
		 //Validate button should be enabled
		 assertEquals(true,myButton.isEnabled());
		 
	 }
	 
	 //test the Validate actionPerformed
	 @Test
	 public void test_c_SudokuButtonListener_ActionPerformedValidate() throws NoSuchFieldException, SecurityException, 
	                                                         IllegalArgumentException, IllegalAccessException{
     
		 //Disable the solve button
		 sudokuView.setEnabledSolveButton(false);
		 
		 //Manually fire an event
		 when  = System.currentTimeMillis();
		 myEvent = new ActionEvent("button", ActionEvent.ACTION_PERFORMED,"Validate",when,0); 
		 sudokuButtonListener.actionPerformed(myEvent);
		 
		 // Use reflection to validate - get button panel
		 field = sudokuView.getClass().getDeclaredField("buttonPanel");
		 field.setAccessible(true);
	    
		 myButtonPanel = (ButtonPanel) field.get(sudokuView);
		 
		 // get specific button
		 field2 = myButtonPanel.getClass().getDeclaredField("solveButton");
		 field2.setAccessible(true);
		 
		 myButton = (JButton) field2.get(myButtonPanel);
		 // Solve button should be enabled
		 assertEquals(true,myButton.isEnabled());
	 }
	 
	 
	 //test the Stop actionPerformed
	 @Test
	 public void test_d_SudokuButtonListener_ActionPerformedStop() throws NoSuchFieldException, SecurityException,
	                                                            IllegalArgumentException, IllegalAccessException{
		 
		 //Manually fire an event code coverage test
		 when  = System.currentTimeMillis();
		 myEvent = new ActionEvent("button", ActionEvent.ACTION_PERFORMED,"Stop",when,0); 
		 sudokuButtonListener.actionPerformed(myEvent);
		 
	 }
	 
	 //test the default actionPerformed
	 @Test
	 public void test_e_SudokuButtonListener_ActionPerformedDefault(){
     
		 //Manually fire an event for code coverage purposes
		 when  = System.currentTimeMillis();
		 myEvent = new ActionEvent("button", ActionEvent.ACTION_PERFORMED,"default",when,0); 
		 sudokuButtonListener.actionPerformed(myEvent);
	 }
	 
	 //test the Solve actionPerformed
	 @Test
	 public void test_f_SudokuButtonListener_ActionPerformedSolve() throws InterruptedException, IllegalArgumentException,
	 								IllegalAccessException, NoSuchFieldException, SecurityException{
     
		 //Manually fire an event
		 when  = System.currentTimeMillis();
		 myEvent = new ActionEvent("button", ActionEvent.ACTION_PERFORMED,"Solve",when,0); 
		 
		 //Fire Solve event
		 sudokuButtonListener.actionPerformed(myEvent);

	      // Validate the method using reflection
	     field = sudokuController.getClass().getDeclaredField("sudokuSolverWorker");
	     field.setAccessible(true);
         // Check to see if the worker has finished
	     myWorker = (SudokuSolverWorker) field.get(sudokuController);
		 assertNotNull(myWorker);
		 
	   // wait until worker is done
		while(!myWorker.isDone())
		{
			try{
	
		      sudokuController.wait();
		    }
		  catch(Exception e) {};
		}
	 }
	 
	 
	 // Test exit button
	 @Test
	 public void test_g_SudokuButtonListener_ActionPerformedExit() throws InterruptedException{
		 
		 //Manually fire the exit event to leave - code coverage
		 when  = System.currentTimeMillis();
		 myEvent = new ActionEvent("button", ActionEvent.ACTION_PERFORMED,"Exit",when,0); 
		 sudokuButtonListener.actionPerformed(myEvent);

	 }
	 
     //Clean up objects
	 @AfterClass
	 public static void cleanUp() throws InterruptedException{
		 Thread.sleep(2000);
		 sudokuController.closeWindow();
		 
		 System.out.println("End TestSudokuButtonListener"); 

	 }
	 
} //TestSudokuButtonListener
