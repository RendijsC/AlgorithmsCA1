package Resources;

import com.thoughtworks.xstream.mapper.Mapper;

public class LinkedList<o> {
    private Node head = null;// head of list

    private int nodes = 0;



    public Node getHead() {
        return head;
    }


    public void addNode(Object data) {
        //Create a new node
        Node newNode = new Node(data);

        //Checks if the list is empty
        if(head == null) {
            //If list is empty points to new node
            head = newNode;
        }
        else {
            Node temp = new Node(data);//temp is the thing you want to point to
            Node current = head;

            if (current != null) {//checks if it is null
                while (current.getNext() != null){//keeps going until it find the last node and has null next to it
                    current = current.getNext();
                }
                current.setNext(temp);//points last node to temp making a new node.
            }
        }
        nodes ++;//adds to int nodes
    }


    public Object get(int index) {
        if (head != null) {// checks if head is not null
            if (index == 0)
                return head.getData();
            Node current = null;//if index is 0 returns head and makes current null

            current = head.getNext();//if index is more than 0 sets current to the node after head
            for (int i = 1; i < index; i++) {//does a for loop through the index
                if (current.getNext() == null)
                    return null; // keeps going through the index until next points to null
                current = current.getNext();
            }
            return current.getData();//returns get data for each index
        }
        else{
            return null;
        }
    }

    public void deleteAll (){ //head is not null makes it null
        if (head != null){
            head = null;
        }
    }

    public int listSize(){
        return nodes;
    } //returns the number of nodes in LL







}
