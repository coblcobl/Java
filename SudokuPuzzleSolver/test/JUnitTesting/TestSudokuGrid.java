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
*              Name: TestSudokuGrid.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuGrid class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import src.main.model.SudokuGrid;

public class TestSudokuGrid {
	private static SudokuGrid sudokuGrid;
	private static boolean myBoolean[];
	private static ByteArrayOutputStream outContent;
	private static PrintStream stdOut;
	
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
		 System.out.println("Start TestSudokuGrid");  
	     //Generate objects needed for SudokuComponent functionality
		 sudokuGrid = new SudokuGrid(9);
		 myBoolean = new boolean[9];
		 outContent = new ByteArrayOutputStream();
		 stdOut = System.out;  //hold standard out
	 }
	 
	 //test the default constructor
	 @Test
	 public void testSudokuGrid_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuGrid);
	 }
	 
	 //test the getGridSize method
	 @Test
	 public void testSudokuGrid_getGridSize(){
	   
	   //Test equals
	   assertEquals(sudokuGrid.getGridSize(),9);
	 }
	 
	 //test the setSudokuRowValue and getSudokuRowValue method
	 @Test
	 public void testSudokuGrid_setSudokuRowValue(){
	   
	   // set the row value
	   sudokuGrid.setSudokuRowValue(1, 1, 9);
		 
	   //Test equals
	   assertEquals(sudokuGrid.getSudokuRowValue(1,1),9);
	 }
	 
	 //test the setSudokuColumnValue and getSudokuColumnValue method
	 @Test
	 public void testSudokuGrid_setSudokuColumnValue(){
	   
	   // set the column value
	   sudokuGrid.setSudokuColumnValue(1, 1, 9);
		 
	   //Test equals
	   assertEquals(sudokuGrid.getSudokuColumnValue(1,1),9);
	 }
	 
	 //test the setSudokuBlockValue and getSudokuBlockValue method
	 @Test
	 public void testSudokuGrid_setSudokuBlockValue(){
	   
	   // set the block value
	   sudokuGrid.setSudokuBlockValue(1, 1, 9);
		 
	   //Test equals
	   assertEquals(sudokuGrid.getSudokuBlockValue(1,1),9);
	 }
	 
	 //test the setSudokuSquareValue and getSudokuSquareValue method
	 @Test
	 public void testSudokuGrid_setSudokuSquareValue(){
	   
	   // set the square value
	   sudokuGrid.setSudokuSquareValue(1, 9);
		 
	   //Test equals
	   assertEquals(sudokuGrid.getSudokuSquareValue(1),9);
	 }
	 
	 //test the setSudokuSquareLocked and getSudokuSquareLocked method
	 @Test
	 public void testSudokuGrid_setSudokuSquareLocked(){
	   
	   // set the square locked value
	   sudokuGrid.setSudokuSquareLock(1,true);
		 
	   //Test equals
	   assertEquals(sudokuGrid.getSudokuSquareLocked(1),true);
	 }
	 
	 //test the getSudokuBlockNum method
	 @Test
	 public void testSudokuGrid_getSudokuBlockNum(){
	   
	   //Test equals
	   assertEquals(sudokuGrid.getSudokuBlockNum(1,1),0);	   
	   assertEquals(sudokuGrid.getSudokuBlockNum(8,8),8);
	 }
	 
	 //test the getSudokuBlockIndex method
	 @Test
	 public void testSudokuGrid_getSudokuBlockIndex(){
	   
	   //Test equals
	   assertEquals(sudokuGrid.getSudokuBlockIndex(0,0),0);
	   assertEquals(sudokuGrid.getSudokuBlockIndex(1,1),4);	   
	   assertEquals(sudokuGrid.getSudokuBlockIndex(8,8),8);
	   
	 }
	 
	 //test the getSudokuSquareIndex method
	 @Test
	 public void testSudokuGrid_getSudokuSquareIndex(){
	   
	   //Test equals
	   assertEquals(sudokuGrid.getSudokuSquareIndex(0,0),0);
	   assertEquals(sudokuGrid.getSudokuSquareIndex(8,8),80);	   
	   
	 }
	 
	 //test the getUsedValues Row method
	 @Test
	 public void testSudokuGrid_getUsedValuesRow(){
	   
	   //Set up row component to have a value of 9 populated in row 0 col 0
	   sudokuGrid.setSudokuRowValue(0, 0, 9);
	   
	   myBoolean = sudokuGrid.getUsedValues(0, 0);  
	   //Test equals
	   assertEquals(myBoolean[8],true);
	 }
	 
	 //test the getUsedValues Column method
	 @Test
	 public void testSudokuGrid_getUsedValuesColumn(){
	   
	   //Set up column component to have a value of 9 populated in row 0 col 0	   
	   sudokuGrid.setSudokuColumnValue(0, 0, 9);
	   
	   myBoolean = sudokuGrid.getUsedValues(0, 0);  
	   //Test equals
	   assertEquals(myBoolean[8],true);
	 }
	 
	 //test the getUsedValues Block method
	 @Test
	 public void testSudokuGrid_getUsedValuesBlock(){
	   	   
	   //Set up block component to have a value of 9 populated in row 0 col 0	  
	   sudokuGrid.setSudokuBlockValue(0, 0, 9);
	   
	   myBoolean = sudokuGrid.getUsedValues(0, 0);  
	   //Test equals
	   assertEquals(myBoolean[8],true);
	 }
	 
	 //test the validateSudokuGrid method to be valid
	 @Test
	 public void testSudokuGrid_validateSudokuGridValid(){
	   	   
	   //Set up row component to be valid  
	   sudokuGrid.setSudokuRowValue(0, 0, 1);
	   sudokuGrid.setSudokuRowValue(0, 1, 2);
	   
	   //Test equals
	   assertEquals(sudokuGrid.validateSudokuGrid(),true);
	 }
	 
	 //test the validateSudokuGrid method to be invalid on a row
	 @Test
	 public void testSudokuGrid_validateSudokuGridInValidRow(){
	   	   
	   //Set up row component to be invalid (value 1 in 2 separate squares on same row)
	   sudokuGrid.setSudokuRowValue(0, 0, 1);
	   sudokuGrid.setSudokuRowValue(0, 1, 1);
	   
	   //Test equals
	   assertEquals(sudokuGrid.validateSudokuGrid(),false);
	 }
	 
	 //test the validateSudokuGrid method to be invalid on a column
	 @Test
	 public void testSudokuGrid_validateSudokuGridInValidColumn(){
	   	   
	   //Set up row component to be invalid (value 1 in 2 separate squares on same column)
	   sudokuGrid.setSudokuColumnValue(0, 0, 1);
	   sudokuGrid.setSudokuColumnValue(0, 1, 1);
	   
	   //Test equals
	   assertEquals(sudokuGrid.validateSudokuGrid(),false);
	 }
	 
	 //test the validateSudokuGrid method to be invalid on a block
	 @Test
	 public void testSudokuGrid_validateSudokuGridInValidBlock(){
	   	   
	   //Set up row component to be invalid (value 1 in 2 separate squares on same square)
	   sudokuGrid.setSudokuBlockValue(0, 0, 1);
	   sudokuGrid.setSudokuBlockValue(0, 1, 1);
	   
	   //Test equals
	   assertEquals(sudokuGrid.validateSudokuGrid(),false);
	 }
	 
	 //test the printSudokuGrid method to be invalid on a block
	 @Test
	 public void testSudokuGrid_printSudokuGrid(){
   
	   //Redirect standard out
	   System.setOut(new PrintStream(outContent));	 
		 
	   //print the values
	   sudokuGrid.printSudokuGrid();
	   
	   //check the standard out stream for content 
	   assertNotNull(outContent.toString());
	   
	   //reset the standard out stream
	   System.setOut(stdOut);
	 }
	 
	 @AfterClass
	 public static void messageDone()
	 {
		 System.out.println("End TestSudokuGrid"); 
	 }

}  //TestSudokuGrid
