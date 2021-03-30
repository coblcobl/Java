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
*              Name: TestSudokuSquare.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuSquare class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import src.main.model.SudokuSquare;


public class TestSudokuSquare {
	private static SudokuSquare sudokuSquare;
	
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
		 System.out.println("Start TestSudokuSquare"); 
	     //Generate objects needed for SudokuSquare functionality
		 sudokuSquare = new SudokuSquare();
	 }
	 
	 //test the default constructor
	 @Test
	 public void testSudokuSquare_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuSquare);
	 }
	 
	 //test the setSquareValue method
	 @Test
	 public void testSudokuSquare_setSquareValue(){
	   
	   //Test set square value
	   sudokuSquare.setSquareValue(9);
	   assertEquals(sudokuSquare.getSquareValue(),9);
	 }
	 
	 //test the setSquareLocked method
	 @Test
	 public void testSudokuSquare_setSquareLocked(){
	   
	   //Test set square value
	   sudokuSquare.setLocked(true);
	   assertEquals(sudokuSquare.getLocked(),true);
	 }
	 
	 @AfterClass
	 public static void messageDone()
	 {
		 System.out.println("End TestSudokuSquare"); 
	 }

}  //TestSudokuSquare
