package com.netcracker.List;



import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class MyLinkedList<E> implements ILinkedList<E>{
    private int size ;
    private Node<E> first;

    private Node<E> last;

    Node<E> getHelper(int index){
        Node node = first;
        for(int i=0;i<index;i++){
            node=node.next;
        }
        return node;
    }

    @Override
    public void add(E element) {
        Node<E> node;
        if(size!=0){
            node = new Node<>(element,last,null);
            last.next = node;

        }
        else{
            node = new Node<>(element,null,null);
            first = node;
        }
        size++;
        last = node;

    }


    private boolean saneIndex(int index) {
        return index >= 0 && index < size;
    }
    @Override
   public void add(int index, E element) {
        if (!saneIndex(index) && index != size) return;

            Node<E> node;
            if (index == 0) {
                node = new Node<>(element, null, first);
                first.prev = node;
                first = node;
            } else if (index == size) {
                node = new Node<>(element, last, null);
                last.next = node;
                last = node;
            } else {
                Node<E> cur =getHelper(index);
                Node<E> prev = cur.prev;
                cur.prev = new Node<>(element, prev, cur);
                prev.next = cur.prev;
            }
            size++;
        }




    @Override
    public void clear() {
        first.next = last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (!saneIndex(index) && index != size)return null;

            Node<E> get = first;
            while (index != 0) {
                get = get.next;
                index--;
            }
            return get.value;


    }
    @Override
    public E set(int index, E element) {
        if(!iChange(index)){
            return null;
        }
        else{
            Node<E> set =getHelper(index);
            E indexed = set.value;
            set.value = element;
            return indexed;
        }
    }

    @Override
    public int indexOf(E element) {
        int i = 0;
        for (E el : this) {
            if (el.equals(element))
                return i;
            ++i;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if (!iGet(index)){
            return null;
        }
        else{
            Node<E> cur = getHelper(index);
            Node<E> prev1;
            Node<E> next1;

            if(index==0){
                first=first.next;
            }
            else if(index == (size-1)) {
                last = cur.prev;

            }
            else{
                prev1 = cur.prev;
                next1 = cur.next;
                prev1.next = next1;
                next1.prev = prev1;
            }
            E result = cur.value;
            cur.value = null;
            cur.next = null;
            cur.prev = null;
            size-=1;
            return result;

        }
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        if (size == 0)
            return null;

        E[] arr = (E[]) new Object[size];

        int i = 0;
        for (E el : this)
            arr[i++] = el;

        return arr;
    }

    public Iterator<E> iterator(){
        return new MyIterator<E>(0);
    }
    public boolean iChange(int value){
        return (value>=0)&&(value<=size);
    }
    public boolean iGet(int value){
        return (value>=0)&&(value<size);
    }

    private class MyIterator<E> implements MyListIterator{
        private int curI;
        private int nextI;
        private Node<E> nextE;
        private Node<E> curE;
        private int iterSize;

        public MyIterator(int index){
            iterSize = size;
            nextI=index;
            if(iterSize==0){
                nextE=null;
            }
            else{
                nextE=(Node<E>)getHelper(nextI);
            }
        }



        public boolean hasNext(){
            if (nextI<iterSize){
                return true;
            }
            else {return false;}
        }
        public E next() {
            if (!hasNext()) {
                System.out.println("Next element not found!");
                return null;
            }
            else{
                curI = nextI;
                nextI += 1;
                curE = nextE;
                nextE = nextE.next;
                return curE.value;
            }

        }


        public void remove(){
            if (curE == null){
                throw new NoSuchElementException("The element isn't found");
            }
            if(curI==nextI&&hasNext()){
                nextI+=1;
                nextE=nextE.next;
                iterSize-=1;
            }
            MyLinkedList.this.remove(curI);
            curE=null;
        }
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "MyLinkedList{", "}");
        for (E el : this)
            sj.add(el.toString());
        return sj.toString();
    }

    private  class Node<E> {
        private    E value;
        private  Node<E> prev;
        private    Node<E> next;

        public Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

    }


}

