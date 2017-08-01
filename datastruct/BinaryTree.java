package datastruct;

public class BinaryTree {

    final private BinaryNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(BinaryNode root) {
        this.root = root;
    }

    public BinaryTree(int data) {
        root = new BinaryNode(data);
    }

    public BinaryTree(int data, BinaryNode left, BinaryNode right) {
        root = new BinaryNode(data, left, right);
    }
    
    public int height(){
        return root.height();
    }

    StringBuilder toString(BinaryNode node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        BinaryNode right = node.getRight(), left = node.getLeft();
        Object value = node.getData();
        if (right != null) {
            this.toString(right, new StringBuilder().append(prefix).append(isTail ? "│　　　" : "　　　　"), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(value.toString()).append("\n");
        if (left != null) {
            this.toString(left, new StringBuilder().append(prefix).append(isTail ? "　　　　" : "│　　　"), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return this.toString(root, new StringBuilder(), true, new StringBuilder()).toString();
    }
}
