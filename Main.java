import java.util.Random;

public class Main{

    public static void main(String[] args) {

        Random rnd = new Random();
        
        TreeSet<Integer> set = new TreeSet<>();

        for(int i=10; i<22; i++)
        set.add(i);


       set.pollFirst();
       
        set.inOrder();

        


    }

}