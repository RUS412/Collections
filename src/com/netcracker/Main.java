package com.netcracker;

import com.netcracker.List.MyLinkedList;
import com.netcracker.List.CompareLinkList;
import com.netcracker.test.Tester;

import java.util.*;

public class Main {
    public static  void main(String[] args) {
        MyLinkedList<Integer> test = new MyLinkedList<Integer>();

       
        //add(e)
       for (int i = 0; i < 5; ++i)
            test.add(i);
        System.out.println(test);
        System.out.println(test.size());
        //add(i, e)
        for (int i = 0; i < 5; ++i)
            test.add(test.size()- i, i + 5);

        System.out.println(test);
        System.out.println(test.size());

      //  get(i)
        for (int i = 0; i < test.size() / 2; ++i)
           System.out.print(test.get(test.size() - i - 1) + " ");
       System.out.println();

        //toArray()
        Object[] arr = test.toArray();
        for (Object i : arr)
            System.out.print(i + " ");

        System.out.println();

        //indexOf()
        for (int i = 0; i < 5; ++i)
            System.out.print(test.indexOf(4 - i) + " ");
        System.out.println();

        //remove
        for (int i = 0; i < test.size(); ++i)
            if (test.get(i) % 2 == 0) {
                test.remove(i);
                --i;
            }
        System.out.println(test);

        //set
        for (int i = 0; i < test.size(); ++i)
            test.set(test.size() - i - 1, i + 100);
        System.out.println(test);

        //Iterator
        for (int c : test)
            System.out.print(c + " ");
        System.out.println();



        //clear()

        test.clear();
        System.out.println(test.toString());
        System.out.println("_________________________________________________");

        System.out.println("Compare MyLinkedList and LinkedList");
        CompareLinkList compareLinkList = new CompareLinkList(100000);
        compareLinkList.compareList();


        Tester testClass = new Tester(600000);
        System.out.println("_________________________________________________");

        System.out.println("Compare Add and Remove for begin of Lists:");
        testClass.compareAddListStart("aaa");
        testClass.compareRemoveListStart();
        System.out.println("Compare Add and Remove for middle of Lists:");
        testClass.compareAddList("aaa",300000);
        testClass.compareRemoveList(300000);
        System.out.println("Compare Add and Remove for end of Lists:");
        testClass.compareAddList("aaa",600000);
        testClass.compareRemoveListEnd();

        System.out.println("_______________");
        System.out.println("Compare HashSet, LinkedHashSet and TreeSet");
        testClass.compareAddSet("aaa");
        testClass.compareContainsSet("aaa");
        testClass.compareRemoveSet("aaa");


        System.out.println("_______________");
        System.out.println("Compare HashMap, LinkedHashMap and TreeMap");
        testClass.comparePutMap("aaa");
        testClass.compareGetMap("aaa");
        testClass.compareRemoveMap("aaa");


    }
 /*ВЫВОДЫ
 а) add и Remove у  LinkedList более эффективены в начале ,а у ArrayList  в середине и менее значительно в конце
 Следовательно LinkedList более эффективен при   добавлении/удалении элементов ,а ArrayList при работе с индексами

 b)HashSet лидирует в поиске,а в   добавлении и удалении LinkedHashSet,TreeSet медленнее из-за сортировки, которую он должен выполнять при каждой вставке
  (HashSet  не отсортирован в отличие от TreeSet,а LinkedHashSet что-то между)
 c)LinkedHashMap лидирует по всем параметрам, HashMap на2м месте,его порядок ключей  произвольный ,TreeMap  отстаёт, но метод Get покаывает себя неплохо,
 TreeMap сортируется с использованием естественного порядка его ключей
*/
    }

