package Resources;

public class Node {


        Object data;

    public Object getData() {
        return data;
    }


    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    Node next;//creates a node

        // Constructor
        Node(Object d)
        {
            data = d;
            next = null;
        }

}
