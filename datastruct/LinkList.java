package datastruct;

public class LinkList {

    protected Node first;
    private int length;

    public LinkList() {
        clear();
    }

    public LinkList(Object... list) {
        first = new Node(list[0]);
        Node current = first;
        for (int i = 1; i < list.length; i++) {
            current.next = new Node(list[i]);
            current = current.next;
        }
        length = list.length;
        /* faster than
        * for (Object item : list) {
        *     push(item);
        */
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public Node get(int index) {
        if (index < 0 || index > this.length() - 1) {
            throw new IndexOutOfBoundsException();
        }
        Node node = first;
        for (; index > 0; index--) {
            node = node.next;
        }
        return node;
    }

    public int find(Object item) {
        Node current = first;
        for (int i = 0; i < this.length(); i++) {
            if (current.data == item) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }
    
    public boolean contains(Object item){
        return find(item) != -1;
    }

    public void push(Object data, int index) {
        if (index < 0 || index > this.length()) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = new Node(data);
        Node current = first;
        if (index > 0) {
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            temp.next = current.next;
            current.next = temp;
        } else {
            temp.next = first;
            first = temp;
        }
        length++;
    }

    public void push(Object data) {
        push(data, this.length());
    }

    public Object pop(int index) {
        if (index < 0 || index > this.length() - 1) {
            throw new IndexOutOfBoundsException();
        }
        Object data;
        if (index > 0) {
            Node current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            data = current.next.data;
            current.next = current.next.next;
        } else {
            data = first.data;
            first = first.next;
        }
        length--;
        return data;
    }

    public Object pop() {
        return pop(this.length() - 1);
    }
    
    public final void clear(){
        first = null;
        length = 0;
    }

    public String toString() {
        if (this.length() == 0) {
            return "[]";
        }
        String str = "[";
        Node curr = first;
        while (curr != null) {
            str += curr.data.toString() + ", ";
            curr = curr.next;
        }
        str += "\b\b]";
        return str;
    }
}
