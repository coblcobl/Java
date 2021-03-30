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
*              Name: SudokuSquare.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for holding the values
*                    and identifiers of each SudokuSquare.  A 
*                    Sudoku Square in this case refers to each of the 81
*                    squares in a Sudoku puzzle.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.model;

public class SudokuSquare
{
	// Declare private variables
    private boolean isLocked = false;
    private int squareValue = 0;
      
    // SudokuSquare Constructor
    public SudokuSquare()
    {
    	//Constructor
	}   // SudokuSquare
     
    // Function to get the squareValue
    public int getSquareValue()
	{
    	return squareValue;	
	}   // getSquareValue
	  	  
	// Function to get the isLocked value 
	public boolean getLocked()
	{
		return isLocked;		
	}   // getLocked
	  
	// Function to set the squareValue
	public void setSquareValue(int value)
	{
		squareValue = value;		
	}   // setSquareValue
	  	
	// Function to set the isLocked value
	public void setLocked(boolean locked)
	{
		isLocked = locked;		
	}   // setLocked	
}   // SudokuSquare
