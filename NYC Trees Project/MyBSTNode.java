/* *
*This class represents a node in the binary search tree. It implements the Comparable <MyBSTNode<E>> interface
*and contains three private data fields that keep tracks of the nodes on the right and left of this node, as well as the
*data associated with this node. This class has getters and setters for the right and left nodes as well as the
*data field of this node. It also contains a compareTo method that compares another node's data to this node's data.
*@author Prianca Padmanabhan
*@version 4/22/17
*/
public class MyBSTNode <E extends Comparable<E>> implements Comparable < MyBSTNode<E> >
{
	//private data fields of node
	private E data;
	private MyBSTNode<E> left;
	private MyBSTNode<E> right;
	/* *
	*Constructs a node by setting it's data according to the parameter it takes. 
	*@param E data
	*@return none
	*@throws none
	*/
	public MyBSTNode(E data)
	{
		this.data = data;
	}
	/* *
	*Constructs a node by setting it's data according to the parameter it takes, as well as defining the nodes to the right and left of this node. 
	*@param E data, MyBSTNode<E> left (to set left node), MyBSTNode<E> right (to set right node)
	*@return none
	*@throws none
	*/
	public MyBSTNode (E data, MyBSTNode <E> left, MyBSTNode<E> right)
	{
		this.data= data;
		this.left = left;
		this.right = right;	
	}
	/* *
	*Sets the data of this node.
	*@param E data
	*@return none
	*@throws none
	*/
	public void setData(E data)
	{
		this.data = data;
	}
	/* *
	*Gets the data of this node.
	*@param none
	*@return E data of this node
	*@throws none
	*/
	public E getData()
	{
		return this.data;
	}
	/* *
	*Sets the node to the left of this node.
	*@param MyBSTNode<E> left
	*@return none
	*@throws none
	*/
	public void setLeft(MyBSTNode<E> left)
	{
		this.left = left;
	}
	/* *
	*Gets the node to the left of this node.
	*@param none
	*@return MyBSTNode<E> left node
	*@throws none
	*/
	public MyBSTNode<E> getLeft()
	{
		return this.left;
	}
	/* *
	*Sets the node to the right of this node.
	*@param MyBSTNode<E> right node
	*@return none
	*@throws none
	*/
	public void setRight(MyBSTNode<E> right)
	{
		this.right = right;
	}
	/* *
	*Gets the node to the right of this node.
	*@param none
	*@return MyBSTNode<E> right node
	*@throws none
	*/
	public MyBSTNode<E> getRight()
	{
		return this.right;
	}
	/* *
	*Implements the compareTo method that compares nodes based on their data. If this node's data is greater then the parameter, less than the parameter, or equal, 1, -1 and 0 will be returned respectively.
	*@param none
	*@return MyBSTNode<E> right node
	*@throws none
	*/
	public int compareTo(MyBSTNode <E> other)
	{
		//use already defined compareTo method 
		return this.data.compareTo(other.data);
	}
	
}
