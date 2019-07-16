package com.company.springdemo.test.algorithm;

/**
 * @Auther: whs
 * @Date: 2019/2/18 13:25
 * @Description:
 */
public class TreeSolution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 该段代码的思路入口即为从前序开始，找到第一个根节点，二分中序结构数据，
    // 再递归，再二分，诸如此类。。。。。。从而构建出相应的二叉树类型
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0)
            return null;
        // 先序遍历与中序遍历长度不同返回null
        if (pre.length != in.length)
            return null;
        TreeNode root = new TreeNode(pre[0]);
        root.left = null;
        root.right = null;
        // 只有一个节点时，返回根节点
        if (pre.length == 1 && in.length == 1)
            return root;
        int index = -1;// 记录根节点的位置
        // 查找中序序列中根节点的位置
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                index = i;
                break;
            }
        }
        if (index == -1) // 中序中无根节点
            return null;
        if (index - 0 > 0) {// 有左子树
            int[] preLeftTree = new int[index];// 记录左子树的先序遍历序列
            for (int i = 0; i < index; i++) {
                preLeftTree[i] = pre[i + 1];
            }
            int[] inLeftTree = new int[index];// 记录左子树的中序遍历序列
            for (int i = 0; i < index; i++) {
                inLeftTree[i] = in[i];
            }
            root.left = reConstructBinaryTree(preLeftTree, inLeftTree);
        }
        if (in.length - 1 - index > 0) { // 有右子树
            int[] preRightTree = new int[in.length - 1 - index];// (in.length-1-index)右子树的元素个数
            int m = 0;
            for (int i = index + 1; i < pre.length; i++) {// 记录右子树的先序遍历序列
                preRightTree[m] = pre[i];
                m++;
            }
            int[] inRightTree = new int[in.length - 1 - index];
            int j = 0;
            for (int i = index + 1; i < in.length; i++) {// 记录右子树的中序遍历序列
                inRightTree[j] = in[i];
                j++;
            }
            root.right = reConstructBinaryTree(preRightTree, inRightTree);
        }
        return root;
    }

    // 后序遍历
    public void postOrder(TreeNode root) {
        if (root.left != null)
            postOrder(root.left);
        if (root.right != null)
            postOrder(root.right);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6};
        int[] in = {4, 2, 5, 1, 6, 3};
        TreeSolution treeSolution = new TreeSolution();
        TreeNode treeNode = treeSolution.reConstructBinaryTree(pre, in);
        treeSolution.postOrder(treeNode);
    }
}
