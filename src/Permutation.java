import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args)
    {   int k=Integer.parseInt(args[0]);
        RandomizedQueue<String> ran=new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String c = StdIn.readString();
            ran.enqueue(c);
        }
        Iterator r=ran.iterator();
        int n=0;
        while(r.hasNext()&n<k){
            StdOut.println(r.next());
            n++;

        }
    }
}