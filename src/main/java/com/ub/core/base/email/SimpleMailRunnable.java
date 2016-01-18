package com.ub.core.base.email;

import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleMailRunnable implements Runnable {
    protected List<String> emails = new ArrayList<String>();
    protected String title;
    protected String body;
    protected String from;

    private JavaMailSender mailSender;

    public SimpleMailRunnable() {
    }

    public SimpleMailRunnable(List<String> emails, String title, String body, JavaMailSender mailSender) {
        this.emails = emails;
        this.title = title;
        this.body = body;
        this.mailSender = mailSender;
    }

    public SimpleMailRunnable(String email, String title, String body, JavaMailSender mailSender) {
        this.emails.add(email);
        this.title = title;
        this.body = body;
        this.mailSender = mailSender;
    }

    public SimpleMailRunnable(String[] emails, String title, String body, JavaMailSender mailSender) {
        for (String m : emails)
            this.emails.add(m);
        this.title = title;
        this.body = body;
        this.mailSender = mailSender;
    }

//    @Override
    public void run() {
        MimeMessage mimeMessage = mailSender.createMimeMessage();


        List<InternetAddress> ms = new ArrayList<InternetAddress>();
        for (int i = 0; i < emails.size(); i++) {
            try {
                ms.add(new InternetAddress(emails.get(i)));
            } catch (Exception e) {
            }
        }
        InternetAddress[] ims = new InternetAddress[ms.size()];
        for (int i = 0; i < ms.size(); i++)
            ims[i] = ms.get(i);

        try {
            mimeMessage.setRecipients(Message.RecipientType.TO, ims);
            String encodingOptions = "text/html;charset=UTF-8";
            mimeMessage.setHeader("Content-Type", encodingOptions);
            mimeMessage.setSentDate(new Date());
            mimeMessage.setSubject(title);
            if (from != null) {
                mimeMessage.setFrom(new InternetAddress(from));
            }
            mimeMessage.setContent(body, encodingOptions);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
    }

    @Deprecated
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void start(JavaMailSender javaMailSender) {
        this.setMailSender(javaMailSender);
        Thread thread = new Thread(this);
        thread.start();
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
