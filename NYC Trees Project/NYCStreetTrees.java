/* *
*This class contains the main method of the program that opens and reads the file (as long as pathname as command-line argument is correct).
*The user is prompted to enter the name of a species of tree in NYC that they want to learn about until they type "quit". Once the user
*enters a name, the program finds all different species from the data set that contain the entered name as a substring and prints the names.
*It also prints the breakdown of related species by borough, printing the total number of trees in the borough in parentheses and the number of 
*selected species in each borough. It also prints the percentage breakdown.
*@author Prianca Padmanabhan
*version 4/22/17
*/
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class NYCStreetTrees {
	//initialize our TreeList
	static TreeCollection treelist = new TreeCollection();//TreeList
	public static void main(String[] args) throws FileNotFoundException  {
		
		//if no command line argument print error
		if (1 > args.length)
		{	
			System.err.println("Usage Error: the program expects a file name as an argument");				
		}
		//if command line argument entered correctly
		else
		{
			//initialize data fields
			String file_name = null;
			File file = null;
			Scanner fileInput = null;
			try
			{
			//set file name to pathname as command line argument
				file_name = args[0];
				//create new File object and open the file
				file = new File(file_name);
				fileInput = new Scanner(file);
				//skip the first line that contains text
				fileInput.nextLine();
			}
			//handle exceptions
			catch (IllegalArgumentException e)
			{
				System.err.printf("The program expects a file name as an argument.");
			}
			catch(FileNotFoundException e)
			{
				System.err.printf("The file" + file_name + " cannot be opened");
			}	
			//keep reading next line as long as it exists
			while (fileInput.hasNextLine())
			{
				//each tree is represented in one line of data, define the line 
				String line = fileInput.nextLine();
				//define new ArrayList of strings to store data 
				ArrayList<String> trees = new ArrayList<String>();
				//ArrayList contains line from data with each value separated by a comma as an element and white space removed
				trees = splitCSVLine(line);	
				try
				{
					//extract the relevant information about the tree from the Arraylist 
					int id = Integer.parseInt(trees.get(0).trim());
					int diameter = Integer.parseInt(trees.get(3));
					String status = trees.get(6);
					String health = trees.get(7);
					String species =trees.get(9);
					int zipcode = Integer.parseInt(trees.get(25).trim());
					String borough = trees.get(29);
					double x = Double.parseDouble(trees.get(39).trim());
					double y = Double.parseDouble(trees.get(40).trim());
					//define and construct tree based on information extracted from the line of the file from the ArrayList 	
					Tree tree = new Tree(id, diameter, status, health, species, zipcode, borough, x, y);
					//add the tree to the TreeList
					treelist.add(tree);
				}
				//exception handling for invalid tree data 	
				catch(NumberFormatException e)
				{
					System.out.println("Invalid data.");
				}		
				catch(IllegalArgumentException e)
				{
					System.out.println("Invalid tree input data.");
				}
				catch(NullPointerException e)
				{
					System.out.println(e.getMessage());
				}			
			}		
		}			
		//for user input
		Scanner input = new Scanner(System.in);
		//initialize user input
		String response ="";
		//keep going as long as the user doesn't type "quit" in any case
		while (!response.equalsIgnoreCase("quit"))
			{
				System.out.println("Please enter the name of a tree or \"quit\":");
				response = input.nextLine();
				if (!response.equalsIgnoreCase("quit"))
				{
					//initialize new ArrayList Collection of Strings to contain all matching species names based on user response
					Collection<String> match = new ArrayList<String>();
					//assign values to the ArrayList of Strings containing matches in species name to the input
					match = treelist.getMatchingSpecies(response);	
					System.out.println("All matching species:");
					//print each matching species
					for (int i = 0; i < match.size(); i ++)
					{
						System.out.println(((ArrayList<String>) match).get(i));
					}
					//initialize data fields to calculate percentages by borough
					float queensp;
					float nycp;
					float manhattanp;
					float bronxp;
					float brooklynp;
					float statenislandp;
					//as long as matches exist, compile the data 
					if (match.size() != 0)
					{
						//initialize data fields to calculate the sums by borough
						int sum = 0;
						int mansum = 0;
						int bronxsum = 0;
						int brooklynsum = 0;
						int queenssum = 0;
						int statensum = 0;
						//when calculating sums based on species, make sure to include the numbers for every single match contained in the ArrayList, not just what the user inputs
						for (int i = 0; i < match.size(); i++)
						{
							sum += treelist.getCountByTreeSpecies(((ArrayList<String>) match).get(i)); 
							mansum += treelist.getCountByTreeSpeciesBorough(((ArrayList<String>) match).get(i), "Manhattan");
							bronxsum += treelist.getCountByTreeSpeciesBorough(((ArrayList<String>) match).get(i), "Bronx");
							brooklynsum += treelist.getCountByTreeSpeciesBorough(((ArrayList<String>) match).get(i), "Brooklyn");
							queenssum += treelist.getCountByTreeSpeciesBorough(((ArrayList<String>) match).get(i), "Queens");
							statensum += treelist.getCountByTreeSpeciesBorough(((ArrayList<String>) match).get(i), "Staten Island");
						}
						//formatting the sums with commas
						String nsum = String.format("%,d", sum);
						String msum = String.format("%,d", mansum);
						String brsum = String.format("%,d", bronxsum);
						String broosum = String.format("%,d", brooklynsum);
						String qsum = String.format("%,d", queenssum);
						String stsum = String.format("%,d", statensum);
						String totaltrees = String.format("%,d", treelist.getTotalNumberOfTrees());
						String mantrees = String.format("%,d", treelist.getCountByBorough("Manhattan"));
						String btrees = String.format("%,d", treelist.getCountByBorough("Bronx"));
						String brtrees = String.format("%,d", treelist.getCountByBorough("Brooklyn"));
						String qtrees = String.format("%,d", treelist.getCountByBorough("Queens"));
						String strees = String.format("%,d", treelist.getCountByBorough("Staten Island"));
						
						//prevent Arithmetic errors of dividing by 0, set percentage = 0 if denominator is 0, otherwise calculate percentage to 2 decimals
						if (treelist.getCountByBorough("Queens") != 0)
						{
							queensp = (float) (queenssum)/ (float) treelist.getCountByBorough("Queens")*100;
						}
						else
						{
							 queensp = 0;
						}
						if (treelist.getTotalNumberOfTrees() != 0)
						{
							 nycp = (float) (sum)/(float) treelist.getTotalNumberOfTrees()*100;
						}
						else
						{
							 nycp = 0;
						}
						if (treelist.getCountByBorough("Manhattan") != 0)
						{
							 manhattanp = (float)(mansum)/(float) treelist.getCountByBorough("Manhattan")*100;
						}
						else
						{
							 manhattanp = 0;
						}
						if (treelist.getCountByBorough("Bronx") != 0)
						{
							 bronxp = (float)(bronxsum)/(float) treelist.getCountByBorough("Bronx")*100;
						}
						else
						{
							 bronxp = 0;
						}
						if (treelist.getCountByBorough("Brooklyn") != 0)
						{
							 brooklynp = (float)(brooklynsum)/(float) treelist.getCountByBorough("Brooklyn")*100;
						}
						else
						{
							 brooklynp = 0;
						}
						if (treelist.getCountByBorough("Staten Island") != 0)
						{
							 statenislandp = (float)(statensum)/(float) treelist.getCountByBorough("Staten Island")*100;
						}
						else
						{
							 statenislandp = 0;
						}
						//print the information related to the input by each borough
						System.out.println("Popularity in the city:");
						System.out.printf("%15s: %8s(%8s) %.2f%% \n", "NYC", nsum, totaltrees, nycp);
						System.out.printf("%15s: %8s(%8s) %.2f%% \n", "Manhattan",msum, mantrees,manhattanp);
						System.out.printf("%15s: %8s(%8s) %.2f%% \n", "Bronx",brsum, btrees,bronxp);
						System.out.printf("%15s: %8s(%8s) %.2f%% \n", "Brooklyn",broosum, brtrees,brooklynp);
						System.out.printf("%15s: %8s(%8s) %.2f%% \n", "Queens",qsum, qtrees,queensp);
						System.out.printf("%15s: %8s(%8s) %.2f%% \n", "Staten Island",stsum, strees,statenislandp);
						
						
					}
					//if there are no matches to the user input, print error message
					else
					{
						System.out.println("There are no records of "  + response + " on NYC streets.");
					}
				}
			}	
	}
				
	//given method to split up the CSV data 	
	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry= false;
		
		//iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			//handle smart quotes as well as regular quotes 
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') { 
				//change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false; 
				}
				else {
					insideQuotes = true; 
					insideEntry = true; 
				}
			}
			else if (Character.isWhitespace(nextChar)) {
				if  ( insideQuotes || insideEntry ) {
					// add it to the current entry
					nextWord.append( nextChar );
				}
				else  { // skip all spaces between entries 
					continue;
				}
			}
			else if ( nextChar == ',') {
				if (insideQuotes) //comma inside an entry 
					nextWord.append(nextChar);
				else { //end of entry found 
					insideEntry = false; 
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else {
				//add all other characters to the nextWord 
				nextWord.append(nextChar);
				insideEntry = true; 
			}

		}
		// add the last word (assuming not empty)
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) 
		{
			entries.add(nextWord.toString().trim());
		}
		return entries;
	}


}
