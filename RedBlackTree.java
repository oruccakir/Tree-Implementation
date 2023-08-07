import java.util.Stack;

public class RedBlackTree <K extends Comparable<K>,V> {

    private static final String RED = "red";
    private static final String BLACK = "black";

    private boolean left_left = false;
    private boolean right_right = false;
    private boolean left_right = false;
    private boolean right_left = false;

    public Node root;

    public RedBlackTree(){

    }

    public void insert(K key, V value){

        if(this.root == null){
            this.root = new Node(key, value);
            this.root.colour = BLACK;
        }
        else
            this.root = insertHelp(this.root,key,value);

    }

    private Node insertHelp(Node root, K key, V value){

        boolean isDoubleRed = false;

        if(root == null){
            return new Node(key, value);
        }
        else if(key.compareTo(root.data.key) < 0){

            root.left = insertHelp(root.left, key, value);
            root.left.parent = root;
            
            if(root != this.root){
                if(root.colour.equals(RED) && root.left.colour.equals(RED))
                    isDoubleRed = true;
            }
            
        }
        else{

            root.right = insertHelp(root.right, key, value);
            root.right.parent = root;

            if(root != this.root){
                if(root.colour.equals(RED) && root.right.colour.equals(RED))
                    isDoubleRed = true;
            }

        }

        if(left_left){
            root = root.rotateLeft(root);
            root.colour = BLACK;
            root.left.colour = RED;
            this.left_left = false;
        }
        else if(right_right){
            root = root.rotateRight(root);
            root.colour = BLACK;
            root.right.colour = RED;
            this.right_right= false;
        }
        else if(right_left){
            root.right = root.rotateRight(root);
            root.right.parent = root;
            root = root.rotateLeft(root);
            root.colour = BLACK;
            root.left.colour = RED;
            this.right_left = false;
        }
        else if(left_right){
            root.left = root.rotateLeft(root);
            root.left.parent = root;
            root = root.rotateRight(root);
            root.colour = BLACK;
            root.right.colour = RED;
            this.left_right = false;
        }

        if(isDoubleRed){

            if(root.parent.right == root){

                if(root.parent.left == null || root.parent.left.colour.equals(BLACK)){

                    if(root.left != null && root.left.colour.equals(RED))
                        this.right_left = true;
                    else if(root.right != null && root.right.colour.equals(RED))
                        this.left_left = true;

                }
                else{

                    root.parent.left.colour = BLACK;
                    root.colour = BLACK;

                    if(root.parent != this.root)
                        root.parent.colour = RED;

                }

            }
            else{

                if(root.parent.right == null || root.parent.right.colour.equals(BLACK)){

                    if(root.left != null && root.left.colour.equals(RED))
                        this.right_right = true;
                    else if(root.right != null && root.right.colour.equals(RED))
                        this.left_right = true;
                }
                else{

                    root.parent.right.colour = BLACK;
                    root.colour = BLACK;

                    if(root.parent != this.root)
                        root.parent.colour = RED;

                }

            }

            isDoubleRed = false;

        }

        return root;
    }


    private void inorderRecursive(Node root){

        if(root == null) return;

        inorderRecursive(root.left);

        System.out.print(root.data+" ");

        inorderRecursive(root.right);

    }

    private void inorderIterative(){

        Stack<Node> stack = new Stack<>();

        Node curr = root;

        while(curr != null || stack.isEmpty() == false){

            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            else{
                curr = stack.pop();
                System.out.print(curr.data+" ");
                curr = curr.right;
            }

        }

    }

    public void printInorder(){

        inorderIterative();

    }


    private class Node{

        public Entry data;
        public Node left;
        public Node right;
        public Node parent;
        public String colour;

        public Node(K key,V value){
            data = new Entry(key, value);
            colour = RED;
        }
        
        public Node rotateLeft(Node node){

            Node node_right = node.right;
            Node node_right_left = node_right.left;

            node_right.left = node;
            node.right = node_right_left;
            node.parent = node_right;

            if(node_right_left != null)
                node_right_left.parent = node;
            
            return node_right;

        }


        public Node rotateRight(Node node){

            Node node_left = node.left;
            Node node_left_right = node_left.right;

            node_left.right = node;
            node.left = node_left_right;
            node.parent = node_left;

            if(node_left_right != null)
                node_left_right.parent =node;
            
            return node_left;

        }
        

    }

    private class Entry{

        private K key;
        private V value;

        public Entry(K key,V value){
            this.key = key;
            this.value = value;
        }

        public void setValue(V value){
            this.value = value;
        }

        public V getValue(){
            return value;
        }

        public void setKey(K key){
            this.key = key;
        }

        public K getKey(){
            return key;
        }

        public String toString(){
            return "[ "+key+", "+value+" ]";
        }

    }

    public static void main(String[] args) {
        
        RedBlackTree<Integer,String> rt = new RedBlackTree<>();

        rt.insert(1, "bebe");
        rt.insert(12, "kral");
        rt.insert(4, "cicikuşş");
        rt.insert(15,"heyyy");

        rt.printInorder();

    }
    
}
