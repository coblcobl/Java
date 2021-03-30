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
*              Name: TestSudokuView.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuView class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package test.JUnitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import src.main.view.SudokuView;

public class TestSudokuView {
	private static SudokuView sudokuView;
	private int gridSquareInt[][];
	private Field field;
	private JPanel myPanel;
	private JLabel myLabel;
	private JButton myButton;
	private JFormattedTextField gridSquareText[][];
	
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
	   System.out.println("Start TestSudokuView"); 	 
	   //Generate objects needed for SudokuView functionality
       sudokuView = new SudokuView();
	 }
	 	 
	 //test the default constructor
	 @Test
	 public void testSudokuView_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuView);
	 }
	 
	 //test the setGridSquareValue method
	 @Test
	 public void testSudokuView_setGridSquareValue(){
	   //set a value
	   sudokuView.setGridSquareValue(1,1,"1");	 
	
	   //Retrieve the values
	   gridSquareInt = sudokuView.getGridSquareValues();
	   assertEquals(gridSquareInt[1][1],1);
	 }
	 
	 //test the clearGrid method
	 @Test
	 public void testSudokuView_clearGrid(){
	   
	   //Run the set gridsquare value test to set the value to 1
	   testSudokuView_setGridSquareValue();	 
		
	   //Clear the grid
	   sudokuView.clearGrid();	 
		 
	   //Retrieve the values
	   gridSquareInt = sudokuView.getGridSquareValues();
	   assertEquals(gridSquareInt[1][1],0);
	 }
	 
	 //test the showInfoMessage method
	 @Test
	 public void testSudokuView_showInfoMessage()  throws NoSuchFieldException, IllegalAccessException{	 
	   // Call showInfoMessage
	   sudokuView.showInfoMessage("info");	 
	   
	   // Validate the method using reflection
	   field = sudokuView.getClass().getDeclaredField("infoPanel");
	   field.setAccessible(true);
	   myPanel = (JPanel) field.get(sudokuView);
	   
	   field = myPanel.getClass().getDeclaredField("label");
	   field.setAccessible(true);
	   
	   myLabel = (JLabel) field.get(myPanel);
	   
	   // Label Text should be same as info message
	   assertEquals(myLabel.getText(),"info");
	   
	 }
	 
	 //test the showStatusMessage method
	 @Test
	 public void testSudokuView_showStatusMessage()  throws NoSuchFieldException, IllegalAccessException{	 
	   // Call showStatusMessage
	   sudokuView.showStatusMessage("status");	 
	   
	   // Validate the method using reflection
	   field = sudokuView.getClass().getDeclaredField("statusPanel");
	   field.setAccessible(true);
	   myPanel = (JPanel) field.get(sudokuView);
	   
	   field = myPanel.getClass().getDeclaredField("label");
	   field.setAccessible(true);
	   
	   myLabel = (JLabel) field.get(myPanel);
	   
	   // Label Text should be same as info message
	   assertEquals(myLabel.getText(),"status");
	   
	 }
	 
	 //test the showErrorMessage method
	 @Test
	 public void testSudokuView_showErrorMessage()  throws NoSuchFieldException, IllegalAccessException{	 
	   // Call showErrorMessage
	   sudokuView.showErrorMessage("error");	
	   
	   // Validate the method using reflection
	   field = sudokuView.getClass().getDeclaredField("errorPanel");
	   field.setAccessible(true);
	   myPanel = (JPanel) field.get(sudokuView);
	   
	   field = myPanel.getClass().getDeclaredField("label");
	   field.setAccessible(true);
	   
	   myLabel = (JLabel) field.get(myPanel);
	   
	   // Label Text should be same as info message
	   assertEquals(myLabel.getText(),"error");
	   
	 }
	 
	 
	 //test the setEnabledClearButton method
	 @Test
	 public void testSudokuView_setEnabledClearButton()  throws NoSuchFieldException, IllegalAccessException{	 
	   // Call showErrorMessage
	   sudokuView.setEnabledClearButton(true);	
	   
	   // Validate the method using reflection
	   field = sudokuView.getClass().getDeclaredField("buttonPanel");
	   field.setAccessible(true);
	   myPanel = (JPanel) field.get(sudokuView);
	   
	   field = myPanel.getClass().getDeclaredField("clearButton");
	   field.setAccessible(true);
	   
	   myButton = (JButton) field.get(myPanel);
	   
	   // Button should be enabled
	   assertEquals(myButton.isEnabled(),true);
	   
	 }
	 
	 //test the setEnabledSolveButton method
	 @Test
	 public void testSudokuView_setEnabledSolveButton()  throws NoSuchFieldException, IllegalAccessException{	 
	   // Call showErrorMessage
	   sudokuView.setEnabledSolveButton(true);	
	   
	   // Validate the method using reflection
	   field = sudokuView.getClass().getDeclaredField("buttonPanel");
	   field.setAccessible(true);
	   myPanel = (JPanel) field.get(sudokuView);
	   
	   field = myPanel.getClass().getDeclaredField("solveButton");
	   field.setAccessible(true);
	   
	   myButton = (JButton) field.get(myPanel);
	   
	   // Button should be enabled
	   assertEquals(myButton.isEnabled(),true);
	   
	 }
	 
	 //test the setEnabledStopButton method
	 @Test
	 public void testSudokuView_setEnabledStopButton()  throws NoSuchFieldException, IllegalAccessException{	 
	   // Call showErrorMessage
	   sudokuView.setEnabledStopButton(true);	
	   
	   // Validate the method using reflection
	   field = sudokuView.getClass().getDeclaredField("buttonPanel");
	   field.setAccessible(true);
	   myPanel = (JPanel) field.get(sudokuView);
	   
	   field = myPanel.getClass().getDeclaredField("stopButton");
	   field.setAccessible(true);
	   
	   myButton = (JButton) field.get(myPanel);
	   
	   // Button should be enabled
	   assertEquals(myButton.isEnabled(),true);
	   
	 }
	 
	 //test the setEnabledValidateButton method
	 @Test
	 public void testSudokuView_setEnabledValidateButton()  throws NoSuchFieldException, IllegalAccessException{	 
	   // Call showErrorMessage
	   sudokuView.setEnabledValidateButton(true);	
	   
	   // Validate the method using reflection
	   field = sudokuView.getClass().getDeclaredField("buttonPanel");
	   field.setAccessible(true);
	   myPanel = (JPanel) field.get(sudokuView);
	   
	   field = myPanel.getClass().getDeclaredField("validateButton");
	   field.setAccessible(true);
	   
	   myButton = (JButton) field.get(myPanel);
	   
	   // Button should be enabled
	   assertEquals(myButton.isEnabled(),true);
	   
	 }
	 
	 //test the displayGridSquareHighlighted method
	 @Test
	 public void testSudokuView_displayGridSquareHighlighted()  throws NoSuchFieldException, IllegalAccessException{	 
	   // Call showErrorMessage
	   sudokuView.displayGridSquareHighlighted(1,1);	
	   
	   // Validate the method using reflection
	   field = sudokuView.getClass().getDeclaredField("gridPanel");
	   field.setAccessible(true);
	   myPanel = (JPanel) field.get(sudokuView);
	   
	   field = myPanel.getClass().getDeclaredField("gridSquare");
	   field.setAccessible(true);
	   
	   gridSquareText = (JFormattedTextField[][]) field.get(myPanel);

	   //Fields should have correct background and foreground coloring
	   assertEquals(gridSquareText[1][1].getForeground(),Color.black);
	   assertEquals(gridSquareText[1][1].getBackground(),Color.yellow);
	   
	 }
	 
	 //test the displayGridSquareCalculated method
	 @Test
	 public void testSudokuView_displayGridSquareCalculated()  throws NoSuchFieldException, IllegalAccessException{	 
	   // Call showErrorMessage
	   sudokuView.displayGridSquareCalculated(1,1);	
	   
	   // Validate the method using reflection
	   field = sudokuView.getClass().getDeclaredField("gridPanel");
	   field.setAccessible(true);
	   myPanel = (JPanel) field.get(sudokuView);
	   
	   field = myPanel.getClass().getDeclaredField("gridSquare");
	   field.setAccessible(true);
	   
	   gridSquareText = (JFormattedTextField[][]) field.get(myPanel);

	   //Fields should have correct foreground coloring
	   assertEquals(gridSquareText[1][1].getForeground(),Color.red);
	   
	 }
	 
	 //test the setEnabledGridSquares method
	 @Test
	 public void testSudokuView_setEnabledGridSquares()  throws NoSuchFieldException, IllegalAccessException{	 
	   // Call showErrorMessage
	   sudokuView.setEnabledGridSquares(true);	
	   
	   // Validate the method using reflection
	   field = sudokuView.getClass().getDeclaredField("gridPanel");
	   field.setAccessible(true);
	   myPanel = (JPanel) field.get(sudokuView);
	   
	   field = myPanel.getClass().getDeclaredField("gridSquare");
	   field.setAccessible(true);
	   
	   gridSquareText = (JFormattedTextField[][]) field.get(myPanel);

	   //Fields should have correct foreground coloring
	   assertEquals(gridSquareText[1][1].isEnabled(),true);
	   
	 }
	 
	 
	 // Clean up objects 
	 @AfterClass
	 public static void cleanUpObjects() throws InterruptedException{
		
	   //Wait 10 seconds for messages to clear
	   Thread.sleep(10000);
	   //Clean Up objects
	   sudokuView.closeWindow();
	   System.out.println("End TestSudokuView"); 
	 }

}  //TestSudokuView
