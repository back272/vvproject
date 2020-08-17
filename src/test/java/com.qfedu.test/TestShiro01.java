package com.qfedu.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class TestShiro01 {

    JdbcRealm j;
    public static void main(String[] args){
        //1.创建IniSecurityManagerFactory对象
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory();

        //2.使用InisecurityManagerFactory对象的getInstance()方法获得SecurityManager对象
        SecurityManager manager = factory.getInstance();

        //3.使用SecurityUtils工具类的setSecurityManager()方法将上一步得到的SecurityManager对象放入进来
        SecurityUtils.setSecurityManager(manager);

        //4.使用SecurityUtils工具类获得Subject“”“”"主体"对象
        Subject subject = SecurityUtils.getSubject();

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入用户名:");
        String username = sc.next();

        System.out.println("请输入密码:");
        String password = sc.next();

        //5.收集用户输入的用户名和密码封装成一个Token对象
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            //6.使用Subject对象在指定的Token之上来进行登录
            subject.login(token);
        } catch (IncorrectCredentialsException e) {//密码错误异常
            System.out.println("密码错误");
            e.printStackTrace();
        } catch (UnknownAccountException e){//账户不存在异常
            System.out.println("该用户不存在");
            e.printStackTrace();
        } catch (AuthenticationException e){
            System.out.println("身份验证异常");
            e.printStackTrace();
        }

        if (subject.isAuthenticated()){
            System.out.println("login success");

            //subject对象的hasRole()方法用来判断“”“”"主体"是否拥有该角色
            System.out.println("admin : "+subject.hasRole("admin"));
            System.out.println("guest : "+subject.hasRole("guest"));

            Collection<String> c = new ArrayList<>();

            c.add("admin");
            c.add("guest");

            //subject的hasRoles()方法用来判断该“”"主体"是否拥有所有的角色
            System.out.println("AllRoles : "+ subject.hasAllRoles(c));

            System.out.println("===================================");

            subject.checkPermission("select");
            subject.checkPermissions("select","delete");
        }else{
            System.out.println("login failed");
        }
    }
}
