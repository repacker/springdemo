package com.company.springdemo.common.utils;


import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;
import com.sun.mail.imap.IMAPStore;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

/**
 * @Auther: whs
 * @Date: 2018/5/29 15:17
 * @Description: 接收邮箱并统计分析
 */
public class ImapFetchMail {
    public static void main(String[] args) throws MessagingException {
        String host = "newmail.xxx.com";
        String port = "993";
        String username = "xxx";
        String password = "";
        ImapFetchMail fetchMail = new ImapFetchMail();

        IMAPFolder folder = null;
        IMAPStore store = fetchMail.getCollection(host, port, username, password);
        try {
            folder = (IMAPFolder) store.getFolder("inbox"); //收件箱
            // 使用只读方式打开收件箱
            folder.open(Folder.READ_WRITE);
            int size = folder.getMessageCount();
            System.out.println("这里是打印的条数==" + size);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (folder != null) {
                    folder.close(false);
                }
                if (store != null) {
                    store.close();
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        System.out.println("接收完毕！");
    }

    public void getFullFonder(IMAPStore store) throws MessagingException {
        Folder defaultFolder = store.getDefaultFolder();
        Folder[] allFolder = defaultFolder.list();
        for (int i = 0; i < allFolder.length; i++) {
            System.out.println("这个是服务器中的文件夹=" + allFolder[i].getFullName());
        }
    }

    public IMAPStore getCollection(String host, String port, String username, String password) throws MessagingException {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Properties props = System.getProperties();
        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imap.socketFactory.port", port);
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", host);
        props.setProperty("mail.imap.port", port);
        props.setProperty("mail.imap.auth.login.disable", "true");
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(false);
        IMAPStore store = (IMAPStore) session.getStore("imap");  // 使用imap会话机制，连接服务器
        store.connect(host, Integer.valueOf(port), username, password);
        return store;
    }

    public void getMessageInfo(IMAPFolder folder, Message[] messages) throws MessagingException {
        // 打印不同状态的邮件数量
        System.out.println("收件箱中共" + messages.length + "封邮件!");
        System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
        System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");
        System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");

        for (int i = 0; i < 5; i++) {
            String from = messages[i].getFrom()[0].toString();
            String subject = messages[i].getSubject();
            Date date = messages[i].getSentDate();
            System.out.println("From: " + from);
            System.out.println("Subject: " + subject);
            System.out.println("Date: " + date);
        }


    }

    public void doAnalyzeWork(Message[] messages) throws MessagingException, IOException {
        // 解析邮件
        for (Message message : messages) {
            IMAPMessage msg = (IMAPMessage) message;
            String subject = MimeUtility.decodeText(msg.getSubject());
            System.out.println("[" + subject + "]未读，是否需要阅读此邮件（yes/no）？");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String answer = reader.readLine();
            if ("yes".equalsIgnoreCase(answer)) {
//                 POP3ReceiveMailTest.parseMessage(msg);  // 解析邮件
                // 第二个参数如果设置为true，则将修改反馈给服务器。false则不反馈给服务器
                msg.setFlag(Flags.Flag.SEEN, true);   //设置已读标志
            }
        }
    }
}
