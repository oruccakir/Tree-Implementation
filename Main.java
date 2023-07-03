import java.util.Arrays;

import javax.swing.text.html.StyleSheet;

public class Main{

    public static void main(String[] args) {
        
        TreeSet<Integer> set = new TreeSet<>();

        set.add(15);

        set.add(-5);

        for(int i=0; i<10; i++)
        set.add(i);

        set.remove(0);

        set.remove(15);

        set.inOrder();

        Object [] arr= set.descendingSet();
        
        System.out.println(Arrays.toString(arr));

        set.addAll(new Integer[]{12,-12,0,54});

        set.inOrder();

        System.out.println();
        System.out.println(set.tailSet(6));

        System.out.println(set.subSet(0,7));

        System.out.println(set.first());

        System.out.println(set.last());

        set.inOrder();

        System.out.println();

        System.out.println(set.pollFirst());
        System.out.println(set.pollLast());

   

        set.inOrder();

        


    }

}