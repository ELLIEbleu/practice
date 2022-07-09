package com.algorithm;

import org.junit.Test;
import org.w3c.dom.CharacterData;

/**
 * @Description
 * @Author ellie
 * @Date 2021/10/29 11:24 上午
 **/
public class StringTest {

    @Test
    public void testStringToi() {
        String str = "232424";
        String regex = "[0-9]+";
        boolean match = str.matches(regex);

        if (match) {
            int integer = atoi(str);

        }
    }

    private int atoi(String str) {
//        String[] arr = str.split("");
//        int integer = 1;
//        int val = 1;
//        int length = str.length();
//        while (length-- >0){
//            integer *=10;
//        }
//        for (int i = 0; i < arr.length; i++) {
//
//        }

        return 0; //todo
    }


    @Test
    public void testIntegerCache() {
        Integer val = 59;
        int val2 = 59;
        Integer val3 = Integer.valueOf(59);
        Integer val4 = new Integer(59);

        System.out.println(val == val2);
        System.out.println(val == val3);
        System.out.println(val3 == val4);
        System.out.println(val2 == val4);

        System.out.println(val.hashCode());
        System.out.println(val3.hashCode());
        System.out.println(val4.hashCode());

        System.out.println(val.equals(val2));
        System.out.println(val.equals(val3));
        System.out.println(val3.equals(val4));
        System.out.println(val4.equals(val2));
    }

    @Test
    public void testParseInt() {
//       int val = Integer.parseInt("99",8);
//        System.out.println("val:"+val);
        String str = "124";
        Integer val2 = Integer.parseInt("124", 10);
        System.out.println("val2:" + val2);

        for (int i = 0; i < str.length(); i++) {
            int val3 = Character.digit(str.charAt(i), 10);
            System.out.println("val3:"+ val3);
        }

        System.out.println(50  >> 8);
        System.out.println(50 >>> 8);
        System.out.println(52 >>> 8);

//        CharacterDataLatin1
//        CharacterData
    }
}
