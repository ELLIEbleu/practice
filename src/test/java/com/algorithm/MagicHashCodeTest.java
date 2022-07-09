package com.algorithm;


import org.junit.Test;

/**
 * @Description
 * @Author ellie
 * @Date 2021/5/27 3:03 PM
 **/
public class MagicHashCodeTest {

    private static final int HASH_INCREMENT = 0x61c88647;

    @Test
    public void testHashCode() {
        System.out.println("hash increment:"+ HASH_INCREMENT);
        hashCode(16); //初始化16
        hashCode(32); //后续2倍扩容
        hashCode(64);
        System.out.println("********************");
        magicNumber();
    }

    private static void hashCode(Integer length){
        int hashCode = 0;
        for(int i=0; i< length; i++){
            hashCode = i * HASH_INCREMENT+HASH_INCREMENT;//每次递增HASH_INCREMENT
            System.out.print(hashCode & (length-1));
            System.out.print(" ");
        }
        System.out.println();
    }

    private static void magicNumber(){
        long factor = (long) ((Math.sqrt(5) - 1)/2);
        System.out.println("factor:"+ factor +" ret:"+ ((Math.sqrt(5) - 1)/2));
        long l1 = (long) ((1L << 32) * ((Math.sqrt(5) - 1)/2));
        System.out.println("as 32 bit unsigned: " + l1);
        int i1 = (int) l1;
        System.out.println("as 32 bit signed:   " + i1);
        System.out.println("MAGIC = " + 0x61c88647);
        System.out.println("diff:"+ (l1 - 0x61c88647));
    }

}
