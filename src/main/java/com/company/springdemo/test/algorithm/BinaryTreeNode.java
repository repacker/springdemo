package com.company.springdemo.test.algorithm;

import java.util.LinkedList;

/**
 * @Auther: whs
 * @Date: 2019/2/18 11:45
 * @Description: 二叉树的遍历
 */
public class BinaryTreeNode {

    private Object data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(Object data, BinaryTreeNode left, BinaryTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public BinaryTreeNode createTreeNode() {
        BinaryTreeNode node = new BinaryTreeNode("1", null, null);
        BinaryTreeNode left2 = new BinaryTreeNode("2", null, null);
        BinaryTreeNode left3 = new BinaryTreeNode("3", null, null);
        BinaryTreeNode left4 = new BinaryTreeNode("4", null, null);
        BinaryTreeNode left5 = new BinaryTreeNode("5", null, null);
        BinaryTreeNode left6 = new BinaryTreeNode("6", null, null);
        node.setLeft(left2);
        left2.setLeft(left4);
        left2.setRight(left6);
        node.setRight(left3);
        left3.setRight(left5);
        return node;
    }

    /**
     * 二叉树的层序遍历 借助于队列来实现 借助队列的先进先出的特性
     * 首先将根节点入队列 然后遍历队列。
     * 首先将根节点打印出来，接着判断左节点是否为空 不为空则加入队列
     * @param node
     */
    public void levelIterator(BinaryTreeNode node) {
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();

        //先将根节点入队
        queue.offer(node);
        BinaryTreeNode current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            System.out.print(current.data + "--->");

            if (current.getLeft() != null) {
                queue.offer(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.offer(current.getRight());
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode node = new BinaryTreeNode(null,null,null);
        BinaryTreeNode binaryTreeNode = node.createTreeNode();
        System.out.println(binaryTreeNode);
        binaryTreeNode.levelIterator(binaryTreeNode);
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

}
