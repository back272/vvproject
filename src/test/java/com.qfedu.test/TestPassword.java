package com.qfedu.test;


import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import java.util.Scanner;

public class TestPassword {

    static String password = "admin";

    @Test
    public void testBase64(){
        //Shiro中提供的Base64的API类的encode()方法可以实现对于给定的字节数组实现加密操作
        byte[] encode = Base64.encode(password.getBytes());

        System.out.println(new String(encode));

        //Shiro中提供的Base64的API类的decode()方法可以实现对于给定的字节数组实现解密操作
        byte[] decode = Base64.decode(encode);
        System.out.println(new String(decode));

    }

    //@Test
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("输入密码：");
        String pass = sc.next();

        String encode = new Md5Hash(password,"shirojava",3).toString();

        System.out.println(encode);


        String encode2 = new Md5Hash(pass,"shirojava",3).toString();

        System.out.println("是否一致："+encode.equals(encode2));
    }
}
