package com.gs.mail;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Administrator on 2017/9/22.
 */
public class MailUtils {

    public static void sendMail(Mail mail) {
        Properties properties = ConfigUtils.build("src/main/resources/mail.properties");
        Session session = Session.getInstance(properties,
                new MailAuthenticator(ConfigUtils.getString("username"),
                        ConfigUtils.getString("password")));
        mail.setFrom(ConfigUtils.getString("username"));
        try {
            Message message = new MimeMessage(session);
            message.setSubject(mail.getSubject()); // 邮件主题
            message.setContent(mail.getContent(), mail.getContentType()); // 邮件正文, 第一个参数是内容，第二个参数是内容的类型
            message.setFrom(mail.getFrom()); // 邮件发送者

            message.setRecipients(Message.RecipientType.TO, mail.getTo());
            message.setRecipients(Message.RecipientType.CC, mail.getCc());
            message.setRecipients(Message.RecipientType.BCC, mail.getBcc());

            Transport transport = session.getTransport();
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new Thread(new Runnable() {
            public void run() {
                Mail mail = new Mail();
                mail.setSubject("放假啦");
                mail.setContent("8天<a href='http://baidu.com'>进入网站</a>" +
                        "<img src='http://b.hiphotos.baidu.com/zhidao/pic/item/a5c27d1ed21b0ef47a3cc0a7dbc451da80cb3e76.jpg' />");
                mail.setContentType("text/html;charset=utf-8");
                mail.setTo("1076365808@qq.com,1076365808@qq.com");
                mail.setCc("1076365808@qq.com,1076365808@qq.com");
                mail.setBcc("1076365808@qq.com,1076365808@qq.com");
                MailUtils.sendMail(mail);
            }
        }).start();
    }

}
