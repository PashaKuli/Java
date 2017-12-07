/* *
*This class provides the implementation of a binary search tree object that is defined by a root data field and a size data field. 
*It implements a default constructor, which creates an empty tree. It contains methods to add an element to the tree, check if an 
*element is already contained in the tree, remove an element from the tree, return the lowest elemetn currently in the tree, and
*return the highest element currently in the tree. This class extends the Comparable interface. Every time a node is added/removed, it's data
*is examined to determine where in the tree it should be added/removed.
*@author Prianca Padmanabhan
*@version 4/22/17
*/
import java.util.*;

public class MyBST<E extends Comparable<E>> 
{
	//protected data fields for the BST
	protected MyBSTNode<E> root;
	protected int size = 0;
	/* *
	*Constructs an empty BST by setting the root node to null/
	*@param none
	*@return none
	*@throws none
	*/
	public MyBST()
	{
		//set root to null to signify empty BST
		root = null;
	}
	/* *
	*Method to determine if an object is already contained in the binary search tree. 
	*@param Object o 
	*@return true if object is already contained in the tree, false otherwise 
	*@throws NullPointerException if specified object is null
	*/
	public boolean contains(Object o)
	{
		//if object is null, throw exception
		if (o == null)
		{
			throw new NullPointerException("Cannot work with null elements.");
		}
		//if not null, use private helper method to determine if object is already contained in BST
		else
		{
			return recContains(root, o);
		}
	}
	/* *
	*Private helper method to recursively determine if an object is already contained in the binary search tree. 
	*@param MyBSTNode<E> node, Object o 
	*@return true if object is already contained in the tree, false otherwise 
	*@throws none
	*/
	private boolean recContains(MyBSTNode<E> root, Object o)
	{
		//if tree is empty, return false
		if (root == null)
		{
			return false;
		}
		//if this object is bigger than current node's data, search the BST to the right of the current node
		else if (((E) o).compareTo((E) root.getData()) > 0)
		{
			return recContains(root.getRight(), o);
		}
		//if this object is smaller than current node's data, search the BST to the left of the current node
		else if (((E) o).compareTo((E) root.getData()) < 0)
		{
			return recContains(root.getLeft(), o);
		}
		//if object is equal to current node's data, return true
		else
		{
			return true;
		}
	}
	/* *
	*Method to add an element/node to the binary search tree as long as it is not already contained in the BST.
	*@param Element e
	*@return true if element can be added, false otherwise 
	*@throws NullPointerException if specified element is null
	*/
	public boolean add(E e)
	{
		//throw exception if element to be added is null
		if (e == null)
		{
			throw new NullPointerException("Can't add a null element.");
		}
		//if element does not already exist in BST, then add it and return true
		if (this.contains(e) == false)
		{
			//add the node to the BST 
			root = recAdd(root, e);	
			//update the size of the number of nodes in the BST 
			size ++;
			return true;
		}
		//if element is in BST, don't add it and return false
		else
		{
			return false;
		}
	}
	/* *
	*Private helper method to recursively add an element to the BST as long as it is not already contained in the BST.
	*@param MyBSTNode<E> node, E data
	*@return node that was added 
	*@throws none
	*/
	private MyBSTNode<E> recAdd(MyBSTNode <E> node, E newData)
	{
		//if current node is null, create new node containing newData
		if (node == null)
		{
			MyBSTNode<E> newNode = new MyBSTNode<E>(newData);
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
	*Method to remove an object from the BST.
	*@param Object o 
	*@return true if object is removed successfully, false otherwise 
	*@throws NullPointerException if specified object is null
	*/
	public boolean remove(Object o)
	{
		//if object to be removed is null, throw exception
		if (o == null)
		{
			throw new NullPointerException("Cannot remove a null element.");
		}
		//otherwise, if bst contains this object
		if (this.contains(o))
		{
			//remove this node from bst 
			root = recRemove(root, (E) o);
			//update the size of the bst and return true
			size --;
			return true;
		}
		//otherwise return false
		else
		{
			return false;
		}
	}
	/* *
	*Private helper method to remove an object from the BST by getting the predecessor of the node we want to remove.
	*@return E data of predecessor node
	*@throws NullPointerException if predecessor does not exist.
	*/
	private E getPredecessor (MyBSTNode<E> n)
	{
		//this should not happen, throw exception
		if (n.getLeft() == null)
		{
			throw new NullPointerException("This should never occur.");
		}
		else
		{
			//define node to the left
			MyBSTNode<E> current = n.getLeft();
			//while current node is not null
			while (current.getRight() != null)
			{
				//define node as node to the right until we cannot go right anymore
				current = current.getRight();
			}
			//return the data 
			return current.getData();
		}
	}
	/* *
	*Private helper method to recursively remove an object from the BST.
	*@return MyBSTNode<E> node to remove
	*@throws none
	*/
	private MyBSTNode <E> recRemove(MyBSTNode<E> node, E item)
	{
		
		if (node == null)
		{
			//do nothing, the item is not in the tree
		}
		//if item < node's data
		else if (item.compareTo(node.getData()) < 0)
		{
			//perform function on node to the left
			node.setLeft(recRemove(node.getLeft(), item));
		}
		//if item > node's data
		else if (item.compareTo(node.getData()) > 0)
		{
			//perform function on node to the right
			node.setRight(recRemove(node.getRight(), item));
		}
		//if item = node's data, found it
		else
		{
			//remove the data stored in the node
			node = removal(node);
		}
		//return the node
		return (node);
	}
	/* *
	*Private helper method to remove an object from the BST.
	*@return MyBSTNode<E> node to remove
	*@throws none
	*/
	private MyBSTNode <E> removal(MyBSTNode<E> node)
	{
		//if left node is null, go to node on the right
		if (node.getLeft() == null)
		{
			return node.getRight();
		}
		//if node on the right is null, go to node on the lft
		if (node.getRight() == null)
		{
			return node.getLeft();
		}
		//define date of predececessor node
		E data = getPredecessor(node);
		//set out node's data to predecessor's data
		node.setData(data);
		node.setLeft(recRemove(node.getLeft(), data));
		return node;
	}
	/* *
	*Method to get the first (lowest) element in the BST.
	*@return E data of first/lowest node
	*@throws NoSuchElementException if tree is empty.
	*/
	public E first()
	{
		//if tree is empty, throw exception
		if (root == null)
		{
			throw new NoSuchElementException("Tree is empty.");
		}
		//otherwise
		else
		{
			//start at the root and keep going to the left until we can't go left anymore
			MyBSTNode <E> node = root;
			while (node.getLeft() != null)
			{
				node = node.getLeft();
			}
			//return the data of this left-most/lowest node
			return(node.getData());
		}
	}
	/* *
	*Method to get the last (highest) element in the BST.
	*@return E data of last/highest node
	*@throws NoSuchElementException if tree is empty.
	*/
	public  E last()
	{
		//if tree is empty, throw exception
		if (root == null)
		{
			throw new NoSuchElementException("Tree is empty.");
		}
		//otherwise
		else
		{
			//start at the root
			MyBSTNode node = root;
			//keep going to the right until we can't go to the right anymore
			while (node.getRight() != null)
			{
				node = node.getRight();
			}
			//return the data of this highest/right-most node
			return (E) (node.getData());
		}
	}
	/* *
	*Boolean checker method to determine if a String  is already contained in the ArrayList<String>.
	*@return true if the String is not in the ArrayList, false if it is
	*@throws none
	*/
	public boolean check (ArrayList <String> species, String name)
	{
	//iterates through each element in parameter ArrayList. If ArrayList contains name parameter, return false, otherwise return true
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
	*This method overrides the toString method of Object class to return a String representation of the BST which consists of a list of the tree's elements/nodes, enclosed in square brackets, comma separated.
	*@return String representation of the BST
	*@throws none
	*/
	public String toString()
	{
		//define boolean
		boolean done;
		if (root != null)
		{
			//use in order traversal to traverse through each node in the bst
			Stack<MyBSTNode<E>> stack = new Stack<MyBSTNode<E>>();
			//add opening bracket
			String string1 = "[";
			MyBSTNode<E> current = root;
			done = false;
			//while there are more nodes to traverse
			while (done == false)
			{
				//as long as current is not null
				if (current != null)
				{
					//add the current node to the stack and move left 
					stack.push(current);
					current = current.getLeft();
				}
				else if (stack.empty() == false)
				{
					current = stack.peek();
					current = stack.pop();
					//add current node to the string containing all nodes, add comma to separate
					string1 += current + ", ";
					current = current.getRight();
				}
				else
				{
					//set done to true to stop searching the tree
					done = true;
				}
				
			}
			//add close bracket
			string1 += "]";		
			return(string1);
		}
		else
		{
			//empty tree so return empty brackets
			return("[]");
		}
	}
	
	
	
}
