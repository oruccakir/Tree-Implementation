import java.util.LinkedList;
import java.util.Random;

public class Main{

    public static void main(String[] args) {

        int arr[] = {56, 47, 22, 13, 76, 88, 33, 44, 18, 99};

        TreeSet<Integer> set = new TreeSet<>();

        for(int i=0; i<arr.length; i++) set.add(arr[i]);

        set.inOrder();

        LinkedList<Integer> linkedList = new LinkedList<>();

        set.convertToLinkedList(set.root, linkedList);

        System.out.println(linkedList);

        
        
        

        


    }

}