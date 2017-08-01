package datastruct;

public class Stack {

    private Node top;
    private int size;

    public Stack() {
        clear();
    }

    public Stack(Object... list) {
        top = new Node(list[0]);
        Node current;
        for (int i = 1; i < list.length; i++) {
            current = new Node(list[i]);
            current.next = top;
            top = current;
        }
        size = list.length;
        /* faster than
        * for (Object item : list) {
        *     push(item);
        */
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(Object data) {
        if (size == 0) {
            top = new Node(data);
        } else {
            Node curr = new Node(data);
            curr.next = top;
            top = curr;
        }
        size++;
    }

    public Object pop() {
        if (size == 0) {
            System.err.println("EmptyStackError: Trying to pop from an empty stack.");
            return null;
        }
        Object data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public final void clear() {
        top = null;
        size = 0;
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }
        Node curr = top;
        String s = "[";
        while (curr != null) {
            s += curr.data.toString() + ", ";
            curr = curr.next;
        }
        return s + "\b\b]";
    }
}
