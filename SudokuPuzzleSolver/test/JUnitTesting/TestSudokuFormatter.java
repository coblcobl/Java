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
*              Name: TestSudokuFormatter.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuFormatter class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;

//Import necessary packages
import java.text.NumberFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import src.main.view.SudokuFormatter;

//Order method runs by their name
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSudokuFormatter {

	 private static NumberFormat sudokuFormat;
	 private static SudokuFormatter sudokuFormatter;
	 private Object testObject;
	
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
		System.out.println("Start TestSudokuFormatter");  
		//Generate objects needed for SudokuFormatter functionality
		sudokuFormat = NumberFormat.getInstance();
		sudokuFormatter = new SudokuFormatter(sudokuFormat,1,9);   
	 }
	 
	 //test the default constructor with no input parameters
	 @Test
	 public void test_a_SudokuFormatter_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuFormatter);
	   assertEquals(sudokuFormatter.getMinimum(),1);
	   assertEquals(sudokuFormatter.getMaximum(),9);
	 }
	 
	 //test the string to value function that validates valid input
	 @Test
	 public void test_b_SudokuFormatter_StringToValueValid(){
	   
	   //Test valid entry
	   try
	   {
		   testObject = sudokuFormatter.stringToValue("1");
	   }
	   catch (Exception e)
	   {
		   System.out.println("Error while calling StringtoValue for 1"); 
	   }
	   assertEquals(testObject.toString(),"1");  
	   
	 }
	 
	 //test the string to value function that validates invalid input
	 @Test
	 public void test_c_SudokuFormatter_StringToValueInvalid(){
	   
	   //Test valid entry
	   try
	   {
		   testObject = sudokuFormatter.stringToValue("10");
	   }
	   catch (Exception e)
	   {
		   System.out.println("Error while calling StringtoValue for 10 (this is correct)"); 
	   }
	   assertNull(testObject);  
	   
	 }
	 
	 //test the string to value function that validates null input
	 @Test
	 public void test_d_SudokuFormatter_StringToValueNull(){
	   
	   //Test valid entry
	   try
	   {
		   testObject = sudokuFormatter.stringToValue(null);
	   }
	   catch (Exception e)
	   {
		   System.out.println("Error while calling StringtoValue for null");
	   }
	   assertNull(testObject);  	   
	 }
	 
	 //test the string to value function that validates blank input
	 @Test
	 public void test_e_SudokuFormatter_StringToValueBlank(){
	   
	   //Test valid entry
	   try
	   {
		   testObject = sudokuFormatter.stringToValue("");
	   }
	   catch (Exception e)
	   {
		   System.out.println("Error while calling StringtoValue for blank");
	   }
	   assertNull(testObject);  	   
	 }
	 
	 @AfterClass
	 public static void messageDone()
	 {
		 System.out.println("End TestSudokuFormatter"); 
	 }
	 
}  // TestSudokuFormatter
