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
*              Name: TestSudokuWindowListener.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuWindowListener class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import src.main.controller.SudokuController;
import src.main.view.SudokuUIFrame;
import src.main.view.SudokuWindowListener;
import src.main.view.SudokuView;

public class TestSudokuWindowListener {
	
	private static SudokuController sudokuController;
	private static SudokuWindowListener sudokuWindowListener;
	private static SudokuView sudokuView;
	private SudokuUIFrame sudokuUIFrame;
	private Field field;
	
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
	   System.out.println("Start TestSudokuWindowListener"); 	 
	   //Generate objects needed for SudokuWindowListener functionality
	   sudokuView = new SudokuView();
	   sudokuController = new SudokuController(sudokuView);
	   sudokuWindowListener = new SudokuWindowListener(sudokuController);
	 }
	 
	 //test the default constructor
	 @Test
	 public void testSudokuWindowListener_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuWindowListener);
	 }
	 
	 //test the windowClosed Event
	 @Test
	 public void testSudokuWindowListener_windowClosed() throws NoSuchFieldException, IllegalAccessException{
	   
	   //close the window
	   sudokuView.closeWindow();
	   
	   // Validate the method using reflection
	   field = sudokuView.getClass().getDeclaredField("sudokuUIFrame");
	   field.setAccessible(true);
	   
	   sudokuUIFrame = (SudokuUIFrame) field.get(sudokuView);
	   assertEquals(sudokuUIFrame.isVisible(),false);
	 }
	 
	 // Clean up objects 
	 @AfterClass
	 public static void cleanUpObjects(){
		 
		 //Clean Up objects
		 sudokuView.closeWindow();
		 System.out.println("End TestSudokuWindowListener"); 
	 }

}  //TestSudokuWindowListener
