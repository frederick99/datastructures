package datastruct;

public class Queue {

    private Node first, last;
    private int length;

    public Queue() {
        clear();
    }

    public Queue(Object... list) {
        first = new Node(list[0]);
        Node current = first;
        for (int i = 1; i < list.length; i++) {
            current.next = new Node(list[i]);
            current = current.next;
        }
        last=current;
        length = list.length;
        /* faster than
        * for (Object item : list) {
        *     enqueue(item);
        */
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void enqueue(Object item) {
        Node temp = new Node(item);
        if (length > 0) {
            last.next = temp;
            last = temp;
        } else {
            first = temp;
            last = temp;
        }
        length++;
    }

    public Object dequeue() {
        if (length == 0) {
            System.err.println("EmptyQueueError: Trying to dequeue from an empty queue.");
            return null;
        }
        Object data = first.data;
        first = first.next;
        length--;
        return data;
    }

    public final void clear() {
        first = null;
        last = null;
        length = 0;
    }

    public String toString() {
        if (this.length() == 0) {
            return "{}";
        }
        String str = "{";
        Node curr = first;
        for (int i = 0; i < this.length(); i++) {
            str += curr.data + ", ";
            curr = curr.next;
        }
        str += "\b\b}";
        return str;
    }
}
