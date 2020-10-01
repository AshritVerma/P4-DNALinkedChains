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

    public LinkStrand(){
        this("");
    }

    public LinkStrand(String s){
        initialize(s);
    }

    @Override
    public long size() {
        return myFirst.info.length();
    }

    @Override
    public void initialize(String source) {
        Node a = new Node(source, myFirst);
    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        return null;
    }

    @Override
    public IDnaStrand reverse() {
        return null;
    }

    @Override
    public int getAppendCount() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }
}
