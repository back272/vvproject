package com.qfedu.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestShiro02 {

    @Test
    public void testShiro(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro2.ini");

        SecurityManager manager = factory.getInstance();

        SecurityUtils.setSecurityManager(manager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("wukong", "111111");

        subject.login(token);

        System.out.println("success");
    }
}
