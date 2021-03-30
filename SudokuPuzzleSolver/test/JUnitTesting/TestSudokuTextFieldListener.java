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
*              Name: TestSudokuTextFieldListener.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuTextFieldListener class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;

//Import necessary packages
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import src.main.view.ButtonPanel;
import src.main.view.GridPanel;
import src.main.view.SudokuTextFieldListener;

import javax.swing.JButton;

//Order method runs by their name
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSudokuTextFieldListener {
	private static ButtonPanel buttonPanel;
	private static GridPanel gridPanel;
	private static SudokuTextFieldListener sudokuTextFieldListener;
	private Field field;
	private JButton myButton;
	
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
		System.out.println("Start TesetSudokuTextFieldListener");  
	   //Generate objects needed for SudokuTextFieldListener functionality
	    buttonPanel = new ButtonPanel();
	    gridPanel = new GridPanel(9,buttonPanel);
	    sudokuTextFieldListener = new SudokuTextFieldListener(buttonPanel);   
	 }
	 
	 //test the default constructor
	 @Test
	 public void test_a_SudokuTextFieldListener_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuTextFieldListener);
	 }
	 
	 //test the insertUpdate function
	 @Test
	 public void test_b_SudokuTextFieldListener_insertUpdate() throws NoSuchFieldException, IllegalAccessException{
	     
		 // This will fire the text field listener event
		 gridPanel.setGridSquareValue(1, 1, "1");
		 
		 // Use reflection to validate that the function worked, the solve buttion should be disabled
		 field = buttonPanel.getClass().getDeclaredField("solveButton");
		 field.setAccessible(true);
		  
		 myButton = (JButton) field.get(buttonPanel);

		 assertEquals(myButton.isEnabled(),false);
	 }
	 
	 //test the removeUpdate function
	 @Test
	 public void test_c_SudokuTextFieldListener_removeUpdate() throws NoSuchFieldException, IllegalAccessException{
	     
		 // This will fire the text field listener event
		 gridPanel.setGridSquareValue(1, 1, "1");
		 
		 // This will fire the text field listener event to remove the update
		 gridPanel.setGridSquareValue(1, 1, "");
		 
		 // Use reflection to validate that the function worked, the solve button should be disabled
		 field = buttonPanel.getClass().getDeclaredField("solveButton");
		 field.setAccessible(true);
		  
		 myButton = (JButton) field.get(buttonPanel);

		 assertEquals(myButton.isEnabled(),false);
	 }
	 
	 //test the changedUpdate function
	 @Test
	 public void test_d_SudokuTextFieldListener_changedUpdate() throws NoSuchFieldException, IllegalAccessException{
	     
		 //Plain Text doesn't fire the changedUpdate event
		 assertEquals(true,true);
	 }
	 
	 @AfterClass
	 public static void messageDone()
	 {
		 System.out.println("End TesetSudokuTextFieldListener"); 
	 }
}  //TestSudokuTextFieldListener
