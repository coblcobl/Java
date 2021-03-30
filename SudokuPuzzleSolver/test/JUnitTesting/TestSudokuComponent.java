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
*              Name: TestSudokuComponent.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuComponent class.
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

import src.main.model.SudokuComponent;

public class TestSudokuComponent {
	private static SudokuComponent sudokuComponent;
	private static ByteArrayOutputStream outContent;
	private static PrintStream stdOut;
	
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
		 System.out.println("Start TestSudokuComponent"); 
	     //Generate objects needed for SudokuComponent functionality
		 sudokuComponent = new SudokuComponent();
		 outContent = new ByteArrayOutputStream();
		 stdOut = System.out;  //hold standard out

	 }
	 
	 //test the default constructor
	 @Test
	 public void testSudokuComponent_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuComponent);
	 }
	 
	 //test the setComponentValue and getComponentValue methods
	 @Test
	 public void testSudokuComponent_setComponentValue(){
	   
	   //set the value
	   sudokuComponent.setComponentValue(1, 2);
	   
	   //get the value 
	   assertEquals(sudokuComponent.getComponentValue(1),2);
	 }
	 
	 //test the printComponentValue methods
	 @Test
	 public void testSudokuComponent_printComponentValue(){ 
		 
	   //Redirect standard out
	   System.setOut(new PrintStream(outContent));	 
		 
	   //print the values
	   sudokuComponent.printComponentValue();
	   
	   //check the standard out stream for content 
	   assertNotNull(outContent.toString());
	   
	   //reset the standard out stream
	   System.setOut(stdOut);
	   
	 }
	 
	 //test the isComponentValid method for valid components
	 @Test
	 public void testSudokuComponent_isComponentValid_Valid(){
	   
	   //set the component values up to be valid
	   //1,2,3,4,5,6,7,8,9
	   for(int i = 0; i<9; i++)
	   {
		 sudokuComponent.setComponentValue(i, i);	 
	   }
	   
	   //confirm valid
	   assertEquals(sudokuComponent.isComponentValid(),true);
	   
	 }
	 
	 //test the isComponentValid method for invalid components
	 @Test
	 public void testSudokuComponent_isComponentValid_Invalid(){
	   
	   //set the component values up to be invalid  
		 //1,1,1,1,1,1,1,1,1
	   for(int i = 0; i<9; i++)
	   {
		 sudokuComponent.setComponentValue(i, 1);	 
	   }
	   
	   //confirm valid
	   assertEquals(sudokuComponent.isComponentValid(),false);
	   
	 }
	 
	 @AfterClass
	 public static void messageDone()
	 {
		 System.out.println("End TestSudokuComponent"); 
	 }

}  //TestSudokuComponent
