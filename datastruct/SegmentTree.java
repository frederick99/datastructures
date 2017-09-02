package datastruct;

import java.util.function.BiFunction;

public class SegmentTree {
    private int MAX_SIZE;
    private int[] A;
    private BiFunction<Integer, Integer, Integer> action;

    public SegmentTree(int... items) {
        this((BiFunction<Integer, Integer, Integer>) (Integer x, Integer y) -> x + y, items);
    }

    public SegmentTree(BiFunction<Integer, Integer, Integer> action, int... items) {
        int N = items.length;
        //int height = (int) Math.ceil(Math.log(N) / Math.log(2));                           // height
        //MAX_SIZE = (int) Math.pow(2, MAX_SIZE + 1) - 1 - 2*((int)Math.pow(2,MAX_SIZE)-N);  // no. of nodes
        MAX_SIZE = (N << 1) - 1;
        this.action = action;
        A = new int[MAX_SIZE];
        A[0] = build(0, 0, N - 1, items);
    }

    public void setAction(BiFunction action) {
        this.action = action;
    }

    private int build(int i, int l, int r, int[] items) {
        if (l == r) {
            A[i] = items[l];
            return A[i];
        }
        int m = (l + r) / 2;
        A[i] = (int) action.apply(build(2 * i + 1, l, m, items), build(2 * i + 2, m + 1, r, items));
        return A[i];
    }

    public Object applyActionOnRange(int l, int r) {
        return null;
    }

    private Object helper(int l, int r, int b, int e, int i) {
        if (l == b && r == e)
            return A[i];
        //if(l<b && r>e)
        //    return action.apply(helper(b,l-1,b,e,2*i+1),
        //            action.apply(helper()))
        return null;
    }

    StringBuilder toString(int node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        int right = 2 * node + 2, left = 2 * node + 1;
        int value = A[node];
        if (right < MAX_SIZE) {
            this.toString(right, new StringBuilder().append(prefix).append(isTail ? "│　　　" : " 　　　"), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(Integer.toString(value)).append("\n");
        if (left < MAX_SIZE) {
            this.toString(left, new StringBuilder().append(prefix).append(isTail ? " 　　　" : "│　　　"), true, sb);/*　　　*/
        }
        return sb;
    }

    @Override
    public String toString() {
        if (A == null) {
            return "Tree Size: ";
        }
        return "Tree Size: " + "\n" + this.toString(0, new StringBuilder(), true, new StringBuilder()).toString();
    }
}
