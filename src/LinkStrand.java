import java.util.*;

public class LinkStrand implements IDnaStrand {

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

    public LinkStrand(){
        this("");
    }

    public LinkStrand(String s){
        initialize(s);
    }

    @Override
    public long size() {
        return mySize;
    }

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

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        Node a = new Node(dna, null);
        myLast.next = a;
        myLast = myLast.next;
        mySize += dna.length();
        myAppends ++;
        return this;
    }


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

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        if(index < 0 || index > mySize-1) throw new IndexOutOfBoundsException();
        while(myIndex < index && index != myIndex)
        {
            myIndex++;
            myLocalIndex ++;

            if(myCurrent.info.length() <= myLocalIndex && myCurrent.next != null)
            {
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
            }
        }
        return myCurrent.info.charAt(myLocalIndex);
    }

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
