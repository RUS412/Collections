package com.netcracker.List;

import java.util.Iterator;
public interface MyListIterator<E> extends Iterator<E> {

    boolean hasNext();

    E next();
    void remove();
}
