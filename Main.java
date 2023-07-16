import java.util.Random;

public class Main{

    public static void main(String[] args) {

        Random rnd = new Random();
        
        TreeSet<Integer> set = new TreeSet<>();

        for(int i=0; i<10; i++) set.add(100+i);

        for(int i=0; i<10; i++) set.add(90+i);

        for(int i=0; i<10; i++) set.add(80+i);


       


        set.inOrder();

        set.diagonalTraversal();

        


    }

}