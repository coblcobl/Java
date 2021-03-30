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
*              Name: TestSudokuSolverWorker.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuSolverWorker class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package test.JUnitTesting;

import src.main.model.SudokuSolverWorker;
import src.main.model.SudokuGrid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

//Order method runs by their name
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSudokuSolverWorker {
	private SudokuGrid sudokuGrid;
	private SudokuSolverWorker sudokuSolverWorker;
	private boolean solveSuccessful;
	private boolean cancelSuccessful;
	
	@BeforeClass
	public static void messageStart()
	{
		System.out.println("Start TestSudokuSolverWorker"); 
	}
	
	 // Set up objects first
	 @Before
	 public void setUpObjects(){
		 
	     //Generate objects needed for SudokuComponent functionality
		 sudokuGrid = new SudokuGrid(9);
		 sudokuSolverWorker = new SudokuSolverWorker(sudokuGrid);
	 }
	 
	 //test the default constructor
	 @Test
	 public void test_b_SudokuSolverWorker_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuSolverWorker);
	 }
	 
	 //test the solveSudokuPuzzle method with a blank puzzle
	 @Test
	 public void test_c_SudokuSolverWorker_solveSudokuPuzzleEmptyPuzzle() throws InterruptedException{
		//Execute solver
		 sudokuSolverWorker = new SudokuSolverWorker(sudokuGrid);
		 sudokuSolverWorker.execute();
		 System.out.println("Waiting for Solver to Finish Solving Empty Puzzle");
		 Thread.sleep(1000);
		 
			// Get whether or not the solver found a solution
     	try
     	{
     		solveSuccessful = sudokuSolverWorker.get();  // Get the result of the Worker
     	}
     	catch (Exception e)
     	{
     		
     	}
		assertEquals(solveSuccessful,true);
		
		sudokuSolverWorker.cancel(true);
		 
	 }
	 
	 //test the solveSudokuPuzzle method and cancel
	 @Test
	 public void test_d_SudokuSolverWorker_solveSudokuPuzzleCancel() throws InterruptedException{
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
		 
		 //Execute solver	 
		 sudokuSolverWorker = new SudokuSolverWorker(sudokuGrid);
		 sudokuSolverWorker.execute();
		 System.out.println("Waiting for Solver to Finish Being Cancelled");
		 sudokuSolverWorker.cancel(true);
         while(!sudokuSolverWorker.isDone())
         {	 
		   Thread.sleep(1000);
         }
		 
		 
		// Get whether or not the solver was cancelled
     	try
     	{
     		cancelSuccessful = sudokuSolverWorker.isCancelled();  // Get the result of the Worker
     	}
     	catch (Exception e)
     	{
     		
     	}
		assertEquals(cancelSuccessful,true);
		 
	 }
	 
	 //test the solveSudokuPuzzle method using a backtracking puzzle
	 @Test
	 public void test_e_SudokuSolverWorker_solveSudokuPuzzleBacktrack() throws InterruptedException{
		 
		 //Set up the puzzle Row 1 
		 setValue(0, 1, 7);
		 setValue(0, 2, 1);
		 setValue(0, 4, 9);
		 setValue(0, 6, 8);

		 
		 //Set up the puzzle Row 2 
		 setValue(1, 3, 3);
		 setValue(1, 5, 6);

		 //Set up the puzzle Row 3
		 setValue(2, 0, 4);
		 setValue(2, 1, 9);
		 setValue(2, 6, 7);
		 setValue(2, 8, 5);

		 
		 //Set up the puzzle Row 4 
		 setValue(3, 1, 1);
		 setValue(3, 3, 9);

		 //Set up the puzzle Row 5
		 setValue(4, 0, 9);
		 setValue(4, 2, 2); 
		 setValue(4, 6, 6);
		 setValue(4, 8, 3); 
		 		 
		 //Set up the puzzle Row 6 
		 setValue(5, 5, 8); 
		 setValue(5, 7, 2); 
		 
		 //Set up the puzzle Row 7
		 setValue(6, 0, 8);
		 setValue(6, 2, 5);
		 setValue(6, 7, 7);
		 setValue(6, 8, 6);
		 
		 //Set up the puzzle Row 8
		 setValue(7, 3, 6);
		 setValue(7, 5, 7);

		 //Set up the puzzle Row 9
		 setValue(8, 2, 7);
		 setValue(8, 4, 4);
		 setValue(8, 6, 3);
		 setValue(8, 7, 7);
		
		 
		 //Execute solver
		 sudokuSolverWorker = new SudokuSolverWorker(sudokuGrid);
		 sudokuSolverWorker.execute();
		 System.out.println("Waiting for Solver to Finish Solving Backtracking puzzle");
		 while(!sudokuSolverWorker.isDone())
		 {
			 Thread.sleep(1000); 
		 }
		 
		 // Get whether or not the solver was cancelled
		 try
		 {
			 solveSuccessful = sudokuSolverWorker.get();  // Get the result of the Worker
		 }
		 catch (Exception e)
		 {
     		
		 }
		 assertEquals(solveSuccessful,true);
		 
	 }
	 
	 
	 //test the solveSudokuPuzzle method using an unsolvable puzzle
	 @Test
	 public void test_f_SudokuSolverWorker_solveSudokuPuzzleUnSolvable() throws InterruptedException{
		 
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
		 
		 
		 //Execute solver
		 sudokuSolverWorker = new SudokuSolverWorker(sudokuGrid);
		 sudokuSolverWorker.execute();
		 System.out.println("Waiting for Solver to Finish Solving Unsolvable puzzle");
		
	    while(!sudokuSolverWorker.isDone())
	    {
		  Thread.sleep(1000);
	    } 
		// Get whether or not the solver was cancelled
     	try
     	{
     		solveSuccessful = sudokuSolverWorker.get();  // Get the result of the Worker
     	}
     	catch (Exception e)
     	{
     		
     	}
		assertEquals(solveSuccessful,false);
		 
	 }
	 
	 
	//test the solveSudokuPuzzle method using an puzzle that exhausts backtracking algorithms
	 @Test
	 public void test_g_SudokuSolverWorker_solveSudokuPuzzleMaximumBacktrack() throws InterruptedException{
		 
		 //Set up the puzzle Row 1 - none
		 
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
		 
		 
		 //Execute solver
		 System.out.println("Waiting for Solver to Finish Solving Maximum BackTracking ");
		 sudokuSolverWorker = new SudokuSolverWorker(sudokuGrid);
		 sudokuSolverWorker.execute();
		 while(!sudokuSolverWorker.isDone())
		 {
			 
		 }

		 // Get whether or not the solver was cancelled
  	  	try
  	  	{
  	  		solveSuccessful = sudokuSolverWorker.get();  // Get the result of the Worker
     	}
     	catch (Exception e)
     	{
     		
     	}
		assertEquals(solveSuccessful,true);
		
	 }
	 
	 //This sets the values of the grid
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
	   sudokuSolverWorker.cancel(true);

	 }
	 
	 @AfterClass
	 public static void messageDone()
	 {
		 System.out.println("End TestSudokuSolverWorker"); 
	 }
	 
	
} //TestSudokuSolverWorker
