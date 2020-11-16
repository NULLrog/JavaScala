package com.syngly_linked_list;

public class SinglyLinkedList<T extends Comparable<T>>{
    static class Element<T extends Comparable<T>> implements Comparable<SinglyLinkedList.Element<T>> {
        private T data;
        private Element<T> next = null;

        @Override
        public int compareTo(Element<T> o) {
            return data.compareTo(o.data);
        }
    }

    Element<T> head = null;
    Element<T> tail = null;
    int count = 0;

    T getData(int index){
        Element<T> current = head;
        for (int i = 0; current != null; i++) {
            if (i == index) {
                return current.data;
            }
            current = current.next;
        }
        System.out.println("Element ["+index+"] is not exists!");
        return null;
    }

    void insertElement(T data) {
        Element<T> newElement = new Element<>();
        newElement.data = data;
        if (head == null) {
            head = newElement;
            tail = head;
        }
        else {
            tail.next = newElement;
            tail = newElement;
        }
        System.out.println("Element ["+count+"] is " + newElement.data + " was inserted!");
        count++;
    }

    void insertElement(T data, int index) {
        Element<T> newElement = new Element<>();
        newElement.data = data;
        if ((count < index + 1) || index < 0) {
            System.out.println("Index ["+index+"] out of range!");
        }
        else {
            Element<T> current = head;
            for (int i = 0; current != null; i++) {
                if (i == index - 1) {
                    newElement.next = current.next;
                    current.next = newElement;
                    System.out.println("Element ["+index+"] is " + newElement.data + " was inserted!");
                    return;
                }
                current = current.next;
            }
            count++;
        }
    }

    boolean deleteTest() {
        if(head == null){
            System.out.println("Elements doesn't exists!");
            return false;
        }
        else if (head == tail) {
            head = null;
            tail = null;
            System.out.println("List had only 1 element! List was deleted");
            count--;
            return false;
        }
        return true;
    }

    void deleteElement() {
        if(deleteTest()) {
            Element<T> current = head;
            for (int i = 0; current.next != null; i++) {
                if(current.next.next == null) {
                    System.out.println("Element ["+(i+1)+"] is " + current.next.data + " was deleted!");
                    current.next = null;
                    return;
                }
                current = current.next;
            }
            count--;
        }
    }

    void deleteElement(int index) {
        if (deleteTest()) {
            if (index == 0) {
                System.out.println("Element ["+index+"] is " + head.data + " was deleted!");
                head = head.next;
            }
            else {
                Element<T> current = head;
                for (int i = 0; current.next != null; i++) {
                    if(index + 1 == i) {
                        System.out.println("Element ["+(i+1)+"] is " + current.next.data + " was deleted!");
                        current.next = current.next.next;
                        return;
                    }
                    current = current.next;
                }
            }
            count--;
        }
    }

    void forEach(IForEach<T> fe){
        Element<T> current = head;
        for (int i = 0; current != null; i++) {
            current.data = fe.toDo(current.data);
            current = current.next;
        }
    }

    void sort() {
        Element<T> current = head;
        Element<T> next = current.next;
        while(next != null)
        {
            while(next != current && next.compareTo(current) < 0)
            {
                T tmp = current.data;
                current.data = next.data;
                next.data = tmp;
                current = current.next;
            }
            current = head;
            next = next.next;
        }
    }

    void showElement(int index) {
        Element<T> current = head;
        for (int i = 0; current != null; i++) {
            if(i == index) {
                System.out.println("Element ["+i+"] is " + current.data);
                return;
            }
            current = current.next;
        }
        System.out.println("Element ["+index+"] is not exists!");
    }

    void showAll() {
        Element<T> current = head;
        System.out.println("All list is:");
        for (int i = 0; current != null; i++) {
            System.out.println("Element ["+i+"] is " + current.data);
            current = current.next;
        }
        System.out.println();
    }
}

