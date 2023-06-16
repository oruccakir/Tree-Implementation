
import java.util.ArrayDeque;
import java.util.ArrayList;

import javax.swing.text.TabExpander;


public class Tree <T>{

    private Node head;
    private Node tail;
    private int size;
    private int height;

    public Object[][] matrix;

    public Tree (int height){

        this.height = height;
        matrix = new Object[height][2*(int) Math.pow(2,height-1)+1];
        builtTree();
        size = this.size();
        connectLastLevel();
    }

    public Tree(ArrayList<T> list){

        builtTree(list);

        size = list.size();

        height = height();

    }

    public int size(Node root){

        if(root == null) return 0;

        return 1 + size(root.right) + size(root.left);

    }

    public int size(){
        return size(head);
    }

    public int height(){
        return height(head);
    }

    public int height(Node root){

        if(root == null) return 0;

        return 1 + Math.max(height(root.left), height(root.right));

    }

    public void findLeafNodes(Node root, ArrayList<Node> list){

        if(root == null) return;

        findLeafNodes(root.left, list);
        findLeafNodes(root.right, list);

        if(root.left == null && root.right == null) list.add(root);

    }

    private void builtTree(){

        if(height > 0){

            head = new Node();

            matrix[0][(int) Math.pow(2,height-1)-1] = head;

            built(head,1,(int) Math.pow(2,height-1)-1,height-2);

        }

    }

    private void built(Node root,int level,int y,int n){

        if(level== height) return;

        root.left = new Node();
        root.left.parent = root;

        matrix[level][y-(int) Math.pow(2,n)] = root.left;
        root.right = new Node();

        matrix[level][y+(int) Math.pow(2,n)] = root.right;
        root.right.parent = root;

        built(root.left,level+1,y-(int) Math.pow(2,n),n-1);
        built(root.right,level+1,y+(int) Math.pow(2,n),n-1);

    }

    public void builtTree(ArrayList<T> list){

        if(list.size() == 0) return;

        head = new Node(list.get(0));

        ArrayDeque<Node> dq = new ArrayDeque<>();
        
        dq.addLast(head);

        int n = list.size();
        
        int index = 0;
        
        while(dq.isEmpty() == false){
            
            Node root = dq.pollFirst();
            
            if(2*index+1 < n){
                
                root.left = new Node(list.get(2*index+1));
                
            }
            
            if(2*index+2 < n){
                
                root.right = new Node(list.get(2*index+2));
                
            }
            
            if(root.left != null) dq.addLast(root.left);
            
            if(root.right != null) dq.addLast(root.right);
            
            index++;
            
        }
        
    }

    public void connectLastLevel(){

        ArrayList<Node> leafs = new ArrayList<>();

        this.findLeafNodes(head, leafs);

        tail = leafs.get(0);

        for(int i=0; i<leafs.size()-1; i++)
            leafs.get(i).next = leafs.get(i+1);

    }

    public void add(T data){

        Node newNode = new Node(data);

        if(tail == null) {

            tail = getMostLeftNode(head);

             connectLastLevel();

            this.height++;

        }

        if(tail.left != null && tail.right != null) tail = tail.next;

        if(tail == null) {

            tail = getMostLeftNode(head);

            connectLastLevel();

            this.height++;

        }

        if(tail.left == null) tail.left = newNode;

        else if(tail.right == null) tail.right = newNode;

    }

    public void print_as_tree(){

        for(int i=0; i<matrix.length; i++){

            for(int k=0; k<matrix[0].length; k++){

                if(matrix[i][k] == null) System.out.print(" ");

                else System.out.print(((Node)matrix[i][k]).data);


            }
            System.out.println();

        }

    }

    public void fillTheTree(ArrayList<T> list){

        if(list.size() == 0) return;

        ArrayDeque<Node> dq = new ArrayDeque<>();

        int index = 0;

        dq.addLast(head);

        boolean isTreeFilled = false;
        boolean isListFinished = false;

        while(isListFinished == false && isTreeFilled == false){

            Node root = dq.pollFirst();

            root.data = list.get(index);

            if(root.left != null) dq.addLast(root.left);

            if(root.right != null) dq.addLast(root.right);

            if(dq.isEmpty()) isTreeFilled = true;

            if(index == list.size()-1) isListFinished = true;

            index++;

        }


    }

    public void refreshTree(Node root){

        if(root == null) return;

        root.data = null;

        refreshTree(root.left);
        refreshTree(root.right);

    }

    public void refreshTree(){

        refreshTree(head);

    }

    public Node getMostLeftNode(Node root){

        if(root.left == null) return root;

        return getMostLeftNode(root.left);

    }

    public Node getMostRightNode(Node root){

        if(root.right == null) return root;

        return getMostLeftNode(root.right);

    }

    
    public void levelOrderPrint(){

        ArrayDeque<Node> dq = new ArrayDeque<>();

        if(head != null) dq.addLast(head);

        while(dq.isEmpty() == false){

            int n = dq.size();

            Node root = null;

            for(int i=0; i<n; i++){

                root = dq.pollFirst();

                System.out.print(root.data+" ");

                if(root.left != null) dq.addLast(root.left);

                if(root.right != null) dq.addLast(root.right);
            }

            System.out.println();

        }

    }



    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        int size = 63;

        for(int i=0; i<size; i++){
            list.add(i);
        }

        Tree<Integer> tree = new Tree<>(list);

        tree.levelOrderPrint();

        Tree<Integer> tree1 = new Tree<>(4);

        tree1.levelOrderPrint();
        tree1.print_as_tree();

        //System.out.println(tree.size);
        //System.out.println(tree.height);

        tree1.fillTheTree(list);

        for(int i=0; i<16; i++){
            tree1.add(i);
        }

        for(int i=0; i<32; i++){
            tree1.add(i);
        }

        for(int i=0; i<64; i++){
            tree1.add(i);
        }

       

        tree1.levelOrderPrint();
        



        

        
        
    }

    



















































    private class Node{

        public Node  left;
        public Node  right;
        public Node  parent;
        public Node  next;
        public T data;

        public Node(T data){
            
            this.data = data;
            this.left = this.right = this.parent = null;

        }

        public Node(){
            
            this.data = null;
            this.left = this.right = this.parent = null;

        }

        public String toString(){
            return "Data  :"+this.data;
        }

        

    }











}