import java.util.*;

/**
 * A LinkStrand represents new pedagogy with Nodes to decrease runtime
 *
 * @author Ashrit Verma
 *
 */

public class LinkStrand implements IDnaStrand {

    /**
     * Create Node by using String s and Node n as parameter s.t. instance variables
     * info can equate to s and .next of Node can equate to n
     */
    private class Node{
        String info;
        Node next;

        public Node(String s, Node n){
            info = s;
            next = n;
        }
    }

    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;

    private Node myCurrent;
    private int myLocalIndex;
    private int myIndex;

    /**
     * Default constructor of LinkStrand
     */
    public LinkStrand(){
        this("");
    }

    /**
     * Constructor of LinkStrand with parameter s works by calling method initialize
     * to initialize instance variables
     * @param s represents the strand of DNA that will be used to initialize the instance variables
     */
    public LinkStrand(String s){
        initialize(s);
    }

    /**
     * Returns the total number of string characters in LinkStrand
     * @return mySize representing the total number of string characters in LinkStrand
     */
    @Override
    public long size() {
        return mySize;
    }

    /**
     * Establishes and maintains the class invariant with a single node representing
     * the entire strand of DNA
     * @param source represents the source strand of DNA
     */
    @Override
    public void initialize(String source) {
        myFirst = new Node(source, null);
        myLast = myFirst;
        mySize = source.length();
        myAppends = 0;
        myCurrent = myFirst;
        myLocalIndex = 0;
        myIndex = 0;
    }

    /**
     *
     * @param source is data from which object constructed
     * @return LinkStrand object
     */
    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    /**
     * Mutator that creates one new node and updates instance variables to maintain class invariants. DNA appended to strand.
     * @param dna is the string appended to this strand
     * @return this object that was just modified/appended to
     */
    @Override
    public IDnaStrand append(String dna) {
        Node a = new Node(dna, null);
        myLast.next = a;
        myLast = myLast.next;
        mySize += dna.length();
        myAppends ++;
        return this;
    }

    /**
     * Creates a new `LinkStrand` object that is the reverse of the object on which it's called; ; it does not alter the strand on which it's called, i.e., it's not a mutator.
     * @return new LinkStrand object that's the reverse of the object on which it's called.
     */
    @Override
    public IDnaStrand reverse() {
        Node list2 = myFirst;
        StringBuilder ofList2 = new StringBuilder("");
        LinkStrand result = new LinkStrand();

        while(list2 != null)
        {
            ofList2 = new StringBuilder(list2.info);
            result.myFirst = new Node(ofList2.reverse().toString(), result.myFirst);
            list2 = list2.next;
        }

        result.mySize = mySize;
        result.myAppends = myAppends;

        return result;
    }

    /**
     * Returns correct myAppends that's maintained by class variants and initialized/updates in initialize and append
     * @return myAppends instance variable
     */
    @Override
    public int getAppendCount() {
        return myAppends;
    }

    /**
     * Returns the character at the specified index if that's a valid index, and throws an `IndexOutOfBoundsException` otherwise
     * @param index specifies which character will be returned
     * @return the character at a specific index located on a specific Node from the entire LinkStrand
     * @throws IndexOutOfBoundsException if index is less than 0 or is greater than the size of the LinkStrand
     */
    @Override
    public char charAt(int index) {
        if(index < 0 || index > mySize-1) throw new IndexOutOfBoundsException();
        if(index < myIndex)
        {
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }
        while(index - myIndex > myCurrent.info.length() - myLocalIndex - 1)
        {
            myIndex += myCurrent.info.length() - myLocalIndex;
            myLocalIndex = 0;
            myCurrent = myCurrent.next;
        }
        myLocalIndex += index - myIndex;
        myIndex = index;
        return myCurrent.info.charAt(myLocalIndex);
    }

    /**
     * Returns the string representation of LinkStrand by looping over nodes and appending thier values to StringBuilder object
     * @return `String` representation of the entire DNA strand i.e. concatenation of the `String` stored in each node.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        Node list1 = myFirst;
        while(list1 != null)
        {
            result.append(list1.info);
            list1 = list1.next;
        }
        return result.toString();
    }
}
