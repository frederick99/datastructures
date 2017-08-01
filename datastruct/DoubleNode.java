package datastruct;

public class DoubleNode {

    public DoubleNode prev, next;
    public Object data;

    public DoubleNode() {
        prev = null;
        next = null;
        data = null;
    }

    public DoubleNode(Object data) {
        this();
        this.data = data;
    }

    public String toString() {
        return data.toString();
    }
}
