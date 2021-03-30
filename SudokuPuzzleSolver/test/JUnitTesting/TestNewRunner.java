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
*              Name: TestNewRunner.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 test cases.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestNewRunner {
  public static void main(String[] args) throws InterruptedException {
  
	  System.out.println("Start Testing");
	  Result result = JUnitCore.runClasses(TestSudokuSolverWorker.class,		  							      
		  							       TestButtonPanel.class,
		  							       TestGridPanel.class,
		  							       TestMessagePanel.class,
		  							       TestSudokuFormatter.class,
		  							       TestSudokuTextFieldListener.class,
		  							       TestSudokuUIFrame.class,
		  							       TestSudokuView.class,
		  							       TestSudokuSquare.class,
		  							       TestSudokuGrid.class,
		  							       TestSudokuBuilder.class,
		  							       TestSudokuComponent.class,
		  							       TestSudokuWindowListener.class,
		  							       TestSudokuController.class,
			                               TestSudokuButtonListener.class);
	
	  System.out.println("End Testing - Successful:" + result.wasSuccessful());
	  //Get success and failures 
	  for (Failure failure : result.getFailures()) 
	  {
	    System.out.println(failure.toString());
	  }
	    
 } // main

} //TestNewRunner
