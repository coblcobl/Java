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
*              Name: TestSudokuUIFrame.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the SudokuUIFrame class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package test.JUnitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import src.main.view.SudokuUIFrame;

public class TestSudokuUIFrame {
	private static SudokuUIFrame sudokuUIFrame;
	private static JPanel myPanel;
	private static JFrame myFrame;
	
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
	   System.out.println("Start TestSudokuUIFrame"); 	 
	   //Generate objects needed for SudokuUIFrame functionality
	   myPanel = new JPanel();
	   myFrame = new JFrame();
	   sudokuUIFrame = new SudokuUIFrame("Sudoku Puzzle Solver");  
	 }
	 
	 //test the default constructor
	 @Test
	 public void testSudokuUIFrame_DefaultConstructor(){
	   
	   //Test not null
	   assertNotNull(sudokuUIFrame);
	   assertEquals(sudokuUIFrame.isResizable(),false);
	 }
	 
	 //test the showFrame method
	 @Test
	 public void testSudokuUIFrame_showFrame(){
	   
	   // show the frame
	   sudokuUIFrame.showFrame();
	   assertEquals(sudokuUIFrame.isVisible(),true);
	 }
	 
	 //test the addpanel and removepanel methods
	 @Test
	 public void testSudokuUIFrame_addandremovePanel(){
	   
	   // add panel to the frame
	   sudokuUIFrame.addPanel(myPanel);
	   // get the window for myPanel
	   myFrame = (JFrame) SwingUtilities.windowForComponent(myPanel);
	   
	   //myFrame and sudokuUIFrame should be the same
	   assertEquals(myFrame,sudokuUIFrame);
	   
	   // remove panel from the frame
	   sudokuUIFrame.removePanel(myPanel);  
	   
	   // get the window for myPanel
	   myFrame = (JFrame) SwingUtilities.windowForComponent(myPanel);
	   
	   //Should be null since it was removed from the frame
	   assertEquals(myFrame,null);
	   
	 }
	 
	// Clean Up objects
	 @AfterClass
	 public static void cleanUpObjects(){
		 
	   //Clean Up objects
	   sudokuUIFrame.dispose();
	   System.out.println("End TestSudokuUIFrame"); 
	 }
	
}  //TestSudokuUIFrame
