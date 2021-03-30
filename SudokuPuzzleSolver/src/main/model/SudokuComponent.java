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
*              Name: SudokuComponent.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for holding the values
*                    and identifiers of each SudokuComponent.  A 
*                    Sudoku Component in this case refers to a 9 square
*                    set including Rows, Columns, or 3x3 Blocks.  They 
*                    are the primary building blocks of a Sudoku Puzzle.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.model;

// Import necessary packages
import java.util.Arrays;

public class SudokuComponent 
{		
	// Declare private variables 
	private static int COMPONENT_SIZE = 9;
	private int[] componentValue = new int[COMPONENT_SIZE];;
	
	// SudokuComponent Constructor
	public SudokuComponent()
	{
	   // Constructor
	}  // SudokuComponent
	  
	// Function to get the componentValue
	public int getComponentValue(int index)
	{
		return componentValue[index];		
	}   // getComponentValue
	  
	// Function set the componentValueId field
	public void setComponentValue(int index, int value)
	{
	   componentValue[index] = value; 	   
	}  // setComponentValue
	
	// Function print the componentValue field
	public void printComponentValue()
	{
		System.out.println(Arrays.toString(componentValue));		
	}   // printComponentValue
		
	// Function to check if the component is valid
	// Any duplicate non-zero values in the array means the component isn't valid
	public boolean isComponentValid()
	{
		// [1,2,3,1,0,0,0,0,0] - invalid because of duplicate 1
		// [1,2,3,4,5,6,7,8,9] - valid no duplicates
		// [1,2,3,4,5,0,0,0,0] - valid no duplicate non-zero values
		
		// Go through the componentValue array
		for( int i = 0; i < componentValue.length; i++ )
		{
			//  Do another iteration and check for duplicates
			for( int j = 0; j < componentValue.length; j++ )
			{
				// If either of the values of the components are 0 at 
				// their respective iterations then it is valid to continue to next index
				if ( componentValue[i] == 0 || componentValue[j] == 0 )
				{
				   continue;
				}  // if either value is 0
				   
				// If either the iterations are at the same index then continue 
				// as the values are guaranteed to be the same, but is not a violation
				if ( i == j ) 
				{
				   continue;
				}  // if same iteration
				
				// If the values match then there is a duplicate, return false
				if( componentValue[i] == componentValue[j] )
				{
					return false;
				}   // if value's match			
			}   // for int j = 0
		}   // for int i = 0	
		return true;  // return true if we made it all the way through	
	}   // isComponentValid	
}   // SudokuComponent
