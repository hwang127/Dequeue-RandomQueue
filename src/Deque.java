import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int N;
    private class Node
    {
        Item item;
        private Node next;
        private Node previous;

    }
    private Node first,last;

    // construct an empty deque
    public Deque(){N=0;}

    // is the deque empty?
    public boolean isEmpty()
    {return N==0;}

    // return the number of items on the deque
    public int size(){return N;}

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item==null)
        {
            throw new java.lang.IllegalArgumentException();
        }
        Node oldFirst= first;
        first = new Node();
        first.item = item;

        first.next =oldFirst;
        if (oldFirst!=null){
        oldFirst.previous=first;}
        else {last=first;}
        N++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item==null)
        {
            throw new java.lang.IllegalArgumentException();
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        N++;
        if (N==1) first = last;
        else
            {
                oldlast.next = last;
                last.previous=oldlast;
            }
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (this.isEmpty())
        {
           throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if (first!=null)
            {first.previous=null;}
        N--;
        if (isEmpty()) last = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (this.isEmpty())
        {
            throw new java.util.NoSuchElementException();
        }
        Item item=last.item;
        if (last.previous!=null)last=last.previous;
        else {last=null;first=null;}


        last.next=null;
        N--;
        if (isEmpty()){first=null;}
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {return new ListIterator();}
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { throw new java.lang.UnsupportedOperationException();}
        public Item next()
        {   if(!this.hasNext()){throw new java.util.NoSuchElementException();}
            Item item = current.item;
            current = current.next;
            return item;
        }
    }



    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer>deq=new Deque<>();
        deq.addFirst(2);
        deq.addFirst(1);
        deq.addLast(3);
        for (int i:deq){StdOut.println(i); }
        StdOut.print(deq.size());
        StdOut.printf("%d,%d,%d",deq.removeLast(),deq.removeFirst(),deq.removeLast());
    }

}
