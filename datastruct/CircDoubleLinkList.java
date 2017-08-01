package datastruct;

public class CircDoubleLinkList {

    private DoubleNode first;
    private int size;

    public CircDoubleLinkList() {
        clear();
    }

    public CircDoubleLinkList(Object... list) {
        first = new DoubleNode(list[0]);
        DoubleNode current = first;
        DoubleNode prev = first;
        for (int i = 1; i < list.length; i++) {
            current = new DoubleNode(list[i]);
            prev.next = current;
            current.prev = prev;
            prev = current;
        }
        current.next = first;
        first.prev = current;
        size = list.length;
    }
//FOR DEVELOPMENT VERSION ONLY

    public DoubleNode get() {
        return first;
    }
//============================

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public DoubleNode get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        index %= size;
        DoubleNode node = first;
        for (; index > 0; index--) {
            node = node.next;
        }
        return node;
    }

    public int find(Object item) {
        DoubleNode current = first.next;
        for (int i = 0; i < size; i++) {
            if (current.data == item) {
                return i;
            }
            current = current.next;
        }
        return 0;//###################################
    }

    public boolean contains(Object item) {
        return find(item) != -1;//####################
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
        DoubleNode curr = first;
        for (int i = 0; i < size; i++) {
            str += curr.data + ", ";
            curr = curr.next;
        }
        str += "\b\b]";
        return str;
    }

}
