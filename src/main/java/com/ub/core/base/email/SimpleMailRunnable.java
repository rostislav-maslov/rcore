package com.ub.core.base.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;

public class SimpleMailRunnable implements Runnable {
    protected List<String> emails = new ArrayList<String>();
    protected String title;
    protected String body;

    private JavaMailSender mailSender;

    public SimpleMailRunnable() {
    }

    public SimpleMailRunnable(List<String> emails, String title, String body, JavaMailSender mailSender) {
        this.emails = emails;
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

    @Override
    public void run() {
        SimpleMailMessage emailM = new SimpleMailMessage();
        String[] ms = new String[emails.size()];
        for (int i = 0; i < emails.size(); i++)
            ms[i] = emails.get(i);
        emailM.setTo(ms);
        emailM.setSubject(title);
        emailM.setText(body);
        mailSender.send(emailM);
    }

    public void start() {
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
}
