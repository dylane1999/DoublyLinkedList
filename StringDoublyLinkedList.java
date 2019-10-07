//package com.homework1;


import java.util.ArrayList;

class StringDoublyLinkedList {

    public boolean ifUndone;
    //  public ArrayList<Node> deletedItems = new ArrayList<Node>();
    public int deletedSize;
    /**
     * A private class to represent a link in the linked list.
     */
    private class Node {
        String value;

        Node next;
        Node previous;
        // FIXME add additional member variables here

        Node(String value) {
            this.value = value;

        }
    }

    private int size = 0;
    private Node head = null;
    private Node tail = null;
    private Node deletedHead = null;
    private Node deletedTail = null;
    public int deleteindex = 0;

    // FIXME add addition member variables here


    /**
     * Add a String to the end of the list.
     *
     * @param value The String to add.
     */
    public void add(String value) {
        // FIXME to be updated for part a.
        Node newNode = new Node(value);
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
            this.tail.previous = null;
            this.tail.next = null;

        } else {
            this.tail.next = newNode;
            newNode.previous= tail;
            this.tail = newNode;
            newNode.next = null;
        }
        this.size += 1;
    }

    /**
     * Get the number of elements in the list.
     *
     * @return The number of elements in the list.
     */
    public int size() {
        return this.size;
    }

    /**
     * Get the element at the specifiedgetde index.
     *
     * Assumes the index < this.size().
     *
     * @param index The index of the desired element.
     * @return The String at that index.
     */

    public String getdeleted(int index) {
        return this.getDeletedNode(index).value;
    }

    public String get(int index) {
        return this.getNode(index).value;
    }


    /**
     * Remove the element at the specified index.
     *
     * Assumes the index < this.size().
     *
     * @param index The index of the element to remove.
     */
    public void remove(int index) {
        // FIXME to be updated for part a.
        if (index == 0) {
            if (size ==1){
                this.head = null;
            }
            else{
                this.head = this.head.next;
                head.previous = null;
            }

        }
        else {
            Node node = this.getNode(index - 1);
            // Node node1 = this.getNode(index);
            if (node.next == tail) {
                this.tail = node;
                this.tail.next = null;
            }
            else {
                node.next = node.next.next;
                //System.out.println(node1);
                // node1.previous = node1.previous.previous;
                node.next.previous = node;
            }
        }
        this.size--;
    }

    /**
     * A helper function to get the Node at a given index.
     *
     * Assumes the index < this.size().
     *
     * @param index The index of the desired Node.
     * @return The Node.
     */

    private Node getNode(int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    private Node getDeletedNode(int index) {
        Node curr = deletedHead;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    /**
     * Undo an action.
     *
     * @return True if there was an action to undo; false otherwise.
     */
    public boolean undo() {
        if (size>0) {
            if (size == 1) {
                if (deletedSize == 0){
                    deletedHead = this.tail;
                    deletedTail = this.tail;
                    deletedTail.previous = null;
                    deletedTail.next = null;
                }
                else {
                    this.tail.previous = deletedTail;
                    deletedTail.next = this.tail;
                    deletedTail = this.tail;
                    deletedTail.next = null;

                }
                this.tail = null;
                this.head = null;
            } else {
                if (deletedSize == 0){
                    deletedHead = this.tail;
                    deletedTail = this.tail;
                    this.tail.previous.next= null;
                    this.tail = this.tail.previous;
                    deletedTail.previous = null;
                    deletedTail.next = null;
                }
                else {
                    this.tail.previous.next= null;
                    // this.tail.previous = deletedTail;
                    deletedTail.next = this.tail;
                    this.tail = this.tail.previous;
                    deletedTail.next.previous = deletedTail;
                    deletedTail = deletedTail.next;
                    deletedTail.next = null;


                }
            }
            deletedSize ++;
            size--;
            ifUndone = true;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Redo an action.
     *
     * @return True if there was an action to redo; false otherwise.
     */



    // deletedNode.nexta\

    //  deletedNode.previous = this.tail;
    // this.tail.next = deletedNode;
    // this.tail = deletedNode;
    // deleteindex++;


    public boolean redo() {

        if ((ifUndone) & (deletedSize > 1)){
            if ( (size == 0) &(deletedSize == 1 ) ){
                Node deletedNode = new Node(getdeleted(deletedSize-1));
                this.head = deletedNode;
                this.tail = deletedNode;
                deletedNode.next = null;
                deletedNode.previous = null;
                deletedHead = null;
                deletedTail = null;
            }
            else if ((size == 0) &(deletedSize > 1 )){
               // Node deletedNode = new Node(getdeleted(deletedSize-1));
                this.head = deletedTail;
                this.tail = deletedTail;
                deletedTail.previous.next = null;
                deletedTail = deletedTail.previous;
                this.head.next = null;
                this.tail.previous = null;
                //deletedTail = deletedNode.previous;
              //  deletedNode.previous= null;
              //  deletedNode.previous.next = null;
            }
            else if ((size > 0) & (deletedSize > 1 )){
               // Node deletedNode = new Node(getdeleted(deletedSize-1));
                //deletedNode.next = null;
                deletedTail.next = null;
                deletedTail.previous.next = null;
                this.tail.next = deletedTail;
                deletedTail = deletedTail.previous;
                deletedTail.previous= this.tail;
                this.tail.next.previous = this.tail;
                this.tail = this.tail.next;

               // this.tail.next = deletedNode;
                //  deletedTail.next= null;
            }
            deletedSize--;
            size++;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Record an action.
     *
     * @param value The action to record.
     */
    public void doAction(String value) {
        // FIXME to be updated for part a.
        Node newNode = new Node(value);
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
            this.head.previous = null;
            this.tail.next = null;

        } else {
            this.tail.next = newNode;
            newNode.previous= tail;
            this.tail = newNode;
            newNode.next = null;
        }
        this.size ++;
        //deletedItems.clear();
        ifUndone = false;
        deletedSize = 0;
        deletedHead = null;
        deletedTail = null;

    }

    /**
     * Get the number of actions recorded. Does *not* include actions that were undone.
     *
     * @return The number of actions recorded.
     */
    public int getNumActions() {
        return size;
    }

    /**
     * Get the action at the specified index.
     *
     * Assumes the index < this.getNumActions().
     *
     * @param index The index of the desired action.
     * @return The action (String).
     */
    public String getAction(int index) {
        return this.getNode(index).value;

    }


    /**
     * Print out the list. Note this function assumes that all your pointers are correct.
     */
    public void print() {
        Node curr = this.head;
        while (curr != null) {
            System.out.print(curr.value);
            System.out.print(", ");
            curr = curr.next;
        }
        System.out.println();
    }

    public void printDeleted() {
        Node curr = this.deletedHead;
        while (curr != null) {
            System.out.print(curr.value);
            System.out.print(", ");
            curr = curr.next;
        }
        System.out.println();
    }

    /**
     * A small test case for this linked list.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // tests for our doubly linked list
        StringDoublyLinkedList list = new StringDoublyLinkedList();
        list.add("42");
        list.add("37");
        list.add("61");
        list.add("13");
        list.print(); // should be 42, 37, 61, 13
        //System.out.println(list.get(1));
        //    System.out.println(list.size());

        list.remove(0);
        list.print(); // should be 37, 61, 13
        //  System.out.println(list.size());

        list.remove(1);
        list.print(); // should be 37, 13

        list.remove(1);
        list.print(); // should be 37

        list.remove(0);
        list.print(); // (empty list)

        StringDoublyLinkedList actions = new StringDoublyLinkedList();

        actions.doAction("create outline");

        actions.doAction("write introduction paragraph");

        actions.doAction("write paragraph 1a");
        // outline intro 1b 2b 3
        //    ]\\\\\

        actions.undo();

        actions.doAction("write paragraph 1b");

        actions.doAction("write paragraph 2a");

        actions.undo();

        actions.undo();

        actions.redo();

        actions.doAction("write paragraph 2b");

        actions.doAction("write paragraph 3");

        actions.doAction("write paragraph 4");

        actions.undo();

        actions.doAction("write conclusion paragraph");

        actions.doAction("add something about how long this assignment took");
        // actions.print();

        actions.undo();

        actions.undo();

        actions.undo();

        actions.undo();

        actions.undo();

        actions.undo();

        System.out.println(actions.undo());
        // System.out.println();
        actions.print();
        //actions.printDeleted();

        // !actions.undo();

        actions.redo();

        actions.redo();

        actions.redo();

        actions.redo();

        actions.redo();

        actions.redo();

        //!actions.redo();



        int n = actions.getNumActions();
        for (int i = 0; i < n; i++) {
            System.out.println(actions.getAction(i));
        }
        //System.out.println(actions.getNumActions());
       // UndoRedoTest.main();



    }
}
