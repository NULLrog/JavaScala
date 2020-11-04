package com.syngly_linked_list;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
        l.insertElement(10);
        l.insertElement(15);
        l.insertElement(13);
        l.showAll();
        l.insertElement(11, 2);
        l.showAll();
        l.showElement(10);
        l.showElement(3);
        l.deleteElement();
        l.showAll();
        l.deleteElement(0);
        l.showAll();
        l.sort();
        l.showAll();
        l.forEach(new IForEach<Integer>() {
            @Override
            public Integer toDo(Integer data) {
                return data * 2;
            }
        });
        l.showAll();
    }
}
