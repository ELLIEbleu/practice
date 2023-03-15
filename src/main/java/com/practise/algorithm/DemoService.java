package com.practise.algorithm;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class DemoService {

    /**
     * 输入：n = 3
     * 输出：[
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     * <p>
     * 输入：n= 1
     * 输出：[
     * "()"
     * ]
     * <p>
     * 输入：n =2
     * 输出：[
     * "()()"
     * "(())"
     * ]
     */

    public List<String> generateParenthesis(int n) {
        List<String> list = Lists.newArrayList();
        Stack<String> track = new Stack<>();
        backtrack(n, n, track, list);
        print(list);
        return list;
    }

    public void backtrack(int left, int right, Stack<String> track, List<String> res) {
        if (left < right) return;
        if (left < 0 || right < 0) return;
        if (left == 0 && right == 0) {
            StringBuffer buffer = new StringBuffer();
            track.forEach(record -> {
                buffer.append(record);
            });
            res.add(buffer.reverse().toString());
            return;
        }

        track.push("(");
        backtrack(left - 1, right, track, res);
        track.pop();

        track.push(")");
        backtrack(left, right - 1, track, res);
        track.pop();
    }

    public void print(List<String> track) {
        for (String str : track) {
            System.out.println(str);
        }
    }

    /**
     * coins 存储不同面值的硬币，指定amount，硬币面值可以重复，得到amount的组合使用的硬币最少的数量
     *
     * @param coins
     * @param amount
     * @return
     */

    private static int coinChange(int[] coins, int amount) {
        int count = 0;
        List<Integer> results = Lists.newArrayList();

        while (amount > 0) {
            for (int i = coins.length - 1; i >= 0; ) {
                amount = amount - coins[i];
                if (amount >= 0) {
                    results.add(coins[i]);
                    count += 1;
                } else {
                    amount += coins[i];
                    --i;
                }
            }
        }
        if (count > 0) {
            results.forEach(record -> System.out.print(record + " "));
            System.out.println(" ");
            return count;
        }
        return -1;
    }

    private static List<List<Integer>> merge(List<List<Integer>> lists) {
        List<List<Integer>> results = Lists.newArrayList();

        for (int i = lists.size() - 1; i > 0; i--) {
            List<Integer> list = lists.get(i);
            boolean flag = isMerge(lists.get(i - 1), lists.get(i));
            if (flag) {
                //merge 两个数组
                List<Integer> merge = Lists.newArrayList();
                merge.addAll(results.get(i - 1));
                merge.addAll(results.get(i));
                results.add(merge);
            } else {
                results.add(list);
            }
        }

        return results;
    }

    private static boolean isMerge(List<Integer> pre, List<Integer> next) {
        int preMax = pre.get(pre.size() - 1);
        int nextMin = next.get(0);

        if (preMax > nextMin) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        DemoService demo = new DemoService();
        System.out.println("n = 1 ");
        demo.generateParenthesis(1);
        System.out.println("n = 2");
        demo.generateParenthesis(2);
        System.out.println("n = 3");
        demo.generateParenthesis(3);

        int[] coins = new int[]{1, 2, 5};

        int n = coinChange(coins, 19);
        System.out.println("coins n = " + n);
        List<List<Integer>> lists = Lists.newArrayList();
        List<Integer> list1 = Lists.newArrayList(1, 3);
        lists.add(list1);
        List<Integer> list2 = Lists.newArrayList(2, 6);
        lists.add(list2);
        List<Integer> list3 = Lists.newArrayList(8, 10);
        lists.add(list3);
        List<Integer> list4 = Lists.newArrayList(15, 18);
        lists.add(list4);

        List<List<Integer>> merge = merge(lists);
        System.out.println("merge list");
        merge.forEach(list -> {
            list.forEach(record -> {
                System.out.print(record + " ");
            });
            System.out.println(" ");
        });


        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(2);
        queue.add(1);
        queue.add(3);
        queue.add(0);
        queue.add(6);

        queue.stream().forEach(record -> {
            System.out.println(record);
        });
        // test

//        Stack<String> stack = new Stack<>();
//        stack.add("test1");
//        stack.add("test2");
//        stack.add("test3");
//        while (!stack.isEmpty()) {
//            System.out.println(stack.pop());
//        }
//        stack.forEach(record ->{
//            System.out.println("stack: "+ record);
//        });
//
//        Vector<String> vector = new Vector<>();
//        vector.add("ve1");
//        vector.add("ve2");
//        vector.add("ve3");
//        vector.add("ve4");
//
//        for (int i = 0; i < vector.size(); i++) {   //
//            System.out.println(vector.get(i));
//        }

//        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>();

        LinkedBlockingDeque<String> qu = new LinkedBlockingDeque<>();
        LinkedList<Integer> cache = new LinkedList<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        HashMap<String, String> hashMap = new HashMap<>();


        List<Integer> list = Lists.newArrayList(1, 3, 2, 12, 4, 6, 19);
        System.out.println(Collections.max(list));
        System.out.println(CollectionUtils.size(list));

        Collections.sort(list);
        System.out.println(Collections.binarySearch(list, 1));
        System.out.println(Collections.binarySearch(list, 6));
        System.out.println(Collections.binarySearch(list, 19));
        Integer[] objs = new Integer[]{2, 3, 5, 7, 1};
        System.out.println(objs.length);

        System.out.println("********************");
        putIn(0,weights[0]);
        System.out.println("max:"+ maxW);
    }

    /**
     * 输入 -> 输出 能被3整除的数
     * 1000 -> 0
     * 2119 -> 21
     * 5469 -> 54,6,9
     */

    public List<Integer> outputDiv(String input) {
        List<Integer> result = Lists.newArrayList();


        return result;
    }

    /**
     *  打印环形矩阵
     *  n=3
     *  1 2 3
     *  8 9 4
     *  7 6 5
     *
     *  n= 4
     *  1  2  3 4
     *  6  5  4 5
     *  1  2  3 6
     *  0  9  8 7
     *
     */

    public Vector<Integer> printMatrix(){
        Vector<Integer> vector = new Vector<>();
        return vector;
    }


    /**
     * 0-1背包问题
     * 回溯算法实现
     */

    private static int n = 5;
    private static int w = 9;
    private static int maxW = Integer.MIN_VALUE;
    private static int[] weights = new int[]{2,2,4,6,3};

    private static boolean[][] mem = new boolean[5][10];

    public static void putIn(int i, int cw){
        if( cw == w || i == n){
            if( cw > maxW){
                maxW = cw;
                return;
            }
        }
        if( i+1< weights.length -1) {
            putIn(i + 1, weights[i + 1]);
        }

        if( cw+ weights[i] <=w){
            putIn(i+1,cw+weights[i]);
        }
    }


}
