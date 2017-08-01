package datastruct;

public class Node {

    public Node next;
    public Object data;

    public Node() {
        next = null;
        data = null;
    }

    public Node(Object data) {
        this();
        this.data = data;
    }

    public String toString() {
        return data.toString();
    }
}
