/* *
*This class represents the sequence of letters from which words/permutations can be constructed. This class
*validates the word itself and constructs all of the permutations of the word, and all of the permutations of the word also stored in
*the dictionary (by using the Dictionary object).
*
* @author Prianca Padmanabhan
* @version 03/02/2017
*/
import java.util.ArrayList;
import java.util.Collections;

public class Permutations 
{
	//represents input sequence of characters
	private String letters;
	/* *
	* Constructor method that determines if an inputed String contains any non-alphabetic characters.
	*
	* @param String of characters 
	*
	* @throws IllegalArgumentException if inputed String contains non-alphabetic characters
	*/
	Permutations (String letters) throws IllegalArgumentException
	{
		for (int i = 0; i < letters.length(); i++)
		{
			//validates whether the String sequence is alphabetic, throws exception if non-alphabetic characters are in String sequence.
			if (Character.isLetter(letters.charAt(i)) == false)
			{
				throw new IllegalArgumentException("Only letters are allowed.");
			}	
		}		
		this.letters = letters;
	}
	/* *
	* This method determines all the permutations that can be constructed from a String of alphabetic characters, with each
	* permutation stored as an element in an ArrayList object.
	*
	* @param none
	*
	* @return ArrayList containing all permutations of a sequence of letters
	*
	*/
	public ArrayList<String> getAllPermutations()
	{
		//define ArrayList to store all permutations
		ArrayList<String> permutations = new ArrayList<String>();
		//actually call the method to determine permutations
		permutation(letters, permutations);
		//return ArrayList containing each permutation as an element
		return(permutations);
	}
	/* *
	* Uses backtracking to determine all the permutations of a String and stores the permutations in an ArrayList.
	*
	* @param String to make permutations out of, ArrayList to store permutations
	* 
	* @return void
	*/
	private static void permutation(String word, ArrayList<String> perms)
	{
		
		int length = word.length();
		//if word is only one letter, then only permutation is itself
		//base case
		if (length == 1)
		{
			//add word to the ArrayList of permutations
			perms.add(word);
		}
		//if word is more that one character
		else
		{
			for (int i = 0; i < length; i++)
			{
				//define beginning character
				char beginning = word.charAt(i);
				//construct substring without beginning character
				String characters = word.substring(0, i) + word.substring(i+1);
				//temporary ArrayLIist to hold permutations of substring 
				ArrayList<String> temp = new ArrayList<String>();
				//recursive call to shift beginning character each time
				permutation(characters, temp);
				//for each String in ArrayList "temp"
				for (String add : temp)
				{
					//add beginning character to rest of substring and add to ArrayList
					perms.add(beginning + add);
				}
			}	
		}
		
	}
	/* *
	* This method determines all the permutations of a String that are contained in the dictionary. The permutations are stored
	* in an ArrayList that is sorted in alphabetical order.
	*
	* @param Dictionary containing all the words, one per line, in alphabetical order
	*
	* @return ArrayList containing all the permutations of the word that exist in the dictionary
	*
	*/
	public ArrayList<String> getAllWords(Dictionary dictionary)
	{
		//ArrayList to store all permutations that exist in dictionary
		ArrayList<String> matches = new ArrayList<String>();
		//call private method to actually perform the function of searching for matches between permutation and dictionary
		//stores matches in ArrayList
		words("", letters, matches, dictionary);
		//alphabetize ArrayList of matches and return
		Collections.sort(matches);
		return(matches);	
	}
	/* *
	* This method uses backtracking to determine all the permutations of a given String that exist in the dictionary. 
	*
	* @param String of letters to make permutations out of, ArrayList to store the permutations that are in the dictionary, Dictionary that contains alphabetized words 
	* 
	* @return void
	*/
	private static void words(String beginning, String str, ArrayList<String> perms, Dictionary dictionary)
	{
		//if the beginning is a prefix for some word in dictionary
		if (dictionary.isPrefix(beginning))
		{
			
			int n = str.length();
			//base case, length of word is 0
			if (n == 0)
			{
				//make sure String beginning does not already exist in ArrayList of matches
				if (checker(perms, beginning))
				{
					//then if String beginning is a word in the dictionary, then add it to the ArrayList
					if(dictionary.isWord(beginning))
					{
						perms.add(beginning);
					}
				}
			}
			//
			else
			{
				for (int i = 0; i < n; i++)
				{
					//recursive call, if length of string is greater than 0 then add next letter and continue with same process, if added letter doesn't generate a prefix then go down next path
					words(beginning + str.charAt(i), str.substring(0, i)+ str.substring(i+1, n), perms, dictionary);
				}
			}
		}
	}
	/* *
	* This method determines if a word (an element in the ArrayList) exists more than once in the ArrayList. It determines the existence of repeat permutations of a word that may arise from duplicate letters in the input String sequence.
	*
	* @param ArrayList of Strings to check if duplicate words exist in, String to determine if a duplicate of that word is in the ArrayList.
	*
	* @return true if the String exists only once in the ArrayList, return false if there are duplicate words in the ArrayList
	*
	*/
		private static boolean checker(ArrayList<String> input, String string)
		{
			//iterate through each String in ArrayList
			for (int i = 0; i < input.size(); i++)
			{
				//if String string is found at some index in ArrayList, return false to signal that word already exists and shouldn't be added to list
				if (input.get(i).equals(string))
				{
					return false;
				}
			}
			//otherwise return true to signal word doesn't currently exist in ArrayList, so it is safe to add it
			return true;
		}
}	
	
	


		



	
	
	

