package datastruct;

public class BinaryNode {

    private BinaryNode left;
    private BinaryNode right;
    private BinaryNode parent;
    private int data;

    public BinaryNode() {
        left = null;
        right = null;
        parent = null;
    }

    public BinaryNode(int data) {
        left = null;
        right = null;
        parent = null;
        this.data = data;
    }

    public BinaryNode(int data, BinaryNode left, BinaryNode right) {
        this.data = data;
        if (left != null) {
            this.left = left;
            left.parent = this;
        }
        if (right != null) {
            this.right = right;
            right.parent = this;
        }
        parent = null;
    }

    public int getData() {
        return data;
    }

    public BinaryNode setData(int data) {
        this.data = data;
        return this;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public BinaryNode setLeft(BinaryNode left) {
        this.left = left;
        left.parent = this;
        return this;
    }

    public BinaryNode getRight() {
        return right;
    }

    public BinaryNode setRight(BinaryNode right) {
        this.right = right;
        right.parent = this;
        return this;
    }

    public BinaryNode getParent() {
        return parent;
    }

    public BinaryNode setParent(BinaryNode parent) {
        if (this.parent.left == this) {
            this.parent.left = null;
        } else {
            this.parent.right = null;
        }
        this.parent = parent;
        return this;
    }

    public int recursiveHeight(BinaryNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(recursiveHeight(node.getLeft()), recursiveHeight(node.getRight()));
    }

    public int height() {
        return recursiveHeight(this);
    }

    public String toString() {
        return data + "";
    }
}
