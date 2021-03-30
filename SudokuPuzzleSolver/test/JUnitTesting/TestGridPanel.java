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
*              Name: TestGridPanel.java
*     Creation Date: 10/7/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for running code coverage
*       			 on the GridPanel class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package test.JUnitTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.lang.reflect.Field;

import javax.swing.JFormattedTextField;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import src.main.view.ButtonPanel;
import src.main.view.GridPanel;

public class TestGridPanel {
	 private static ButtonPanel buttonPanel;	
	 private static GridPanel gridPanel;
	 private JFormattedTextField gridSquare[][];
	 private int[][] testArrayInt;
	 private Field field;
	 
	 // Set up objects first
	 @BeforeClass
	 public static void setUpObjects(){
	
	   System.out.println("Start TestGridPanel"); 
	   //Generate objects needed for GridPanel functionality
	   buttonPanel = new ButtonPanel();
	   gridPanel = new GridPanel(9,buttonPanel);
	   
	 }
	 
	 //test the default constructor with no input parameters
	 @Test
	 public void testGridPanel_DefaultConstructor(){
	   
	   //Generate an object and assert that it isn't null
	   assertNotNull(gridPanel);
       
	   //Test blank values in grid
	   gridPanel.setGridSquareValue(1, 1, "");	   
	   assertEquals(gridPanel.getGridSquareValue(1,1),0);
	   
	   //Test null value in grid
	   gridPanel.setGridSquareValue(1, 1, null);
	   assertEquals(gridPanel.getGridSquareValue(1,1),0);
	   
	 }
	 
	
	 //test the clearGrid method
	 @Test
	 public void testGridPanel_clearGrid() throws NoSuchFieldException, IllegalAccessException{	 
       
		 
	   //Test the set grid methods
	   gridPanel.setGridSquareValue(1,1,"1");
	   assertEquals(gridPanel.getGridSquareValue(1,1),1);

	   // Invoke clearGrid
	   gridPanel.clearGrid();   
	   assertEquals(gridPanel.getGridSquareValue(1,1),0);
	   
	   //Test the set grid methods
	   gridPanel.setGridSquareValue(1,1,"1");
	   //Test the set grid methods
	   gridPanel.setGridSquareValue(1,2,"2");
	   testArrayInt = gridPanel.getGridSquareValues();
	   assertEquals(testArrayInt[1][1],1);
	   assertEquals(testArrayInt[1][2],2);
	 }
	 
	 
	 //test the setEnabledGridSquares method
	 @Test
	 public void testGridPanel_setEnabledGridSquares() throws NoSuchFieldException, IllegalAccessException{

	   // Invoke setEnabledSolveButton
	   gridPanel.setEnabledGridSquares(true);
	   
	   // Validate the method using reflection
	   field = gridPanel.getClass().getDeclaredField("gridSquare");
	   field.setAccessible(true);
	   
	   gridSquare = (JFormattedTextField[][]) field.get(gridPanel);

	   assertEquals(gridSquare[1][1].isEnabled(),true);
	 }
	 
	 //test the displayGridSquareHighlighted method
	 @Test
	 public void testGridPanel_displayGridSquareHighlighted() throws NoSuchFieldException, IllegalAccessException{
	   
	   // Invoke setEnabledSolveButton
	   gridPanel.displayGridSquareHighlighted(1,1);
	   
	   // Validate the method using reflection
	   field = gridPanel.getClass().getDeclaredField("gridSquare");
	   field.setAccessible(true);
	   
	   gridSquare = (JFormattedTextField[][]) field.get(gridPanel);

	   assertEquals(gridSquare[1][1].getForeground(),Color.black);
	   assertEquals(gridSquare[1][1].getBackground(),Color.yellow);
	 }
	 
	 //test the displayGridSquareCalculated method
	 @Test
	 public void testGridPanel_displayGridSquareCalculated() throws NoSuchFieldException, IllegalAccessException{
		 
	   // Invoke setEnabledSolveButton
	   gridPanel.displayGridSquareCalculated(1,1);
	   
	   // Validate the method using reflection
	   field = gridPanel.getClass().getDeclaredField("gridSquare");
	   field.setAccessible(true);
	   
	   gridSquare = (JFormattedTextField[][]) field.get(gridPanel);

	   assertEquals(gridSquare[1][1].getForeground(),Color.red);
	 }	 
	 
	 @AfterClass
	 public static void messageDone()
	 {
		 System.out.println("End TestGridPanel"); 
	 }
}  //TestGridPanel
