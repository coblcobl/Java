/*  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*
*                   	 SUDOKU PUZZLE SOLVER
*                   
*  						
*                                                 
*                                                                       
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*   
*              Name: SudokuFormatter.java
*     Creation Date: 7/21/2017
*            Author: Cody Blair
*  
*       Description: This class is responsible for creating a 
*                    NumberFormatter that will prevent invalid entries into
*                    the Sudoku Puzzle UI.  This class extends the
*                    javax.swing.text.NumberFormatter class.
*	Code Review:
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package src.main.view;

// Import necessary packages
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.text.NumberFormatter;

public class SudokuFormatter extends NumberFormatter
{
	// Declare private variables
	private static final long serialVersionUID = 1L;
	
	// SudokuFormatter constructor
	// Generates the NumberFormatter that will limit the UI inputs 
	public SudokuFormatter(NumberFormat numberFormat,
			               int minEntry, 
			               int maxEntry)
	{			
		// Set attributes of the NumberFormatter
		setFormat(numberFormat);       // Set the Format instance
		setValueClass(Integer.class);  // Set the types of inputs to be integer
		setMinimum(minEntry);          // Set the minimum valid entry 
		setMaximum(maxEntry);          // Set the maximum valid entry  
		setAllowsInvalid(false);   	   // Do not allow invalid entries
		setCommitsOnValidEdit(true);   // Commit the value to the field when it is edited	
	}   // SudokuFormatter
	
	
	// Verifies if the string entered in the text field meets the NumberFormatter conventions
	@Override
	public Object stringToValue(String string) throws ParseException
	{
		/*
		 * The standard Integer class NumberFormatter stringToValue function doesn't allow null values.
		 * This means that the Text Fields in the UI won't allow backspace to be entered after
		 * a valid integer is entered.  To accommodate null values and backspaces,
		 * we have to override the stringToValue function of the superclass, in this case 
		 * the InternationalFormatter.  We override to ignore 0 length characters and null values.
		 * If the value isn't null or 0 length, then we call the InternationFormatter's standard
		 * stringToValue function to validate the input as an integer between our min and max values,
		 * minEntry and maxEntry, respectively.
		 */
			
		// If the string that is passed in is null or has a length of 0, do nothing
		if(string == null || string.length() == 0)
		{
			return null;
		}
		// If the string is of a valid length, then call the super class method
		return super.stringToValue(string);		  
	}   // stringToValue	
}   // SudokuFormatter
