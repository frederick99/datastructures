/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastruct;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Naveen
 */
public class TreeNode {
    
    private ArrayList<TreeNode> child;
    private TreeNode parent;
    private int data;
    
    public TreeNode() {
        child = null;
        parent = null;
    }

    public TreeNode(int data) {
        child = null;
        parent = null;
        this.data = data;
    }

    public TreeNode(int data, TreeNode ...collection) {
        child.addAll(Arrays.asList(collection));
        parent = null;
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public TreeNode setData(int data) {
        this.data = data;
        return this;
    }
    
    public TreeNode getParent() {
        return parent;
    }

    public TreeNode setParent(TreeNode parent) {
        
        this.parent = parent;
        return this;
    }

    public String toString() {
        return data + "";
    }
}
