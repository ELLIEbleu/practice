package com.practise.algorithm;

import java.util.ArrayDeque;
import java.util.Stack;

public class TreeService {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    //递归
    //根 -> 左 -> 右
    public static void preTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preTraverse(root.left);
        preTraverse(root.right);
    }

    //左 ->根节点 -> 右
    public static void inTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        inTraverse(root.left);
        System.out.print(root.val + " ");
        inTraverse(root.right);
    }

    //左 -> 根 ->右
    public static void postTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        postTraverse(root.left);
        postTraverse(root.right);
        System.out.print(root.val + " ");
    }

    public static void levelTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);

        while (!deque.isEmpty()) {
            TreeNode node = deque.pop();
            System.out.print(node.val + " ");
            if (node.left != null) {
                deque.add(node.left);
            }
            if (node.right != null) {
                deque.add(node.right);
            }
        }
    }

    //非递归
    public static void preTraverse1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                System.out.print(root.val + " ");
                stack.push(root);
                root = root.left;
            }

            if (!stack.isEmpty()) {
                root = stack.pop();
                root = root.right;
            }
        }
    }

    public static void inTraverse1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            if (!stack.isEmpty()) {
                root = stack.pop();
                System.out.print(root.val + " ");
                root = root.right;
            }
        }
    }

    public static void postTraverse1(TreeNode root) {
        Stack<Integer> result = new Stack<>();
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node  = stack.pop();
            result.push(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        while (!result.isEmpty()) {
            Integer val = result.pop();
            System.out.print(val + " ");
        }

    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode left1 = new TreeNode(8);
        TreeNode right1 = new TreeNode(16);
        root.setLeft(left1);
        root.setRight(right1);

        TreeNode left2 = new TreeNode(5);
        TreeNode right2 = new TreeNode(9);
        left1.setLeft(left2);
        left1.setRight(right2);

        TreeNode left3 = new TreeNode(12);
        TreeNode right3 = new TreeNode(17);
        right1.setLeft(left3);
        right1.setRight(right3);

        System.out.println("pre traverse ");
        preTraverse(root);
        System.out.println(" ");
        preTraverse1(root);
        System.out.println("");

        System.out.println("in traverse");
        inTraverse(root);
        System.out.println(" ");
        inTraverse1(root);
        System.out.println("");

        System.out.println("post traverse");
        postTraverse(root);
        System.out.println(" ");
        postTraverse1(root);
        System.out.println(" ");

        System.out.println("level traverse");
        levelTraverse(root);
        System.out.println(" ");
    }
}
