import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
        private Item[] s;
        private int head,tail = 0;

    // construct an empty randomized queue
    public RandomizedQueue()
    { s = (Item[]) new Object[64]; }

    // is the randomized queue empty?
    public boolean isEmpty()
    { return head == tail; }

    // return the number of items on the randomized queue
    public int size(){return tail-head;}
    //resize the array
    private void resize(int capacity)
    {
        Object[] copy = new Object[capacity];
        int N=tail-head;
        for (int i = head; i < tail; i++)
            copy[i-head] = s[i];
        s = (Item[]) copy;
        head=0;tail=N;
    }
    // add the item
    public void enqueue(Item item)
    {
        if (item==null)
        {
            throw new java.lang.IllegalArgumentException();
        }
        if ( tail== s.length) resize(2 * s.length);
        s[tail]=item;
        tail++;
    }

    // remove and return a random item
    public Item dequeue(){
        if (this.isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }

        int r=StdRandom.uniform(tail-head);
        Item item=s[r+head];
        s[r+head]=s[head];

        s[head]=null;
        head++;
        if (tail > 0 && tail-head == s.length/4) resize(s.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (this.isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }
        int ran=(int) (StdRandom.uniform()*(tail-head));
        return s[head+ran];
    }

    // return an independent iterator over items in random order

    public Iterator<Item> iterator()
    { return new ReverseArrayIterator(); }
    private class ReverseArrayIterator implements Iterator<Item>
    {   private Item []ran;
        private int n;
        public ReverseArrayIterator(){
            n=tail-head;
            Object[]o=new Object[tail-head];
            for (int i=head;i<tail;i++){o[i-head]=s[i];}
            ran=(Item []) o;
            for (int i =0 ; i < tail-head; i++)
            {
                int r = StdRandom.uniform(i + 1);
                Item item=ran[i];
                ran[i]=ran[r];
                ran[r]=item;
            }
        }









        public boolean hasNext() { return n > 0; }
        public void remove() { throw new java.lang.UnsupportedOperationException();}
        public Item next() {
            if(!this.hasNext()){throw new java.util.NoSuchElementException();}
            n--;
            return ran[n]; }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> st=new RandomizedQueue<>();
        st.enqueue("a");
        st.enqueue("b");
        StdOut.print(st.size());
        StdOut.print(st.tail);
        StdOut.print(st.sample());
        st.dequeue();
        StdOut.print(st.dequeue());
    }

}