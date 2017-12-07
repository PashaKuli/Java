/* *
* This class is used to store all the Tree objects of the BST. It inherits from the MyBST<Tree> class. It provides 
* a default constructor and contains methods to get the number of trees in the BST, override the add method of the MyBST class,
* count trees in the BST by species, count trees in the BST by species and borough, count trees in the BST by borough, collect 
* all the species names of trees in the BST that match or contain a String species name entered as a parameter. It also contains
* a toString method to return a String representation of all the trees in the BST.
* 
*/
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class TreeCollection extends MyBST<Tree>
{
	//array to keep track of count of trees in each borough
	private static int [] boroughCount = new int[5];
	//arraylist to keep track of species name of each tree added 
	private static ArrayList<String> species = new ArrayList<String>();
	/* *
	*Constructs an empty Tree Collection by calling the super constructor
	*@param none
	*@return none
	*@throws none
	*/
	public TreeCollection()
	{
		//call on super constructor to construct an empty BST
		super();
	}
	/* *
	*Returns the number of trees in the BST
	*@param none
	*@return none
	*@throws none
	*/
	public int getTotalNumberOfTrees()
	{
		//collect size of BST from add method 
		return size;
	}
	@Override
	/* *
	*Overrides the add method of the MyBST class to efficiently keep track of count of trees by borough and collect all the species names of trees entered into the BST.
	*@param Tree tree to be added
	*@return true if Tree is added to the BST, false otherwise
	*@throws NullPointerException if a null tree is to be added
	*/
	public boolean add(Tree tree)
	{
		//if tree is null, throw exception
		if (tree == null)
		{
			throw new NullPointerException("Can't add a null element.");
		}
		//if tree is not already contained in BST, then add it
		if (this.contains(tree) == false)
		{
			root = recAdd(root, tree);
			//determine borough of tree to be added and add to the count of that borough (in array_
			if(tree.getBoroName().equalsIgnoreCase("Manhattan"))
			{
				boroughCount[0] ++;
			}
			else if (tree.getBoroName().equalsIgnoreCase("Brooklyn"))
			{
				boroughCount[1] ++;
			}
			else if (tree.getBoroName().equalsIgnoreCase("Queens"))
			{
				boroughCount[2] ++;
			}
			else if (tree.getBoroName().equalsIgnoreCase("Staten Island"))
			{
				boroughCount[3] ++;
			}
			else
			{
				boroughCount[4] ++;
			}
			//as long as species name of this tree is not already in the arraylist of species names, add it to the array list to ensure array list contains unique names only
			boolean checker = check(species, tree.getSpeciesName());
			if (checker == true)
			{
				species.add(tree.getSpeciesName());	
			}
			//update the number of trees
			size ++;
			return true;		
		}
		else
		{
			//return false if tree is already contained in bst
			return false;
		}

	}
	/* *
	*Private helper method to recursively add a tree to the BST
	*@param MyBSTNode<Tree> node, Tree tree to be added
	*@return MyBSTNode<Tree>node that represents tree to add
	*@throws none
	*/
	private MyBSTNode<Tree> recAdd(MyBSTNode <Tree> node, Tree newData)
	{
		//if current node is null, create new node containing newData
		if (node == null)
		{
			MyBSTNode<Tree> newNode = new MyBSTNode<Tree>(newData);
			//return reference to this node
			return newNode;
		}
		//if newData< node's data
		if (newData.compareTo(node.getData()) < 0)
		{
			//focus on the left branch of that node, perform recursion
			node.setLeft(recAdd(node.getLeft(), newData));

		}
		//otherwise focus on the right branch of that node
		else
		{
			node.setRight(recAdd(node.getRight(), newData));
		}
		//return the node to be added
		return node;
	}
	/* *
	*Gets the count of trees in the BST with species name that match the parameter (case insensitive).
	*@param String speciesName to search for matches
	*@return int count that contains count of trees in BST with matching species name 
	*@throws none
	*/
	public int getCountByTreeSpecies(String speciesName)
	{
		//return output from recursive private helper method
		return recGetCountBySpecies(root, speciesName);
	}
	/* *
	*Private helper method to get the count of trees in the BST with species name that match the parameter (case insensitive).
	*@param MyBSTNode<Tree> current, String speciesName to search for matches
	*@return int count that contains count of trees in BST with matching species name 
	*@throws none
	*/
	private int recGetCountBySpecies(MyBSTNode<Tree> current, String speciesName)
	{
		//if current node is null, return 0
		if (current == null)
		{
			return 0;
		}
		//if current species name > parameter species name, call function on node to the left
		if (current.getData().getSpeciesName().compareToIgnoreCase(speciesName) > 0)
		{
			return recGetCountBySpecies(current.getLeft(), speciesName);
		}
		//if current species name < parameter species name, call function on node to the right
		else if (current.getData().getSpeciesName().compareToIgnoreCase(speciesName) < 0)
		{
			return recGetCountBySpecies(current.getRight(), speciesName);
		}
		//if species names match, add 1 to the count and search both children nodes
		else
		{
			return 1 + recGetCountBySpecies(current.getLeft(), speciesName) + recGetCountBySpecies(current.getRight(), speciesName);
		}
	}
	
	/* *
	*Gets the count of trees in the BST with species name that match the parameter (case insensitive) and borough name that matches parameter (case insensitive).
	*@param String speciesName, String boroName to search for matches
	*@return int count that contains count of trees in BST with matching species name and borough name
	*@throws none
	*/
	public int getCountByTreeSpeciesBorough(String speciesName, String boroName)
	{
		//return output from recursive private helper method
		return recGetCountBySpeciesBorough(root, speciesName, boroName);
	}
	/* *
	*Private helper method to get the count of trees in the BST with species name that match the parameter (case insensitive) and borough name that matches parameter (case insensitive).
	*@param MyBSTNode<Tree> current, String speciesName, String boroName to search for matches
	*@return int count that contains count of trees in BST with matching species name and borough name
	*@throws none
	*/
	private int recGetCountBySpeciesBorough(MyBSTNode<Tree> current, String speciesName, String boroName)
	{
		//if current is null return 0
		if (current == null)
		{
			return 0;
		}
		//if current name is greater than parameter name, search left
		if (current.getData().getSpeciesName().compareToIgnoreCase(speciesName) > 0)
		{
			return recGetCountBySpeciesBorough(current.getLeft(), speciesName, boroName);
		}
		//if current name is less than parameter name, search right
		else if (current.getData().getSpeciesName().compareToIgnoreCase(speciesName) < 0)
		{
			return recGetCountBySpeciesBorough(current.getRight(), speciesName, boroName);
		}
		//if species names match
		else
		{
			//only add to the count if borough names match too, then search both children nodes
			if (current.getData().getBoroName().compareToIgnoreCase(boroName) == 0)
			{
				return 1 + recGetCountBySpeciesBorough(current.getLeft(), speciesName, boroName) + recGetCountBySpeciesBorough(current.getRight(), speciesName, boroName);
			}
			else
			{
				//otherwise dont add to the count but search both children nodes
				return recGetCountBySpeciesBorough(current.getLeft(), speciesName, boroName) + recGetCountBySpeciesBorough(current.getRight(), speciesName, boroName);
			}
			
		}
	}
	/* *
	*Gets the count of trees in the BST in the borough specified as a parameter. 
	*@param String boroName to search for matches
	*@return int count that contains count of trees in BST in specified borough
	*@throws none
	*/
	public int getCountByBorough(String boroName)
	{
		//if borough is manhattan, return that count from array
		if (boroName.equalsIgnoreCase("Manhattan"))
		{
			return this.boroughCount[0];
		}
		//if borough is brooklyn, return that count from array
		else if (boroName.equalsIgnoreCase("Brooklyn"))
		{
			return this.boroughCount[1];
		}
		//if borough is queens, return that count from array
		else if (boroName.equalsIgnoreCase("Queens"))
		{
			return this.boroughCount[2];
		}
		//if borough is staten island, return that count from array
		else if (boroName.equalsIgnoreCase("Staten Island"))
		{
			return this.boroughCount[3];
		}
		//if borough is bronx, return that count from array
		else
		{
			return this.boroughCount[4];
		}
	}
	/* *
	*Gets all the species names of trees in the BST that equal or contain the speciesName entered as a parameter.
	*@param String speciesName to search for all species names of trees in the BST that contain this parameter
	*@return int count that contains count of trees in BST with species names that contain the String parameter.
	*@throws none
	*/
	public Collection<String> getMatchingSpecies(String speciesName)
	{
		//initialize array list of strings to store all matching species names in the BST
		ArrayList<String> speciesCollection = new ArrayList<String>();
		//iterate through the names in the array list, species, which contains the species names of each tree in the bst
		for (int i = 0; i < this.species.size(); i++)
		{
			//if an element in the array list, species, contains the species name parameter, add it to speciesCollectio
			if (this.species.get(i).toLowerCase().contains(speciesName.toLowerCase()) == true)
			{
				speciesCollection.add(this.species.get(i));
			}
		}
		//return this array list containing all matching species names
		return speciesCollection;
	}

/* *
*Checks if an string is already an element in an arrayList of strings
*@param ArrayList<String> species, String name to search for in arraylist
*@return false if name is already in element in the arraylist, true otherwise
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
	/* *
	*Overrides the toString method of the Object class to print a String representation of each tree in the BST.
	*@param String speciesName, String boroName to search for matches
	*@return int count that contains count of trees in BST with matching species name and borough name
	*@throws none
	*/
	public String toString()
	{
		//uses in order traversal to print a string representation of each tree in the bst
		boolean done;
		if (root != null)
		{
			Stack<MyBSTNode<Tree>> stack = new Stack<MyBSTNode<Tree>>();
			MyBSTNode<Tree> current = root;
			String string1 = "";
			done = false;
			//while there are more nodes to traverse
			while (done == false)
			{
				if (current != null)
				{
					//add to the stack and search left
					stack.push(current);
					current = current.getLeft();
				}
				else if (stack.empty() == false)
				{
					current = stack.peek();
					current = stack.pop();
					//add the string representation of the current tree (using toString from Tree class) to the string we want to return
					string1 += current.getData().toString() + "\n";
					current = current.getRight();
				}
				else
				{
					//no more nodes to traverse
					done = true;
				}
				
			}
			//return string representation of all trees in BST
			return(string1);
		}
		//if tree is empty, nothing to print
		else
		{
			return("");
		}
	}

	
}


