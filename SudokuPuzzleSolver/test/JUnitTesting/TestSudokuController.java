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
*              Name: TestSudokuController.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuController class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import src.main.controller.SudokuController;
import src.main.model.SudokuGrid;
import src.main.view.SudokuView;
import src.main.model.SudokuSolverWorker;

//Order method runs by their name
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSudokuController {
	private  SudokuView sudokuView;
	private  SudokuController sudokuController;
	private  SudokuGrid sudokuGrid;
	private  Field field;
	private Field myField;
	private boolean myBoolean;
	private SudokuSolverWorker myWorker;
	private int[][] testArrayInt;
	
	 
	 @BeforeClass
	 public static void messageStart()
	 {
		 System.out.println("Start TestSudokuController"); 
	 }
	
	 // Set up objects first
	 @Before
	 public void setUpObjects() throws NoSuchFieldException, IllegalAccessException{

	     //Generate objects needed for SudokuComponent functionality
		 sudokuView = new SudokuView();
		 sudokuController = new SudokuController(sudokuView);
		 
	    // get the controllers sudokuGrid
	    field = sudokuController.getClass().getDeclaredField("sudokuGrid");
	    field.setAccessible(true);
	   
	    sudokuGrid = (SudokuGrid) field.get(sudokuController);

	 }
	 
	 //test the default constructor
	 @Test
	 public void test_a_SudokuController_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuController);
	 }
	 
	 //test the buildSudoku method with a valid puzzle
	 @Test
	 public void test_b_SudokuController_buildSudokuValid() throws NoSuchFieldException, IllegalAccessException{
	   
		// Execute
	   sudokuController.buildSudoku();	
	   
	   // Validate the method using reflection
	   myField = sudokuController.getClass().getDeclaredField("buildSuccessful");
	   myField.setAccessible(true);
	   
	   myBoolean = (boolean) myField.get(sudokuController);

	   assertEquals(myBoolean,true);

	 }
	 
	 //test the buildSudoku method with an invalid puzzle
	 @Test
	 public void test_c_SudokuController_buildSudokuInvalid() throws NoSuchFieldException, IllegalAccessException{
	   // Set up an invalid puzzle
	   sudokuView.setGridSquareValue(0, 0, "1");
	   sudokuView.setGridSquareValue(0, 1, "1");
	   
	   //Execute
	   sudokuController.buildSudoku();	
	   
	   // Validate the method using reflection
	   myField = sudokuController.getClass().getDeclaredField("buildSuccessful");
	   myField.setAccessible(true);

	   myBoolean = (boolean) myField.get(sudokuController);
	   
	   assertEquals(myBoolean,false);

	 }
	 
	 //test the clearGrid method with a valid puzzle
	 @Test
	 public void test_d_SudokuController_clearGrid(){
	   
	   //Set a value on the grid
	   sudokuView.setGridSquareValue(0, 0, "8");	
	   
	   // Validate the method by getting the grid values
	   testArrayInt = sudokuView.getGridSquareValues();
	   
	   // Should be 8
	   assertEquals(testArrayInt[0][0],8);
	   
	   // Execute clearGrid
	   sudokuController.clearGrid();
	  
	   // Validate the method by getting the grid values
	   testArrayInt = sudokuView.getGridSquareValues();

	   // Should be 0 now
	   assertEquals(testArrayInt[0][0],0);

	 }
	 
	 
	 //test the solveSudoku method with a blank puzzle
	 @Test
	 public void test_e_SudokuController_solveSudokuEmpty() throws NoSuchFieldException, IllegalAccessException, 
	           InterruptedException{
        System.out.println("Start solve sudoku empty");
        
		// Execute clearGrid
		sudokuController.clearGrid();
		
		//Execute build grid
		sudokuController.buildSudoku();	
		 
	   //Execute
	   sudokuController.solveSudoku();	
	   
	   // Validate the method using reflection
	   myField = sudokuController.getClass().getDeclaredField("sudokuSolverWorker");
	   myField.setAccessible(true);

	   myWorker = (SudokuSolverWorker) myField.get(sudokuController);
	   // verify a worker was started
	   assertNotNull(myWorker);
	   
	   while(!myWorker.isDone())
	   {
		   try
		   {
			   sudokuController.wait();
		   }
		   catch(Exception e)
		   {
			   
		   }
	   }

	   Thread.sleep(2000);
	   System.out.println("done solve sudoku empty");
	 }
	 
	 
	 //test the stopSolving method with an unsolvable puzzle
	 @Test
	 public void test_f_SudokuController_solveSudokuUnsolvable() throws NoSuchFieldException, IllegalAccessException,
	 																InterruptedException{
		 
		 System.out.println("start solve unsolvable sudoku");
		 
		  //clear grid
		 sudokuController.clearGrid();	
		 //Set up the puzzle Row 1 
		 setValue(0, 0, 5);
		 setValue(0, 1, 1);
		 setValue(0, 2, 6);
		 setValue(0, 3, 8);
		 setValue(0, 4, 4);
		 setValue(0, 5, 9);
		 setValue(0, 6, 7);
		 setValue(0, 7, 3);
		 setValue(0, 8, 2);
		 
		 //Set up the puzzle Row 2
		 setValue(1, 0, 3);
		 setValue(1, 2, 7);
		 setValue(1, 3, 6);
		 setValue(1, 5, 5);
		 
		 //Set up the puzzle Row 3
		 setValue(2, 0, 8);
		 setValue(2, 2, 9);
		 setValue(2, 3, 7);
		 setValue(2, 7, 6);
		 setValue(2, 8, 5);
		 
		 //Set up the puzzle Row 4
		 setValue(3, 0, 1);
		 setValue(3, 1, 3);
		 setValue(3, 2, 5);
		 setValue(3, 4, 6);
		 setValue(3, 6, 9);
		 setValue(3, 8, 7);
		 
		 //Set up the puzzle Row 5
		 setValue(4, 0, 4);
		 setValue(4, 1, 7);
		 setValue(4, 2, 2);
		 setValue(4, 3, 5);
		 setValue(4, 4, 9);
		 setValue(4, 5, 1);
		 setValue(4, 8, 6);
		  
		 //Set up the puzzle Row 6
		 setValue(5, 0, 9);
		 setValue(5, 1, 6);
		 setValue(5, 2, 8);
		 setValue(5, 3, 3);
		 setValue(5, 4, 7);
		 setValue(5, 7, 5);
		 
		 //Set up the puzzle Row 7
		 setValue(6, 0, 2);
		 setValue(6, 1, 5);
		 setValue(6, 2, 3);
		 setValue(6, 3, 1);
		 setValue(6, 4, 8);
		 setValue(6, 5, 6);
		 setValue(6, 7, 7);
		 setValue(6, 8, 4);
		 
		 //Set up the puzzle Row 8
		 setValue(7, 0, 6);
		 setValue(7, 1, 8);
		 setValue(7, 2, 4);
		 setValue(7, 3, 2);
		 setValue(7, 5, 7);
		 setValue(7, 6, 5);
		 
		 //Set up the puzzle Row 9
		 setValue(8, 0, 7);
		 setValue(8, 1, 9);
		 setValue(8, 2, 1);
		 setValue(8, 4, 5);
		 setValue(8, 6, 6);
		 setValue(8, 8, 8);

		 //Execute solve
		 sudokuController.solveSudoku();	
	   
		 // Validate the method using reflection
		 myField = sudokuController.getClass().getDeclaredField("sudokuSolverWorker");
		 myField.setAccessible(true);
	   
		 myWorker = (SudokuSolverWorker) myField.get(sudokuController);
	   
		 // wait for worker to finish
		 while(!myWorker.isDone())
		 {
			 try
			 {
				 sudokuController.wait();
			 }
			 catch(Exception e)
			 {
			   
			 }
		 }
		 System.out.println("worker done unsolvable sudoku");
	   
		 // Validate the puzzle wasn't successful using reflection
		 myField = sudokuController.getClass().getDeclaredField("solveSuccessful");
		 myField.setAccessible(true);

		 myBoolean = (boolean) myField.get(sudokuController);

		 assertEquals(myBoolean,false);	  
	   
		 Thread.sleep(2000);
		 System.out.println("done solve unsolvable sudoku");
	 }
	 
	 
	 //test the stopSolving method with an unsolvable puzzle
	 @Test
	 public void test_g_SudokuController_stopSolvinguMaximum() throws NoSuchFieldException, IllegalAccessException, 
	                                                       InterruptedException{
		 
		 System.out.println("start solve stop maximum sudoku");
		 	 
		 //clear grid
		 sudokuController.clearGrid();	
		 //Set up the puzzle Row 1 		 
		 //Set up the puzzle Row 2
		 setValue(1, 5, 3);
		 setValue(1, 7, 8);
		 setValue(1, 8, 5);
		 
		 //Set up the puzzle Row 3
		 setValue(2, 2, 1);
		 setValue(2, 4, 2);
		 
		 //Set up the puzzle Row 4
		 setValue(3, 3, 5);
		 setValue(3, 5, 7);
		 
		 //Set up the puzzle Row 5
		 setValue(4, 2, 4);
		 setValue(4, 6, 1);
		  
		 //Set up the puzzle Row 6
		 setValue(5, 1, 9);
		 
		 //Set up the puzzle Row 7
		 setValue(6, 0, 5);
		 setValue(6, 7, 7);
		 setValue(6, 8, 3);
		 
		 //Set up the puzzle Row 8
		 setValue(7, 2, 2);
		 setValue(7, 4, 1);
		 
		 //Set up the puzzle Row 9
		 setValue(8, 4, 4);
		 setValue(8, 8, 9);
	   	 
		 //Execute build grid
		 sudokuController.buildSudoku();			 
		 //Execute solve
		 sudokuController.solveSudoku();	
		 //Cancel solve
		 sudokuController.stopSolvingSudoku();	
		 
		 Thread.sleep(1000);
	   
		 // Validate the method using reflection
		 myField = sudokuController.getClass().getDeclaredField("sudokuSolverWorker");
		 myField.setAccessible(true);

		 myWorker = (SudokuSolverWorker) myField.get(sudokuController);
		 // verify a worker was started
		 assertEquals(myWorker.isCancelled(),true);	
		 System.out.println("end solve stop maximum sudoku");

	 }
	 
	 //test the solveSudoku method with a maximum puzzle
	 @Test
	 public void test_h_SudokuController_solveSudokuMaximum() throws NoSuchFieldException, IllegalAccessException, 
	                                                InterruptedException{
		 System.out.println("start solve maximum sudoku");

		 //clear grid
		 sudokuController.clearGrid();	
		 //Set up the puzzle Row 1 
		 
		 //Set up the puzzle Row 2
		 setValue(1, 5, 3);
		 setValue(1, 7, 8);
		 setValue(1, 8, 5);
		 
		 //Set up the puzzle Row 3
		 setValue(2, 2, 1);
		 setValue(2, 4, 2);
		 
		 //Set up the puzzle Row 4
		 setValue(3, 3, 5);
		 setValue(3, 5, 7);
		 
		 //Set up the puzzle Row 5
		 setValue(4, 2, 4);
		 setValue(4, 6, 1);
		  
		 //Set up the puzzle Row 6
		 setValue(5, 1, 9);
		 
		 //Set up the puzzle Row 7
		 setValue(6, 0, 5);
		 setValue(6, 7, 7);
		 setValue(6, 8, 3);
		 
		 //Set up the puzzle Row 8
		 setValue(7, 2, 2);
		 setValue(7, 4, 1);
		 
		 //Set up the puzzle Row 9
		 setValue(8, 4, 4);
		 setValue(8, 8, 9);
		 
		 //Execute
		 sudokuController.solveSudoku();

		 // Validate the method using reflection
		 myField = sudokuController.getClass().getDeclaredField("sudokuSolverWorker");
		 myField.setAccessible(true);

		 myWorker = (SudokuSolverWorker) myField.get(sudokuController);
   
		 // verify a worker was started
		 assertNotNull(myWorker);
		 // wait for first input from solver worker
		 while(!myWorker.isDone())
		 {
			 try
			 {
				 sudokuController.wait();
			 }
			 catch(Exception e)
			 {
				 
			 }
		 }
		 Thread.sleep(2000);
		 System.out.println("end solve maximum sudoku");
	 }
	 
	 //Used to set the value of the grid
	 private void setValue(int row, int col, int value)
	 {		 
		 sudokuGrid.setSudokuRowValue(row, col, value);
		 sudokuGrid.setSudokuColumnValue(col,row,value);
		 sudokuGrid.setSudokuBlockValue(sudokuGrid.getSudokuBlockNum(row, col), 
				                       sudokuGrid.getSudokuBlockIndex(row,col),
				                       value);   
		 sudokuGrid.setSudokuSquareValue(sudokuGrid.getSudokuSquareIndex(row, col), value);
		 sudokuGrid.setSudokuSquareLock(sudokuGrid.getSudokuSquareIndex(row, col), true);
	 }
	 
	
	 // Clean up objects 
	 @After
	 public void cleanUpObjects() throws InterruptedException{
		 
	   //Clean Up objects
	   //Sleep for 15 seconds to Wait for messages to clear
	   System.out.println("Waiting for Messages to Clear");
       Thread.sleep(15000);
	   sudokuController.closeWindow();
	   sudokuView.closeWindow();
		 
	 }
	 
	 @AfterClass
	 public static void messageDone()
	 {
		 System.out.println("End TestSudokuController"); 
	 }

}  //TestSudokuController
