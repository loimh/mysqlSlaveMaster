package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.net.MulticastSocket;

/**
 * 给qq邮箱发送邮件工具类
 */
@Service
public class SendMailUtil {

    //springboot自动集成JavaMailSender
    @Autowired
    private  JavaMailSender mailSender;

    //发送的对象地址，可以直接写，也可以在配置文件李配置
    @Value("${spring.mail.username}")
    private  String from;

    /**
     * @author caiyi 2020/12/07
     * @param address：接收方邮箱地址
     * @param title：邮件标题
     * @param content：邮件内容
     */
    public  void  sendMail(String address, String title, String content){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(from);
        //发送地址
        mailMessage.setTo(address);
        //邮件标题
        mailMessage.setSubject(title);
        //邮件内容
        mailMessage.setText(content);
        mailSender.send(mailMessage);
    }
}
