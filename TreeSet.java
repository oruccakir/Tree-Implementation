import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TreeSet<T extends Comparable<T>> implements TreeSetInterface<T> {

    private Node root;

    public int size;











    












    @Override
    public void add(T data) {
        
       root =  insertKey(root, data);

       size++;

    }












    @Override
    public void clear() {
        root = null;
    }












    @Override
    public boolean contains(T data) {

        return search(root, data);

    }












    @Override
    public T first() throws NoSuchElementException{
        
        if(isEmpty()) throw new NoSuchElementException();

        Node curr = root;

        while(curr.left != null){

            curr = curr.left;

        }

        return curr.data;

    }












    @Override
    public boolean isEmpty() {
        return root == null;
    }












    @Override
    public T last() throws NoSuchElementException {

        if(isEmpty()) throw new NoSuchElementException();

        Node curr = root;

        while(curr.right != null) {

            curr = curr.right;

        }

        return curr.data;

    }












    @Override
    public void remove(T data) {
        
        root = deleteKey(root, data);

        size--;

    }












    @Override
    public int size() {
        return size;
    }












    @Override
    public T pollFirst() {
        
        if(isEmpty()) return null;

        if(size() == 1) {

            T data = root.data;

            root = null;

            return data;

        }

        Node curr = root;
        Node prev = null;

        while(curr.left != null){

            prev = curr;

            curr = curr.left;

        }

        T data = curr.data;

        prev.left = null;

        return data;

    }







    @Override
    public T pollLast() {
        
        if(isEmpty()) return null;

        if(size() == 1) {

            T data = root.data;

            root = null;

            return data;

        }

        Node curr = root;
        Node prev = null;

        while(curr.right != null){

            prev = curr;

            curr = curr.right;

        }

        T data = curr.data;

        prev.right = null;

        return data;

    }












    @Override
    public T ceiling(T data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ceiling'");
    }












    @Override
    public T floor(T data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'floor'");
    }












    @Override
    public void addAll(T[] data_array) {
        
        for(T data : data_array)
         add(data);

    }










    private void tailSet(LinkedList<T> linkedList, Node root, T data){

        if(root == null) return;

        if(data.compareTo(root.data) < 0 || data.equals(root.data)) linkedList.add(root.data);

        tailSet(linkedList,root.left,data);
        tailSet(linkedList,root.right,data);

    }

    @Override
    public LinkedList<T> tailSet(T data) {
        
        LinkedList<T> linkedList = new LinkedList<>();

        tailSet(linkedList, root, data);

        return linkedList;
        

    }












    @Override
    public LinkedList<T> subSet(T fromData, T toData) {
        
        LinkedList<T> linkedList = new LinkedList<>();

        if(fromData.compareTo(toData) > 0) return linkedList;

        Object[] data_array = increasingSet();

        for(int i=0; i<data_array.length; i++){

            if( (((T)data_array[i]).compareTo(fromData) > 0 || ((T) data_array[i]).equals(fromData) ) && ((T)data_array[i]).compareTo(toData) < 0 ){

                linkedList.add((T)data_array[i]);

            }

        }

        return linkedList;
    }












    @Override
    public T higher(T data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'higher'");
    }












    @Override
    public T lower(T data) {
        return null;
    }


    @Override
    public Object[] increasingSet() {

        ArrayDeque<Node> dq = new ArrayDeque<>();

        Node curr = root;

        Object [] data_array = new Object[size];

        int index = 0;

        while(curr != null || dq.isEmpty() == false){

            if(curr != null){

                dq.push(curr);

                curr = curr.left;

            }

            else{

                curr = dq.pop();

                data_array[index] = curr.data;

                index++;

                curr = curr.right;

            }

        }

        return data_array;

    }


    @Override
    public Object[] descendingSet() {
        
        ArrayDeque<Node> dq = new ArrayDeque<>();

        Node curr = root;

        Object [] data_array = new Object[size];

        int index = 0;

        while(curr != null || dq.isEmpty() == false){

            if(curr != null){

                dq.push(curr);

                curr = curr.right;

            }

            else{

                curr = dq.pop();

                data_array[index] = curr.data;

                index++;

                curr = curr.left;

            }

        }

        return data_array;

    }

    private boolean search(Node root, T data){

        if(root == null ) return false;

        if(root.data.equals(data)) return true;

        if(data.compareTo(root.data) < 0) return search(root.left, data);

        else return search(root.right, data);

    }

    private Node insertKey(Node root, T data){

        if(root == null){

            root = new Node(data);

            return root;

        }

        else if(data.compareTo(root.data) < 0){

            root.left = insertKey(root.left, data);
            
        }

        else if(data.compareTo(root.data) > 0){

            root.right = insertKey(root.right, data);

        }

        return root;

    }

    private Node deleteKey(Node root, T data){

        if(root == null) return root;

        if(data.compareTo(root.data) < 0){

            root.left = deleteKey(root.left, data);

        }

        else if(data.compareTo(root.data) > 0){

            root.right = deleteKey(root.right, data);

        }

        else if(data.equals(root.data)){

            if(root.left == null) return root.right;

            else if(root.right == null) return root.left;

            root.data = minValue(root.right);

            root.right = deleteKey(root.right, root.data);

        }

        return root;

    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node root){

        if(root == null) return;

        System.out.print(root.data+" ");

        preOrder(root.left);
        preOrder(root.right);

    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node root){

        if(root == null) return;

        inOrder(root.left);

        System.out.print(root.data+" ");

        inOrder(root.right);

    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node root){

        if(root == null) return;

        postOrder(root.left);
        postOrder(root.right);

        System.out.print(root.data+" ");


    }

    private T minValue(Node root){

        if(root == null) return null;

        Node node = root;

        T ans = null;

        while(node != null){

            ans = node.data;

            node = node.left;

        }

        return ans;

    }

    private T maxValue(Node root){

        if(root == null) return null;

        Node node = root;

        T ans = null;

        while(node != null){

            ans = node.data;

            node = node.right;

        }

        return ans;

    }

    private class Node{

        public T data;
        public Node left;
        public Node right;

        public Node(T data){

            this.data = data;
            this.left = this.right = null;

        }

        public String toString(){

            return "Data : "+data+" Left : "+left.data+" Right : "+right.data+"\n";

        }

    }


}