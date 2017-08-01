/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastruct;

/**
 *
 * @author Naveen
 */
public class AVLNode {

    private AVLNode left;
    private AVLNode right;
    private AVLNode parent;
    private int data;
    private int height;

    public AVLNode() {
        left = null;
        right = null;
        parent = null;
        height = 0;
    }

    public AVLNode(int data) {
        left = null;
        right = null;
        parent = null;
        this.data = data;
        height = 0;
    }

    public int getData() {
        return data;
    }

    public AVLNode setData(int data) {
        this.data = data;
        return this;
    }

    int getHeight() {
        return height;
    }

    void setHeight() {
        boolean r = this.right == null, l = this.left == null;
        if (r && l) {
            this.height = 0;
        } else if (r) {
            this.height = this.getLeft().getHeight() + 1;
        } else if (l) {
            this.height = this.getRight().getHeight() + 1;
        } else {
            this.height = Math.max(this.getLeft().getHeight(), this.getRight().getHeight()) + 1;
        }
    }

    void incrHeight() {
        height++;
    }

    public int getBalanceFactor() {
        boolean r = this.right == null, l = this.left == null;
        if (r && l) {
            return 0;
        } else if (r) {
            return this.left.height + 1;
        } else if (l) {
            return -this.right.height - 1;
        }
        return this.left.height - this.right.height;
    }

    public AVLNode getLeft() {
        return left;
    }

    public AVLNode setLeft(AVLNode left) {
        this.left = left;
        if (left != null) {
            left.parent = this;
        }
        return this;
    }

    public AVLNode getRight() {
        return right;
    }

    public AVLNode setRight(AVLNode right) {
        this.right = right;
        if (right != null) {
            right.parent = this;
        }
        return this;
    }

    public AVLNode getParent() {
        return parent;
    }

    public AVLNode setParent(AVLNode parent, boolean left) {
        if (parent != null) {
            if (left && (parent.left != null)) {
                System.out.println("Node already has a left child.");
                return this;
            }
            if ((!left) && (parent.right != null)) {
                System.out.println("Node already has a right child.");
                return this;
            }
            if (this.parent.left == this) {
                this.parent.left = null;
            } else {
                this.parent.right = null;
            }
            this.parent = parent;
            if (left) {
                parent.left = this;
            } else {
                parent.right = this;
            }
        } else {
            this.parent = null;
        }
        return this;
    }

    int recursiveHeight(AVLNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(recursiveHeight(node.getLeft()), recursiveHeight(node.getRight()));
    }

    public int recHeight() {
        return recursiveHeight(this);
    }

    public int nonRecHeight() {
        if (this == null) {
            System.out.println(-1);
        }
        return height;
    }

    public String toString() {
        return data + "";
    }
}
