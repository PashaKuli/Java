/* *
*This class represents a tree object that consists of different data fields. The tree constructor takes 9 parameters and as long as the entries are valid,
*the tree is constructed, otherwise exceptions are thrown. It overrides the Object class's equals method - if trees have the same id (and species name) then they are equal (but if they have the same
*id's but different species name, then an exception is thrown as id's are unique). There are getter methods to access the state fields. The class implements the Comparable interface
*and ranking is determined lexicographically based on species name (if same species name, then determined by larger id number). This class has its own toString method that returns a
*String representation of the tree object.
*@author Prianca Padmanabhan
*@version 4/22/17
*
*/
public class Tree implements Comparable<Tree>
{
	//privates state fields of tree
	private int tree_id;
	private int tree_dbh;
	private String status;
	private String health;
	private String spc_common;
	private String boroname;
	private int zipcode;
	private double x_sp;
	private double y_sp;
	
	/* *
	*Constructs a Tree object by accepting 9 parameters
	*@param integer tree_id, integer tree diameter, String status of tree, String health of tree, String species name (common), integer zipcode of tree, String borough name, double x coordinate, double y coordinate
	*@return none
	*@throws IllegalArgumentExceptions if parameter values are invalid
	*/
	public Tree (int tree_id, int tree_dbh, String status, String health, String spc_common, int zipcode, String boroname, double x, double y ) 
	{
		//makes sure each parameter is valid otherwise throws exception
		if (tree_id < 0)
		{
			throw new IllegalArgumentException("Error: tree id must be a non-negative integer");
		}
		else
		{
			this.tree_id = tree_id;
		}
		if (tree_dbh < 0)
		{
			throw new IllegalArgumentException("Error: tree diameter must be a non-negative integer");
		}
		else
		{
			this.tree_dbh = tree_dbh;
		}
		if (status == null || status.equalsIgnoreCase("Alive") || status.equalsIgnoreCase("Dead") || status.equalsIgnoreCase("Stump") || status.equals(""))
		{
			this.status = status;
		}
		else
		{
			throw new IllegalArgumentException("Error: Status can be alive, dead, stump, null, or empty.");
		}
		if (health == null || health.equalsIgnoreCase("Good") || health.equalsIgnoreCase("Fair") || health.equalsIgnoreCase("Poor") || health.equals(""))
		{
			this.health = health;
		}
		else
		{
			throw new IllegalArgumentException("Error: Health can be good, fair, poor, null, or empty.");
		}
		if (spc_common == null)
		{
			throw new IllegalArgumentException("Error: Name cannot be null.");
		}
		else
		{
			this.spc_common = spc_common;
		}
		if (boroname == null)
		{
			throw new IllegalArgumentException("Error: Borough name must be one of the 5 boroughs.");
		}
		else if (boroname.equalsIgnoreCase("Manhattan") || boroname.equalsIgnoreCase("Bronx") || boroname.equalsIgnoreCase("Brooklyn") || boroname.equalsIgnoreCase("Queens") || boroname.equalsIgnoreCase("Staten Island"))
		{
			this.boroname = boroname;
		}
		else
		{
			throw new IllegalArgumentException("Error: Borough name must be one of the 5 boroughs.");
		}
		if (zipcode < 0 || zipcode > 99999)
		{
			throw new IllegalArgumentException("Error: Zipcode must be a positive 5 digit integer");
		}
		else
		{
			this.zipcode = zipcode;
		}
	}
	/* *
	*Determines if two trees are equal using a boolean to check
	*@param Tree tree to determine if equivalent
	*@return boolean true if trees are equal in id and species, false if id's are not equal
	*@throws IllegalArgumentException if tree id's are equal but species differ, as id's are unique to each tree
	*/
	public boolean equals (Tree tree)
	{
		//determines equivalence based on tree id
		if (tree.tree_id == this.tree_id)
		{
			//if tree id and species match, then equal
			if(tree.spc_common.equalsIgnoreCase(this.spc_common))
			{
				return true;
			}
			//if id's match but species doesnt then throw exception as each tree id is unique
			else
			{
				throw new IllegalArgumentException("Tree id's are unique so species name should also match.");
			}
		}
		else
		{
			return false;
		}
		
	}
	/* *
	*getter method for tree's species name
	*@param none
	*@return String species name
	*/
	public String getSpeciesName()
	{
		return this.spc_common;
	}
	/* *
	*Getter method for tree's borough name
	*@param none
	*@return String borough name
	*/
	public String getBoroName()
	{
		return this.boroname;
	}
	/* *
	*Overrides compareTo method to compare two trees based on species name, but if species names are equal, then compares based on id number
	*@param Tree tree 
	*@return integers 1, 0, -1. Returns -1 if tree as parameter is "less than" our tree object, 0 if trees are equal, and 1 if tree as parameter is "greater than" our tree object
	*/
	@Override
	public int compareTo(Tree o) {
		if (this.spc_common.compareToIgnoreCase(o.spc_common) > 0)
		{
			//tree o is less than this tree based on species name
			return 1;
		}
		else if (this.spc_common.compareToIgnoreCase(o.spc_common) < 0)
		{
			//tree o is greater than this tree based on species name
			return -1;
		}
		//if species names are equal
		else
		{
			//compare based on tree id's, 
			if (o.tree_id > this.tree_id)
			{
				//tree o is greater than this tree
				return -1;
			}
			else if (this.tree_id > o.tree_id)
			{
				//this tree greater than tree o
				return 1;
			}
			else
			{
				//both trees are equal in species and id, ie the same tree
				return 0;
			}
		}
		
	}
	/* *
	*Overrides toString method to return a String representation of the tree object
	*@param none
	*@return String representation of Tree object
	*/
	public String toString()
	{
		return(String.format("%s: %d, %s: %s, %s: %s, %s: %s, %s: %s" , "Tree ID", this.tree_id, "Status", this.status, "Species", this.getSpeciesName(),"Zipcode", this.formatZipcode(this.zipcode), "Borough", this.getBoroName()));
	}
	/* *
	*Method to format the zipcode of each tree. If the zipcode of a given tree is less than 5 digits, it is padded with 0's on the left
	*@param integer zipcode to be re-formatted
	*@return String representation of 5 digit zipcode in proper format
	*/
	public String formatZipcode(int zipcode)
	{
		//formats zipcode by padding it with zeroes to the left if the length of zipcode is less than 5
		String s = String.format("%05d", zipcode);
		return (s);	
	}
	/* *
	*Method to determine if this tree and Tree t have the same species name (case insensitive).
	*@param Tree object
	*@return true if the species names are the same (case insensitive), false otherwise
	*/
	public boolean sameName(Tree t)
	{
		boolean checker;
		//if names are equal ignoring case, return true
		if (t.spc_common.equalsIgnoreCase(this.spc_common))
		{
			checker = true;
		}
		//if names are not equal, return false
		else
		{
			checker = false;
		}
		return(checker);		
	}
	/* *
	*Method to compare the species name of this tree and another tree taken as a parameter, lexicographically, case insensitive. 
	*@param Tree object
	*@return 1 if this tree's species name is greater than that of the tree parameter's, -1 if this tree's species name is less, and 0 if the species names are equal.
	*/
	public int compareName(Tree t)
	{
		//call the already defined compareTo method, ignoring case 
		int comparison = this.spc_common.compareToIgnoreCase(t.spc_common);
		return comparison;
	}
}
