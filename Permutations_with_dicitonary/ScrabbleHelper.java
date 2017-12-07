/* *
*This class represents the actual runnable program. It parses the command line arguments to determine the file dictionary name
*and the series of letter from which to determine matching permutations of words in the file dictionary, and displays the results in alphabetical order.
*@author Prianca Padmanabhan
*@version 03/02/2017
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class ScrabbleHelper 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		//if no command line arguments, print error
		if (1 > args.length)
		{
			System.err.println("Error: missing name of the input file");	
		}		
		//if 1st command line argument does not specify correct file name, print error
		else if (!args[0].equals("/Users/priancapadmanabhan/Downloads/dictionary.txt"))
		{
			System.err.println("Error: file " + args[0] + " does not exist.");	
		}	
		//if 1st command line argument is valid
		else
		{
			//if there is not 2nd command line argument, print error
			if(2 > args.length)
			{
				System.err.println("Error: no letters provided, cannot compute any words.");				
			}
			//if 2nd command line argument exists
			else
			{
				//checker variable to validate 2nd command line argument only contains alphabetical characters
				boolean checker = true;
				//iterate through each letter in 2nd command line argument
				for (int i = 0; i < args[1].length(); i++)
				{
					//if a character in 2nd command line argument is not a letter, print error, set checker to false and exit loop
					if (Character.isLetter(args[1].charAt(i)) == false)
					{
						System.err.println("Error: You entered an invalid character; only letters can be accepted.");
						checker =false;
						break;
					}
				}	
				//if all characters in 2nd command line argument are alphabetical 
				if (checker == true)
				{
					//initialize the file based on 1st command line argument
					File file = new File(args[0]);
					//initialize dictionary based on file
					Dictionary dictionary = new Dictionary(file);
					//initialize permutations as lower-case version of second command line argument
					Permutations p = new Permutations(args[1].toLowerCase());	
					//get all matching permutations of the 2nd command line argument that exist in the dictionary, stored in ArrayList 
					p.getAllWords(dictionary);
					//determine size of ArrayList, if greater than 0, then print number of elements/matches
					if (p.getAllWords(dictionary).size() > 0)
					{
						System.out.println("Found " + p.getAllWords(dictionary).size() + " words:");						
					}	
					//if ArrayList size is 0, that means no matches were found so print that
					else
					{
						System.out.println("No words found.");
					}
					//iterate through each element (matching permutation found in dictionary) in the ArrayList and print each on a separate line
					for (int i = 0; i < p.getAllWords(dictionary).size(); i ++)
					{
						System.out.println(p.getAllWords(dictionary).get(i));
					}
				}
			}
		}
	}
}
