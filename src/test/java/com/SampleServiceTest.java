package com;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class SampleServiceTest extends TestCase {


    @Test
    public void testReverseWordsInString() {
        String str = "the sky is blue";
        String[] arr = str.split(" ");

        Collections.reverse(Arrays.asList(arr));
        String reverse = Joiner.on(" ").join(arr);
        System.out.println("after reverse: " + reverse);
    }

    //constant cache: Byte Short Long  Integer     |   Character
    @Test
    public void testConstantCache() {
        Integer a = 12;
        Integer b = 12;

        Integer aa = new Integer(12);
        Integer bb = new Integer(12);

        Integer aaa = Integer.valueOf(12);
        Integer bbb = Integer.valueOf(12);

        System.out.println(a == b);
        System.out.println(aa == bb);
        System.out.println(aaa == bbb);

        System.out.println(a == bbb);
        System.out.println(a == aaa);

        System.out.println(a == aa);
        System.out.println(b == bb);


        Character ch = 'A';
        Character chh = 'A';

        Character chhh = Character.valueOf('_');
        Character chhhh = Character.valueOf('_');

        System.out.println(ch == chh);
        System.out.println(chhh == chhhh);

    }

    @Test
    public void testGreatestCommonDivisor() {
        Integer a = 6;
        Integer b = 12;
        Integer greatest1 = greatestCommonDivisorMethod1(a, b);
        Integer greatest2 = greatestCommonDivisorMethod2(a, b);
        System.out.println("greatest1 = " + greatest1);
        System.out.println("greatest2 = " + greatest2);


    }

    private Integer greatestCommonDivisorMethod2(Integer a, Integer b) {
        Integer greatest = 1;
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        //a b even
        if ((a & 1) == 0 && (b & 1) == 0) return greatestCommonDivisorMethod2(a >> 1, b >> 1) << 1;
        //a even, b odd
        if ((a & 1) == 0) return greatestCommonDivisorMethod2(a >> 1, b);
        //a odd b even
        if ((b & 1) == 0) return greatestCommonDivisorMethod2(a, b >> 1);
        //a b odd
        if (a > b) return greatestCommonDivisorMethod2(a - b, b);
        if (a <= b) return greatestCommonDivisorMethod2(a, b - a);

        return greatest;
    }

    private Integer greatestCommonDivisorMethod1(Integer a, Integer b) {
        Integer greatest = 1;
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        for (int i = 2; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                greatest = i;
            }
        }
        return greatest;
    }

    @Test
    public void testCollections() {
        List<String> list = Lists.newArrayList("test");
        list.add("te");
        String[] str = new String[]{"tes", "yw"};
        List<String> li = Arrays.asList(str);
//        li.add("te");
//        li.remove("yw");
        str[1] = "newTest";
        li.set(0, "te");
        System.out.println(Arrays.toString(str));
        System.out.println(li.toString());

        List<String> list1 = Lists.newArrayList("tes", "yw");
        list1.remove("yw");
        list1.add("yyye");
        System.out.println(list1.size());

        int[] arr = new int[]{1, 2, 3};
        List list2 = Arrays.asList(arr);
        System.out.println("list2 " + list2.size());

        int m = 6;
        int n = 3;
        System.out.println(m %= n);

        int[] intArray = {1, 2, 3};
        List<Integer> list3 = Arrays.stream(intArray)
                .boxed().collect(Collectors.toList());
        System.out.println(list3);

    }

    @Test
    public void testString() {
//        byte[] bytes = new byte[Integer.MAX_VALUE-1];
//        System.out.println(bytes.length);

        int[] oldArray = new int[Integer.MAX_VALUE - 2];  //
        int[] newArray = Arrays.copyOfRange(oldArray, 0, 256);
        System.out.println(newArray.length);
    }

    @Test
    public void testLongestStr() {
        String str1 = "aAzZfollowre";
        String str2 = "lovewerz";
        String longestStr = getLongestStr(str1, str2);
        System.out.println("longestStr: " + longestStr);
        System.out.println("longestStr2: "+getLongestStr2(str1,str2));

    }

    private String getLongestStr(String str1, String str2) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (map.get(chars1[i]) == null) {
                map.put(chars1[i], 1);
            }
        }
        String longestStr = "";
        for (int i = 0; i < chars2.length; i++) {
            Integer val = map.get(chars2[i]);
            if (val != null && val == 1) {
                longestStr = longestStr.concat(String.valueOf(chars2[i]));
            }
        }

        return longestStr;
    }

    private String getLongestStr2(String str1, String str2) {
        boolean[] map = new boolean[123];
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (!map[chars1[i]]) {
                map[chars1[i]] = true;
            }
        }
        String longestStr = "";
        for (int i = 0; i < chars2.length; i++) {
            if (map[chars2[i]]) {
                longestStr = longestStr.concat(String.valueOf(chars2[i]));
            }
        }
        return longestStr;
    }

    @Test
    public void testFoibo() {
        int n = fobio(6);
        System.out.println("n=" + n);
    }

    private int fobio(int n) {
        if (n < 1) {
            return -1;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        return fobio(n - 1) + fobio(n - 2);
    }
}