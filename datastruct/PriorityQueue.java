package datastruct;

import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * 
 * @author Naveen
 */
public class PriorityQueue {

    private PQNode first, last;
    private int length;

    public PriorityQueue() {
        clear();
    }

    /**
     * Initializes the priority queue from a mapping, where
     * 
     * <ul>
     *   <li>Keys correspond to items to be queued.</li>
     *   <li>Values correspond to their respective priorities.</li>
     * </ul>
     * 
     * @param map the map object mapping an item to its priority
     * 
     */
    public PriorityQueue(HashMap<Object, Integer> map) {
        map.forEach((Object item, Integer priority) -> {
            enqueue(item, priority);
        });
    }

    public final int length() {
        return length;
    }

    public final boolean isEmpty() {
        return length == 0;
    }

    public void enqueue(Object item, int priority) {
        PQNode temp = new PQNode(item, priority);
        if (length > 0) {
            PQNode curr = last;
            while (curr.priority < priority) {
                curr = curr.prev;
                if (curr == null) {
                    temp.next = first;
                    first.prev = temp;
                    first = temp;
                    length++;
                    return;
                }
            }
            if (curr == last) {
                temp.prev = last;
                last.next = temp;
                last = temp;
            } else {
                curr.next.prev = temp;
                temp.next = curr.next;
                temp.prev = curr;
                curr.next = temp;
            }
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
        if (first != null) {
            first.prev = null;
        }
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
        PQNode curr = first;
        for (int i = 0; i < this.length(); i++) {
            str += curr.toString() + ", ";
            curr = curr.next;
        }
        str += "\b\b}";
        return str;
    }

    private class PQNode {

        PQNode prev, next;
        Object data;
        int priority;

        PQNode() {
            prev = null;
            next = null;
            data = null;
            priority = 0;
        }

        PQNode(Object data, int priority) {
            this();
            this.data = data;
            this.priority = priority;
        }

        public String toString() {
            return data.toString() + String.format(" (%d)", priority);
        }
    }

    /**
     * Runs the given action against the queued items in the order they
     * would be dequeued.
     * <p>Note that the function need not use both parameters; they must
     * be specified nevertheless.
     * @param action  a lambda function that takes two parameters:
     * an {@code Object} and an {@code int}
     */
    public final void forEach(BiConsumer<Object, Integer> action) {
        PQNode curr = first;
        while(curr!=null){
            action.accept(curr.data, curr.priority);
            curr = curr.next;
        }
    }
}
