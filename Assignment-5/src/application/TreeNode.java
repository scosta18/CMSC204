package application;
public class TreeNode <T>{

    private T data;
    public TreeNode<T> left;
    public TreeNode<T> right;

    /**
     * New node whic only containing data
     * @param value of data to store
     */
    public TreeNode(T data){
        this.data = data;
    }

    /**
     *Deep copu constructor, copies data and pointers to other leaves
     * @param node ndoe to copy
     */
    public TreeNode(TreeNode<T> node){
        this.data = node.data;
        this.left = node.left;
        this.right = node.right;
    }

    /**
     * Public access method for data access
     * data access for public access.
     * @return data held by pointer
     */
    public T getData(){
        return data;
    }

}