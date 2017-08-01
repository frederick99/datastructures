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
public class AVLTree extends BinaryTree {

    private AVLNode root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public AVLNode probe() {
        return root;
    }

    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }

    public AVLNode find(int item) {
        AVLNode curr = root, parent = root;
        int n;
        while (curr != null) {
            n = curr.getData();
            parent = curr;
            if (n == item) {
                return curr;
            } else if (n < item) {
                curr = curr.getRight();
            } else {
                curr = curr.getLeft();
            }
        }
        return parent;
    }

    public boolean contains(int item) {
        if (root == null) {
            return false;
        }
        return find(item).getData() == item;
    }

    public AVLTree add(int item) {
        if (size == 0) {
            root = new AVLNode(item);
        } else {
            AVLNode curr = root, parent = find(item);
            if (item == parent.getData()) {
                System.out.println("Already exists: " + item);
                size--;
            } else {
                if (item < parent.getData()) {
                    parent.setLeft(new AVLNode(item));
                    curr = parent.getLeft();
                } else {
                    parent.setRight(new AVLNode(item));
                    curr = parent.getRight();
                }
                parent = curr;
                while (parent != root) {
                    if (parent.getParent().getHeight() > parent.getHeight()) {
                        break;
                    }
                    parent = parent.getParent();
                    parent.incrHeight();
                }
                balance(curr);
            }
        }
        size++;
        return this;
    }

    public AVLTree add(int... items) {
        for (int x : items) {
            this.add(x);
        }
        return this;
    }

    public AVLTree addAll(int[] items) {
        for (int x : items) {
            this.add(x);
        }
        return this;
    }

    public AVLTree remove(int item) {
        AVLNode node = find(item);
        if (node.getData() != item) {
            System.out.println("Not found: " + item);
        } else {
            rem(node);
            size--;
        }
        return this;
    }

    public AVLTree remove(int... items) {
        for (int x : items) {
            this.remove(x);
        }
        return this;
    }

    public AVLTree removeAll(int[] items) {
        for (int x : items) {
            this.remove(x);
        }
        return this;
    }

    void rem(AVLNode node) {
        boolean r = node.getRight() == null, l = node.getLeft() == null;
        if (r && l) {
            if (size == 1) {
                root = null;
            } else {
                AVLNode parent = node.getParent();
                if (parent.getLeft() == node) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                AVLNode curr = parent;
                int bf;
                while (curr != null) {
                    parent = curr.getParent();
                    curr.setHeight();
                    bf = curr.getBalanceFactor();
                    if (bf < -1) {
                        if (curr.getRight().getBalanceFactor() == 1) {
                            RL(curr);
                        } else {
                            RR(curr);
                        }
                        //break;
                    } else if (bf > 1) {
                        if (curr.getLeft().getBalanceFactor() == 1) {
                            LL(curr);
                        } else {
                            LR(curr);
                        }
                        //break;
                    }
                    curr = parent;
                }
            }
        } else if (l) {
            remFromRight(node);
        } else if (r) {
            remFromLeft(node);
        } else {
            if (node.getLeft().getHeight() > node.getRight().getHeight()) {
                remFromLeft(node);
            } else {
                remFromRight(node);
            }
        }
    }

    void remFromRight(AVLNode node) {
        AVLNode curr = node.getRight();
        while (curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        int temp = curr.getData();
        curr.setData(node.getData());
        node.setData(temp);
        rem(curr);
    }

    void remFromLeft(AVLNode node) {
        AVLNode curr = node.getLeft();
        while (curr.getRight() != null) {
            curr = curr.getRight();
        }
        int temp = curr.getData();
        curr.setData(node.getData());
        node.setData(temp);
        rem(curr);
    }

    void balance(AVLNode node) {
        AVLNode parent = node.getParent(), curr = node;
        int bf = 0;
        while (parent != root) {
            bf = parent.getBalanceFactor();
            if (bf < -1 || bf > 1) {
                break;
            }
            curr = parent;
            parent = curr.getParent();
        }
        if (parent == root) {
            bf = root.getBalanceFactor();
        }
        if (bf == 2) {
            if (curr.getBalanceFactor() == 1) {
                LL(parent);
            } else {
                LR(parent);
            }
        } else if (bf == -2) {
            if (curr.getBalanceFactor() == 1) {
                RL(parent);
            } else {
                RR(parent);
            }
        }
    }

    void LL(AVLNode node) {
        AVLNode child = node.getLeft(), parent = node.getParent();
        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        } else {
            root = child;
            child.setParent(null, true);
        }
        node.setLeft(child.getRight());
        child.setRight(node);

        node.setHeight();
        child.setHeight();
        while (parent != null) {
            parent.setHeight();
            parent = parent.getParent();
        }
    }

    void RR(AVLNode node) {
        AVLNode child = node.getRight(), parent = node.getParent();
        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        } else {
            root = child;
            child.setParent(null, true);
        }
        node.setRight(child.getLeft());
        child.setLeft(node);

        node.setHeight();
        child.setHeight();
        while (parent != null) {
            parent.setHeight();
            parent = parent.getParent();
        }
    }

    void LR(AVLNode node) {
        RR(node.getLeft());
        LL(node);
    }

    void RL(AVLNode node) {
        LL(node.getRight());
        RR(node);
    }

    StringBuilder toString(AVLNode node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        AVLNode right = node.getRight(), left = node.getLeft();
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
        if (root == null) {
            return "Tree Size: " + size;
        }
        return "Tree Size: " + size + "\n" + this.toString(root, new StringBuilder(), true, new StringBuilder()).toString();
    }
}
