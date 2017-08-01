package datastruct;

public class DoubleLinkList {

    private DoubleNode first;
    private int size;

    public DoubleLinkList() {
        clear();
    }

    public DoubleLinkList(Object... list) {
        first = new DoubleNode(list[0]);
        DoubleNode current = first;
        DoubleNode next;
        for (int i = 1; i < list.length; i++) {
            next = new DoubleNode(list[i]);
            current.next = next;
            next.prev = current;
            current = next;
        }
        size = list.length;
        /* faster than 
        * for(Object item : list)
        *     push(item);
        */
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public DoubleNode get(int index) {
        if (index < 0 || index > this.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        DoubleNode node = first;
        for (; index > 0; index--) {
            node = node.next;
        }
        return node;
    }

    public int find(Object item) {
        DoubleNode current = first;
        for (int i = 0; i < this.size(); i++) {
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
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }
        DoubleNode temp = new DoubleNode(data);
        if (size > 0) {
            DoubleNode current = first;
            if (index > 0) {
                for (int i = 0; i < index - 1; i++) {
                    current = current.next;
                }
                temp.next = current.next;
                temp.prev = current;
                current.next = temp;
                if (temp.next != null) {
                    temp.next.prev = temp;
                }
            } else {
                temp.next = first;
                first.prev = temp;
                first = temp;
            }
        } else {
            first = temp;
        }
        size++;
    }

    public void push(Object data) {
        push(data, this.size());
    }

    public Object pop(int index) {
        if (index < 0 || index > this.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        Object data;
        if (index > 0) {
            DoubleNode current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            data = current.next.data;
            current.next = current.next.next;
            if (current.next != null) {
                current.next.prev = current;
            }

        } else {
            data = first.data;
            first = first.next;
            first.prev = null;
        }
        size--;
        return data;
    }

    public Object pop() {
        return pop(this.size() - 1);
    }
    
    public final void clear(){
        first = null;
        size = 0;
    }

    public String toString() {
        if (this.size() == 0) {
            return "[]";
        }
        String str = "[";
        DoubleNode curr = first;
        while (curr != null) {
            str += curr.data.toString() + ", ";
            curr = curr.next;
        }
        str += "\b\b]";
        return str;
    }
}
