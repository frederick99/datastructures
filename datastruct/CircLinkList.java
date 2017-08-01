package datastruct;

public class CircLinkList {

    private Node first;
    private int size;

    public CircLinkList() {
        clear();
    }

    public CircLinkList(Object... list) {
        first = new Node(list[0]);
        Node current = first;
        for (int i = 1; i < list.length; i++) {
            current.next = new Node(list[i]);
            current = current.next;
        }
        current.next = first;
        size = list.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        index %= size;
        Node node = first;
        for (; index > 0; index--) {
            node = node.next;
        }
        return node;
    }

    public int find(Object item) {
        Node current = first.next;
        for (int i = 0; i < size; i++) {
            if (current.data == item) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public boolean contains(Object item) {
        return find(item) != -1;
    }

    public void push(Object data, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        index %= size + 1;
        if (index > 0) {
            Node temp = new Node(data);
            Node current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            temp.next = current.next;
            current.next = temp;
        } else {
            Node temp = new Node(first.data);
            first.data = data;
            temp.next = first.next;
            first.next = temp;
        }
        size++;
    }

    public void push(Object data) {
        push(data, size);
    }

    public void pop(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        index %= size;
        if (index > 0) {
            Node current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        } else {
            first.data = first.next.data;
            first.next = first.next.next;
        }
        size--;
    }

    public void pop() {
        pop(size - 1);
    }

    public final void clear() {
        first = null;
        size = 0;
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }
        String str = "[";
        Node curr = first;
        for (int i = 0; i < size; i++) {
            str += curr.data + ", ";
            curr = curr.next;
        }
        str += "\b\b]";
        return str;
    }
}
