package ua.tqs;
import java.util.*;

public class TqsStack<T> {
    
    private LinkedList<T> linkedlist;
    private int limitSize = -1;

    public TqsStack(){
        linkedlist = new LinkedList<T>();
    }

    public TqsStack(int limit){
        limitSize = limit;
        linkedlist = new LinkedList<T>();
    }

    public T pop(){

        if(linkedlist.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            T pooped = linkedlist.get(linkedlist.size() - 1);
            linkedlist.remove(linkedlist.size() - 1);
            return pooped;
        }
    }

    public Integer size(){
        return linkedlist.size();
    }

    public T peek(){
        if(linkedlist.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return linkedlist.get(linkedlist.size() - 1);
        }
    }

    public void push(T object){
        if(limitSize != -1 && linkedlist.size() >= limitSize) {
            throw new IllegalStateException();
        } else {
            linkedlist.add(object);
        }
    }   

    public Boolean isEmpty(){
        if(linkedlist.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
