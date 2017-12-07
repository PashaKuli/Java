/* *
* This class represents the dictionary used by the program containing all words, one per line, in alphabetical order (read in from the input file). The words are 
* stored in an ArrayList and this class provides methods to perform queries in the dictionary, such as checking if a String of letters is a prefix for any word in
* the dictionary, and even just checking if a word is contained in the dictionary.
* @author Prianca Padmanabhan
* @version 03/02/2017
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary 
{
	//initalize ArrayList representing our dictionary
	private ArrayList <String> dictionary = new ArrayList();
	/* *
	* Dictionary object constructor that adds each word from the input file to the ArrayList<String> of words, representing the dictionary
	*
	* @param file containing "dictionary" of words to read in 
	*
	* @return nothing, just constructs object
	*
	* @throws IllegalArgumentException if the file name is incorrect or the file is unreadable or does not exist
	*/
	public Dictionary (File f) throws IllegalArgumentException
	{
		try
		{
			//intialize Scanner object to read in file
			Scanner fileInput = new Scanner (f);
			//while there is another line in the file, keep reading
			while (fileInput.hasNextLine())
			{
				//add the line (word) to dictionary, as the file contains each word on a separate line in alphabetic order
				String line = fileInput.nextLine();
				dictionary.add(line);
			}
		}
		//if File is not found, catch exception and throw IllegalArgumentException
		catch (FileNotFoundException e)	
		{
			throw new IllegalArgumentException("The file does not exist or is not readable.");
		}			
	}
	/* *
	* Determines if a String of letters is a word contained in the dictionary
	*
	* @param the String of letters to determine if it exists in the dictionary
	*
	* @return true if word exists in dictionary, returns false otherwise
	*/
	
	public boolean isWord(String str)
	{
		//call private method that actually does binary search computation to determine if str is a word in dictionary
		return binarySearch(str, 0, dictionary.size() - 1);
	}
	/* *
	* This method does the job of actually checking if the word is in the dictionary by using recursive binary search.
	*
	* @param String to be checked, integer  minimum index, integer maximum index (for use in ArrayList)
	*
	* @return true if word is found in dictionary, false otherwise 
	*
	*/
	private boolean binarySearch(String str, int min, int max)
	{
		//base case
		if (min > max)
		{
			return false;
		}
		//define middle index to use for binary search
		int middle = (max + min)/2;
		//if middle element in dictionary equals str, return true
		if (dictionary.get(middle).compareTo(str) == 0)
		{
			return true;
		}	
		//if middle element in dictionary is greater than str (lexicographically), then str exists in bottom half of index of elements in dictionary, so shift the bounds accordingly ie, make the high bound middle-1 so we can proceed to check a smaller range for a match
		else if (dictionary.get(middle).compareTo(str) > 0)
		{
			//recursive call , shift higher bound to only search lower indexes in dictionary
			return binarySearch(str, min, middle-1);
		}	
		//if middle element in dictionary is less than str (lexicographically), then str exists in top half of indexes of elements in dictionary, so shift the lower bound to middle + 1 so that we can narrow the search for a match
		else
		{
			//recursive call, shift lower bound to only search higher indexes in dictionary
			return binarySearch(str, middle + 1, max);
		}
	}
	/* *
	* Determines if a String of letters is a prefix for any word in the dictionary. 
	*
	* @param String of letters to determine if it exists as a prefix for a word in the dictionary
	*
	* @return true if String is found to be a prefix of at least one word in dictionary, false otherwise
	*/	
	public boolean isPrefix(String str)
	{
		//call private method to determine if str is a prefix for at least one word in dictionary
		return prefix(str, 0, dictionary.size()-1);	
	}
	/* *
	* This method uses recursive binary search to determine if the String of letters is a prefix for at least one word in the dictionary.
	*
	* @param String to determine if it is a prefix, integer minimum index, integer maximum index (for use in ArrayList)
	*
	* @return true if String is a prefix of some word in dictionary, false otherwise 
	*/
	private boolean prefix (String str, int min, int max)
	{
		//base case
		if (min > max)
		{
			return false;
		}
		//define middle index of ArrayList
		int middle = (min + max)/2;
		//if middle index of dictionary does not start with str
		if (dictionary.get(middle).startsWith(str) == false)
		{
			//if word at middle index of dictionary is greater than str (lexicographically) shift bounds to only search lower half of indices for matching prefix
			if(dictionary.get(middle).compareTo(str) > 0)
			{
				//recursive call
				return prefix(str, min, middle - 1);
			}
			//if word at middle index of dictionary is less than str (lexicographically) shift bounds to only search top hald of indice for matching prefix
			else
			{
				//recursive call
				return prefix(str, middle + 1, max);
			}
		}
		//if middle index of dictionary starts with str, return true as str is a prefix for the word at middle index
		else
		{
			return true;
		}
	}
}
