/* *
* This class represents an ArrayList of Tree objects. The ArrayList<Tree> is creating by calling the super constructor.
* This class has methods to get the number of trees in the ArrayList, count the number of trees by species, count the number
* of trees by borough, count the number of trees by species and borough, and determine if a name entered as a parameter "matches"
* or is a substring of a species of a tree in the ArrayList and returns an ArrayList of all the Strings of species that match the parameter.
* @author Prianca Padmanabhan
*/
import java.util.ArrayList;

public class TreeList extends ArrayList<Tree>
{
	//static ArrayList<Tree> list;
	/* *
	*Constructs a TreeList by calling the super constructor.
	*@param none
	*@return none
	*/
	public TreeList()
	{
		//call super constructor to construct TreeList
		super();
	}
	/* *
	*Returns the total number of tree objects in the ArrayList
	*@param none
	*@return the size of the ArrayList (number of trees) as an integer
	*/
	public int getTotalNumberOfTrees()
	{
		return this.size();
	}
	/* *
	*Counts the number of occurrences (ie number of trees) of a specified species name in the ArrayList.
	*@param String speciesName to search for
	*@return integer number of trees of specified species
	*/
	public int getCountByTreeSpecies(String speciesName)
	{
		//initialize count that keeps track of each match in species 
		int count = 0;
		//iterate through each tree in the TreeList to check for a match
		for (int i = 0; i < this.size(); i++)
		{
			if (this.get(i).getSpeciesName().trim().equalsIgnoreCase(speciesName))
			{
				count ++;
			}
		}
		return count;
	}
	/* *
	*Counts number of trees by borough
	*@param String borough name 
	*@return integer number of trees in specified borough
	*/
	public int getCountByBorough(String boroName)
	{
		//initialize counter that keeps track of each match in borough name
		int count = 0;
		for (int i = 0; i < this.size(); i++)
		{
			//if tree in treelist matches parameter borough name, count
			if (this.get(i).getBoroName().equalsIgnoreCase(boroName))
			{
				count ++;
			}
		}
		return count;
				
	}
	/* *
	*Counts number of trees by species and borough/
	*@param String species name, String borough name
	*@return integer number of trees of certain species in specified borough
	*/
	public int getCountByTreeSpeciesBorough(String speciesName, String boroName)
	{
		int count = 0;
		//iterate through each tree in TreeList, only count as a match if both species name and borough name match parameters
		for (int i = 0; i < this.size(); i ++)
		{
			if (this.get(i).getSpeciesName().equalsIgnoreCase(speciesName))
			{
				if (this.get(i).getBoroName().equalsIgnoreCase(boroName))
				{
					count ++;
				}
			}
		}
		return count;
	}
	/* *
	*Constructs an ArrayList made up of Strings that hold each species name that matches the parameter. (match as in parameter is a substring of or equals species name) Uses a boolean checker to make sure no species is repeatedly added.
	*@param String species name
	*@return ArrayList<String> containing each species name that matches the parameter 
	*/
	public ArrayList<String> getMatchingSpecies(String speciesName) 
	{
		//initialize Arraylist<String> to return
		ArrayList <String> species = new ArrayList();	
		//iterate through each tree in list
		for (int i = 0; i < this.size(); i++)
		{
			//as long as list is not empty
			if (this.isEmpty() == true)
			{
				break;
			}
			else
			{
				//if species name of current tree in treelist contains the parameter, ie parameter is a substring of a species name, add it to the ArrayList<String> as long as it doesn't already exist - no repetition
				if (this.get(i).getSpeciesName().toLowerCase().contains(speciesName.toLowerCase()) == true)
				{
					//checks if species name is already an element in the ArrayList of Strings, if not then add it
					boolean checker = check(species, this.get(i).getSpeciesName());
					if (checker == true)
					{	
						species.add(this.get(i).getSpeciesName());
					}
		
				}
				
			}
		}
		return species;
		
	}
	/* *
	*Constructs a String representation of the TreeList
	*@param none
	*@return String representing the whole TreeList 
	*/
	@Override
	public String toString()
	{
		String string = "";
		//for each tree in the TreeList, print the String representation of each tree on a new line
		for (int i = 0; i < this.size(); i++)
		{
			string += this.get(i).toString() + "\n";
		}
		return string;
	}
	/* *
	*Checks if a name is already an element in an ArrayList<String> 
	*@param ArrayList<String>, String name to check for name in the ArrayList
	*@return true if the name is not currently in the ArrayList, false if name is already in ArrayList
	*/
	public boolean check (ArrayList <String> species, String name)
	{
	//iterates through each element in parameter ArrayList. If ArrayList contains name parameter, return false
		for (int i = 0; i < species.size(); i ++)
		{
			if (species.get(i).equalsIgnoreCase(name))
			{
				return false;
			}
		}
		return true;
	}
	


}
