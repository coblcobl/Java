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
*              Name: TestButtonPanel.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the ButtonPanel class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import src.main.view.ButtonPanel;
import src.main.controller.SudokuController;
import src.main.view.SudokuView;
import src.main.view.SudokuButtonListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.JButton;

public class TestButtonPanel {
	 
	 private static ButtonPanel buttonPanel;	
	 private static SudokuView sudokuView;	
	 private static SudokuController sudokuController;	
	 private static SudokuButtonListener sudokuButtonListener;	
	 private JButton myButton;
	 private Field field;
	 
	 
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
		
	   System.out.println("Start TestButtonPanel"); 
	   //Generate objects needed for Button Panel functionality
	   sudokuView = new SudokuView();
	   sudokuController = new SudokuController(sudokuView);
	   sudokuButtonListener = new SudokuButtonListener(sudokuController);
	   buttonPanel = new ButtonPanel();
	   
	 }
	 
	 //test the default constructor with no input parameters
	 @Test
	 public void testButtonPanel_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(buttonPanel);

	 }
	 
	 //test the setEnabledClearButton method
	 @Test
	 public void testButtonPanel_setEnabledClearButton() throws NoSuchFieldException, IllegalAccessException{
	   
	   // Invoke setEnabledClearbutton
	   buttonPanel.setEnabledClearButton(true);
	   
	   // Validate the method using reflection
	   field = buttonPanel.getClass().getDeclaredField("clearButton");
	   field.setAccessible(true);
	   
	   myButton = (JButton) field.get(buttonPanel);

	   assertEquals(myButton.isEnabled(),true);
	   
	 }
	 
	 //test the setEnabledSolveButton method
	 @Test
	 public void testButtonPanel_setEnabledSolveButton() throws NoSuchFieldException, IllegalAccessException{

	   // Invoke setEnabledSolveButton
	   buttonPanel.setEnabledSolveButton(true);
	   
	   // Validate the method using reflection
	   field = buttonPanel.getClass().getDeclaredField("solveButton");
	   field.setAccessible(true);
	 
	   myButton = (JButton) field.get(buttonPanel);

	   assertEquals(myButton.isEnabled(),true);

	 }
	 
	 //test the setEnabledStopButton method
	 @Test
	 public void testButtonPanel_setEnabledStopButton() throws NoSuchFieldException, IllegalAccessException{
		 
	   // Invoke setEnabledStopButton
	   buttonPanel.setEnabledStopButton(true);
	   
	   // Validate the method using reflection
	   field = buttonPanel.getClass().getDeclaredField("stopButton");
	   field.setAccessible(true);

	   myButton = (JButton) field.get(buttonPanel);
	   assertEquals(myButton.isEnabled(),true);

	 }
	 
	 //test the setEnabledValidateButton method
	 @Test
	 public void testButtonPanel_setEnabledValidateButton() throws NoSuchFieldException, IllegalAccessException{
	   
	   // Invoke setEnabledValidateButton
	   buttonPanel.setEnabledValidateButton(true);
	   
	   // Validate the method using reflection
	   field = buttonPanel.getClass().getDeclaredField("validateButton");
	   field.setAccessible(true);
	   
	   myButton = (JButton) field.get(buttonPanel);

	   assertEquals(myButton.isEnabled(),true);

	 }
	 
	 
	 //test the addClearActionListener method
	 @Test
	 public void testButtonPanel_addClearActionListener() throws NoSuchFieldException, IllegalAccessException{

	   // Invoke addClearActionListener
	   buttonPanel.addClearActionListener(sudokuButtonListener);
	   
	   // Validate the method using reflection
	   field = buttonPanel.getClass().getDeclaredField("clearButton");
	   field.setAccessible(true);
	   
	   myButton = (JButton) field.get(buttonPanel);
	   ActionListener[] testSudokuListener =  (myButton.getActionListeners());
	   
	   assertEquals(sudokuButtonListener,testSudokuListener[0]);

	 }
	 
	 //test the addValidateActionListener method
	 @Test
	 public void testButtonPanel_addValidateActionListener() throws NoSuchFieldException, IllegalAccessException{

	   // Invoke addValidateActionListener
	   buttonPanel.addValidateActionListener(sudokuButtonListener);
	   
	   // Validate the method using reflection
	   field = buttonPanel.getClass().getDeclaredField("validateButton");
	   field.setAccessible(true);

	   myButton = (JButton) field.get(buttonPanel);
	   ActionListener[] testSudokuListener =  (myButton.getActionListeners());
	   
	   assertEquals(sudokuButtonListener,testSudokuListener[0]);

	 }
	 
	 //test the addSolveActionListener method
	 @Test
	 public void testButtonPanel_addSolveActionListener() throws NoSuchFieldException, IllegalAccessException{

	   // Invoke addSolveActionListener
	   buttonPanel.addSolveActionListener(sudokuButtonListener);
	   
	   // Validate the method using reflection
	   field = buttonPanel.getClass().getDeclaredField("solveButton");
	   field.setAccessible(true);

	   myButton = (JButton) field.get(buttonPanel);
	   ActionListener[] testSudokuListener =  (myButton.getActionListeners());
	   
	   assertEquals(sudokuButtonListener,testSudokuListener[0]);

	 }
	 
	 //test the addExitActionListener method
	 @Test
	 public void testButtonPanel_addExitActionListener() throws NoSuchFieldException, IllegalAccessException{
	   
	   // Invoke addExitActionListener
	   buttonPanel.addExitActionListener(sudokuButtonListener);
	   
	   // Validate the method using reflection
	   field = buttonPanel.getClass().getDeclaredField("exitButton");
	   field.setAccessible(true);
	   
	   myButton = (JButton) field.get(buttonPanel);
	   ActionListener[] testSudokuListener =  (myButton.getActionListeners());
	   
	   assertEquals(sudokuButtonListener,testSudokuListener[0]);

	 }
	 
	 //test the addStopActionListener method
	 @Test
	 public void testButtonPanel_addStopActionListener() throws NoSuchFieldException, IllegalAccessException{

	   // Invoke addStopActionListener
	   buttonPanel.addStopActionListener(sudokuButtonListener);
	   
	   // Validate the method using reflection
	   field = buttonPanel.getClass().getDeclaredField("stopButton");
	   field.setAccessible(true);
	   
	   myButton = (JButton) field.get(buttonPanel);
	   ActionListener[] testSudokuListener =  (myButton.getActionListeners());
	   
	   assertEquals(sudokuButtonListener,testSudokuListener[0]);

	 }
	 
	 // Clean up object
	 @AfterClass
	 public static void cleanUpObjects(){
		 
		//Clean up objects
	   sudokuView.closeWindow();
	   System.out.println("End TestButtonPanel"); 
	 }
	 
}  // TestButtonPanel
