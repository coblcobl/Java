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
*              Name: TestSudokuBuilder.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuBuilder class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import src.main.model.SudokuBuilder;
import src.main.model.SudokuGrid;

public class TestSudokuBuilder {
	private static SudokuGrid sudokuGrid;
	private static SudokuBuilder sudokuBuilder;
	private static int[][] gridSquare;
	
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects() throws NoSuchFieldException, IllegalAccessException{
		 System.out.println("Start TestSudokuBuilder"); 
	     //Generate objects needed for SudokuComponent functionality
		 sudokuGrid = new SudokuGrid(9);
		 sudokuBuilder = new SudokuBuilder(sudokuGrid);
		 gridSquare = new int[9][9];
	 }
	 
	 //test the default constructor
	 @Test
	 public void testSudokuController_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuBuilder);
	 }
	 
	 //test the buildSudokuGrid method
	 @Test
	 public void testSudokuController_buildSudokuGrid(){
	    
		// Set the 1st square to have a value of 1 
		gridSquare[0][0] = 1;
	    sudokuBuilder.buildSudokuGrid(gridSquare);
	    
	    // SudokuGrid should be populated with data
	    assertEquals(sudokuGrid.getSudokuRowValue(0, 0),1); 
	    
	   // SudokuGrid should be populated with data
	    assertEquals(sudokuGrid.getSudokuColumnValue(0, 0),1); 
	    
	   // SudokuGrid should be populated with data
	    assertEquals(sudokuGrid.getSudokuBlockValue(0, 0),1); 
	    
	    // SudokuGrid should be populated with data
	    assertEquals(sudokuGrid.getSudokuSquareValue(0),1); 
	 }
	 
	 @AfterClass
	 public static void messageDone()
	 {
		 System.out.println("End TestSudokuBuilder"); 
	 }
	 
	 
}  //TestSudokuBuilder
