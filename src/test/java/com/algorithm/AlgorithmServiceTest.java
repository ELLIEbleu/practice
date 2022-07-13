package com.algorithm;

import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmServiceTest extends TestCase {

    @Test
    public void testCountCharacter() {
        String str = "teafagfanf";
        char ch = 'f';
        Integer count = countCharacter1(str, ch);
        System.out.println("count1 = " + count);
        Integer count1 = countCharacter2(str, ch);
        System.out.println("count2 = " + count1);
        System.out.println(str.chars().filter(c -> c == ch).count());
    }

    private Integer countCharacter2(String str, char ch) {
        Integer count = 0;
        Pattern pattern = Pattern.compile("[^f]*f");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            count += 1;
        }
        return count;
    }

    private Integer countCharacter1(String str, char ch) {
        Integer count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count += 1;
            }
        }
        return count;
    }


    @Test
    public void testIsMonge() throws IOException {
        int[][] matrix = buildMatrix();
        boolean isMonge = isMonge(matrix);
        System.out.println("matrix is monge: " + isMonge);
    }

    private boolean isMonge(int[][] matrix) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                int sum1 = matrix[i][j] + matrix[i + 1][j + 1];
                int sum2 = matrix[i][j + 1] + matrix[i + 1][j];
                if (sum1 > sum2) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testReadMatrixTxt() throws IOException {
        Properties properties = new Properties();   //key - vale
        properties.load(this.getClass().getResourceAsStream("/matrix"));

        //
        int[][] matrix = buildMatrix();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
//        System.out.println(this.getClass().getResource("/").getFile());
    }

    private int[][] buildMatrix() throws IOException {
        File file = new File("src/test/resources/matrix2");
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        BufferedReader reader = new BufferedReader(new FileReader(file));

        int[][] matrix = new int[7][5];
        int line = 0;
        String str = null;

        while (reader.read() > 0) {
            str = reader.readLine();
            String[] arr = str.split(" ");
            for (int i = 0; i < arr.length; i++) {
                matrix[line][i] = Integer.parseInt(arr[i]);
            }
            ++line;
        }
        return matrix;
    }


    @Test
    public void testFixedCoins() {
        int target = 10;
        String choose = retrieveChoose1(10);
        System.out.println(choose);
    }

    private String retrieveChoose1(int n) {
        String res = "";
        if (n <= 0) {
            return res;
        }
        while (n >= 1) {
            if (n % 2 == 0) {
                res = res.concat("2");
                n = (n - 2) / 2;
            } else {
                res = res.concat("1");
                n = (n - 1) / 2;
            }
        }
        return StringUtils.reverse(res);
    }

    @Test
    public void testOppositeNumber() {
//        int input = 1325;
        int input = 100;
        int oppositeNumber = getOppositeNumber(input);
        System.out.println(oppositeNumber);
    }

    private int getOppositeNumber(int input) {
        int reverse = getReverse(input);
        return reverse + input;
    }

    private int getReverse(int input) {
        String str = String.valueOf(input);
        String newStr = StringUtils.reverse(str);
        System.out.println("newStr:" + newStr);
        return Integer.valueOf(newStr).intValue();
    }

    @Test
    public void testAverageStrLength() {
        String input = "aaabbaaacc";
//        double averageLength = getAverageLength(input);
        double averageLength = getAverageLength2(input);
        System.out.println(averageLength);
    }

    private double getAverageLength2(String input) {
        char[] chars = input.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i + 1 >= chars.length || chars[i] != chars[i + 1]) {
                ++count;
            }
        }

        return BigDecimal.valueOf(input.length()).divide(BigDecimal.valueOf(count)).doubleValue();
    }

    private double getAverageLength(String input) {
        List<String> stringList = splitStr(input);

        int totalLength = stringList.stream().mapToInt(record -> record.length()).sum();
        return BigDecimal.valueOf(totalLength).divide(BigDecimal.valueOf(stringList.size())).doubleValue();
    }

    private List<String> splitStr(String input) {
        List<String> stringList = Lists.newArrayList();
        char[] chars = input.toCharArray();

        String str = "";
        for (int i = 0; i < chars.length; i++) {
            str = str.concat(String.valueOf(chars[i]));
            if (i + 1 >= chars.length || chars[i] != chars[i + 1]) {
                stringList.add(str);
                str = "";
            }

        }


        return stringList;
    }

    /**
     * 1.互斥
     * 2.请求与保持条件
     * 3.不可剥夺
     * 4.循环等待条件
     */

    //
    @Test
    public void testDeadlock() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA start run, apply lock ");
                lock.lock();
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadB start run, apply lock");
                try {
                    Thread.sleep(15 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
            }
        });

        threadA.start();
        threadB.start();


    }

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    static class RunnableA implements Runnable {
        @Override
        public void run() {
            synchronized (object1) {
                System.out.println("threadA acquire resource1");
                try {
                    System.out.println("in runnableA thread name:" + Thread.currentThread().getName());
                    Thread.sleep(20);
                    System.out.println("after threadA sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println("threadA acquire resource2");
                }
                countDownLatch.countDown();
            }
        }
    }

    static class RunnableB implements Runnable {
        @Override
        public void run() {
            synchronized (object2) {
                System.out.println("threadB acquire resource2");
                try {
                    System.out.println("in runnableB thread name:" + Thread.currentThread().getName());
                    Thread.sleep(20);
                    System.out.println("after threadB sleep ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println("threadB acquire resource1");
                }
                countDownLatch.countDown();
            }
        }
    }

    @Test
    public void testDeadlock2() throws InterruptedException {
        Thread threadA = new Thread(new RunnableA());
        Thread threadB = new Thread(new RunnableB());
        threadA.start();
        threadB.start();

//        Thread.sleep(100000);     //主线程等待子线程结束

        countDownLatch.await();
    }


    //
    public static Object object1 = "resource1";
    public static Object object2 = "resource2";

    public static void main(String[] args) {
        //
//        new threadA().start();
//        new threadB().start();

        Thread threadA = new Thread(new RunnableA());
        Thread threadB = new Thread(new RunnableB());
        threadA.start();
        threadB.start();

        String str = StringUtils.valueOf(null);
        System.out.println("str:" + str);
    }

    private static class threadA extends Thread {
        @Override
        public void run() {
            synchronized (object1) {
                System.out.println("threadA acquire resource1");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (object2) {
                    System.out.println("threadA acquire resource2");
                }
            }
        }
    }


    private static class threadB extends Thread {
        @Override
        public void run() {
            synchronized (object2) {
                System.out.println("threadB acquire resource2");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println("threadB acquire resource1");
                }
            }
        }
    }


    @Test
    public void testKmp() {
        String str = "BBC ABCDAB ABCDABCDABDE";
        String match = "ABCDABD";

        int position = kmp(str, match);
    }

    private int kmp(String str, String match) {
        return 0;
    }

    @Test
    public void testReplaceStr() {
        String input = "We Are  Happy";
        String output = replaceStr(input);
        System.out.println(output);
        System.out.println("replaceStr2 = " + replaceStr2(input));
    }

    private String replaceStr(String input) {
        return input.replaceAll("\\s", "%20");
    }

    private String replaceStr2(String input) {
        char[] chars = input.toCharArray();
        String output = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                output = output.concat("%20");
            } else {
                output = output.concat(String.valueOf(chars[i]));
            }
        }
        return output;
    }

    @Test
    public void testLongestPrefix() {
        List<String> list = Arrays.asList("flower", "flow", "flight");
        String longestPrefix = findLongestPrefix(list);
        System.out.println(longestPrefix);
    }

    private String findLongestPrefix(List<String> list) {
        String outPut = "";
        String minStr = Collections.min(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println(minStr);

        for (int i = 0; i < minStr.length(); i++) {
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (j + 1 < list.size() - 1) {
                    char[] beforeChars = list.get(j).toCharArray();
                    char[] afterChars = list.get(j + 1).toCharArray();
                    if (beforeChars[i] == afterChars[i]) {
                        count += 1;
                    } else if (beforeChars[i] != afterChars[i]) {
                        break;
                    }
                }
            }

            if (count == list.size()) {
                outPut = outPut.concat(String.valueOf(minStr.toCharArray()[i]));
            }
        }
        return outPut;
    }

    /**
     * leetcode:30 串联所有单词的子串
     * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置
     */
    @Test
    public void testFindSubString() {
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo", "bar"};
        List<Integer> str = findSubstring(s, words);
        System.out.println("str:" + str);

        String ss = "wordgoodgoodgoodbestword";
        String wordss[] = new String[]{"word", "good", "best", "word"};
        List<Integer> str2 = findSubstring(ss, wordss);
        System.out.println("str2:" + str2);

        String sss = "barfoofoobarthefoobarman";
        String[] wordsss = new String[]{"bar", "foo", "the"};
        List<Integer> str3 = findSubstring(sss, wordsss);
        System.out.println("str3:" + str3);

        String ss2 = "wordgoodgoodgoodbestword";
        String[] word2 = new String[]{"word", "good", "best", "good"};
        List<Integer> str4 = findSubstring(ss2, word2);
        System.out.println("str4:" + str4);

        String ss3 = "ababababab";
        String[] word3 = new String[]{"ababa", "babab"};
        List<Integer> str5 = findSubstring(ss3, word3);
        System.out.println("str5:" + str5);

        String ss4 = "ababaab";
        String[] word4 = new String[]{"ab", "ba", "ba"};
        List<Integer> str6 = findSubstring(ss4, word4);
        System.out.println("str6:" + str6);
    }

    /**
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        //单词字典表
        Map<String, List<String>> map = Arrays.stream(words).collect(Collectors.groupingBy(record -> record));

        int slideWindowSize = Arrays.stream(words).mapToInt(record -> record.length()).sum();
        for (int i = 1; i < s.length(); i++) {
            if (i + slideWindowSize > s.length()) {
                break;
            }
            String temp = s.substring(i, i + slideWindowSize);

            //判断是否 单词匹配的次数
            boolean matchTimes = match(temp, map);
            if (matchTimes) {
                result.add(i);
            }

        }

        return result;
    }

    private boolean match(String temp, Map<String, List<String>> map) {
        Map<String, Integer> countMap = new HashMap<>();
        List<Integer> gapList = map.entrySet().stream().map(record -> record.getKey().length()).distinct().collect(Collectors.toList());
        for (Integer gap : gapList) {
            for (int i = 0; i < temp.length(); ) {
                String subStr = temp.substring(i, i + gap);
                Integer count = countMap.getOrDefault(subStr, 0);
                countMap.put(subStr, count + 1);
                i += gap;
            }
        }
        for (Map.Entry record : map.entrySet()) {
            String str = (String) record.getKey();
            List<String> list = (List<String>) record.getValue();
            int count = list.size();
            Integer val = countMap.getOrDefault(str, 0);
            if (val < count) {
                return false;
            }

        }

        return true;
    }


    /**
     * leetcode: 44.通配符匹配
     * 给定一个字符串(s)和一个字符模式(p)，实现一个支持'?'和'*'的通配符匹配
     * '?' 可以匹配任何单个字符
     * '*' 可以匹配任意字符串(包括空字符串)
     */
    @Test
    public void testIsMatch() {
        String s = "aa";
        String p = "a";
        boolean match = isMatch(s, p);
        assertEquals(false, match);

        s = "aa";
        p = "*";
        match = isMatch(s, p);
        assertEquals(true, match);

        s = "cb";
        p = "?a";
        match = isMatch(s, p);
        assertEquals(false, match);

        s = "adceb";
        p = "*a*b";
        match = isMatch(s, p);
        assertEquals(true, match);

        s = "acdcb";
        p = "a*c?b";
        match = isMatch(s, p);
        assertEquals(false, match);

        s = "aab";
        p = "c*a*b";
        match = isMatch(s, p);
        assertEquals(false, match);

        s = "";
        p = "******";
        match = isMatch(s, p);
        assertEquals(true, match);
    }


    private boolean isMatch(String s, String p) {

        return true;
    }

    /**
     * leetcode 10.正则表达式匹配
     * 给定一个字符串s 和一个字符规律p,请你来实现一个支持'.' 和 '*' 的正则表达式匹配
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那个元素
     * 所谓匹配，是要涵盖 整个字符串s的，而不是部分字符串
     */
    @Test
    public void testMatch() {
        String s = "aa";
        String p = "a";
        boolean res = match(s, p);
        assertEquals(false, res);

        s = "aa";
        p = "a*";
        res = match(s, p);
        assertEquals(true, res);

        s = "ab";
        p = ".*";
        res = match(s, p);
        assertEquals(true, res);

        s = "aab";
        p = "c*a*b";
        res = match(s, p);
        assertEquals(true, res);

        s = "mississippi";
        p = "mis*is*p*.";
        res = match(s, p);
        assertEquals(false, res);
    }

    public boolean match(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();




        return true;
    }


    /**
     * leetcode 72 编辑距离
     * 给你两个单词word1 和 word2，请返回将word1 转换成word2 所使用的最少操作数
     * 你可以对一个单词进行如下三种操作
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     */
    @Test
    public void testMinDistance() {
        String word1 = "horse";
        String word2 = "ros";
        int min = minDistance(word1, word2);
        System.out.println("min:" + min);

        word1 = "intention";
        word2 = "execution";
        min = minDistance(word1, word2);
        System.out.println("min:" + min);
    }

    public int minDistance(String word1, String word2) {
        int minDistance = 0;

        return minDistance;

    }

    /**
     * leetcode:279 完全平方数
     * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量
     * 完全平方数 是一个整数，其值等于另一个整数的平方；
     * 换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是
     */
    @Test
    public void testNumSquares() {
        int n = 12;
        int result = numSquares(n);
        System.out.println("result:" + result);

        n = 13;
        result = numSquares(n);
        System.out.println("result:" + result);

    }

    public int numSquares(int n) {
        int result = 0;


        return result;
    }
}