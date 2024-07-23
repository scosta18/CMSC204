package application;

import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	TreeNode<String> root;
	
	/**
	 * Default constructor
	 * creates tree
	 */
	public MorseCodeTree() {
		setRoot(new TreeNode<>(""));
		buildTree();
	}
	
	/**
	 * Returns the root pointer of the tree
	 * @return root  pointer
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}
	
	/**
	 * Method to insert data into certain pointer
	 * @param code loading to a given leaf
	 * @param result the data to be encoded at the given node
	 * @return A new node has been added to the morse code tree object.
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root=newNode;
		
	}
	
	/**
	 * This method is to insert data into a certain pointer
	 * @param code loading to a given leaf
	 * @param result the data tp be encoded at the given node
	 * @return Modified morse code tree object with new node inserted
	 */
	@Override
	public LinkedConverterTreeInterface<String> insert(String code, String result) {
		addNode(root, code, result);
		return this;
	}
	
	 /**
     * Code traversal is a recursive approach for entering data.
     * @param root the root to derive from
     * @param code the code leading to a given leaf
     * @param letter the data of the new TreeNode to be added
     */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		// TODO Auto-generated method stub
		if(code.length() == 1) {
			if(code.charAt(0)=='.') {
				root.left=new TreeNode<>(letter);
			}
			else {
				root.right =new TreeNode<>(letter);
			}
			return;
		}
		addNode(code.charAt(0)=='.'? root.left : root.right, code.substring(1),letter);
		
	}

	  /**
     * The value encoded at a particular code reference is returned.
     * @param code the code that describes the traversals within the tree
     * @return Data encoded at given string
     */
	@Override
	public String fetch(String code) {
		// TODO Auto-generated method stub
		return fetchNode(root, code);
	}

	  /**
     * Code traversal is a recursive approach for accessing node data.
     * @param root the root of the tree for this particular recursive instance of addNode
     * @param code the code for this particular recursive instance of fetchNode
     * @return data at node after traversal
     */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		// TODO Auto-generated method stub
		if(code.length()==1) {
			return code.charAt(0)=='.'? root.left.getData():root.right.getData();
		}
		return fetchNode(code.charAt(0)=='.'? root.left: root.right, code.substring(1));
	}

	 /**
     * Unsupported operation
     * @param data data of node to be deleted
     * @return N/A
     * @throws UnsupportedOperationException
     */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

    /**
     * Unsupported operation
     * @return N/A
     * @throws UnsupportedOperationException
     */
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	 /**
     * Construct the tree based on morse code
     */
	@Override
	public void buildTree() {
        insert(".", "e");
        insert("-", "t");

        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");

        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");

        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
    }
	
    /**
     *At this point in time, it constructs the tree.
     * @return ArrayList representation of the tree in LNR order
     */
	@Override
	public ArrayList<String> toArrayList() {
		// TODO Auto-generated method stub
		ArrayList<String>output = new ArrayList<>();
		LNRoutputTraversal(root, output);
		return output;
	}

    /**
     * Appends to a provided list after traversing the tree for subsequent processing/debugging.
     * @param root the root to diverge from
     * @param list the ArrayList that will hold the contents of the tree in LNR order
     */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		// TODO Auto-generated method stub
		if(root !=null) {
			LNRoutputTraversal(root.left, list);
			list.add(root.getData());
			LNRoutputTraversal(root.right,list);
		}
	}
}
