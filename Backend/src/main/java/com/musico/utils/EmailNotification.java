//Author: Simar Saggu
// Created on: 16th July,2021
package com.musico.utils;


import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

// Referred this code for email notifications
// https://www.baeldung.com/spring-email
public class EmailNotification {

    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("musico.com.helpdesk@gmail.com");
        mailSender.setPassword("group1@5709musico");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
    public Boolean sendForgetPasswordEmail(String email, String password){
        boolean sent;
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("musico.com.helpdesk@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject("MusicO: Password Recovery Request");
            mailMessage.setText("Hi there,\n\nHere is your password:\n\n"+password+" \n\nYou can use this password to access MusicO. If you did not request for password recovery, please ignore this mail.\n\nClick on the link to login:\n\n https://group1-musico.herokuapp.com/ \n" +
                    "\nThis mail was sent by: MusicO-music streaming website.\nNeed help? Send us a mail on: musico.com.helpdesk@gmail.com ");
            JavaMailSender mailSender = getJavaMailSender();
            mailSender.send(mailMessage);
            sent = true;
        }
        catch(MailException mailException){
            mailException.printStackTrace();
            sent = false;

        }
        return sent;
    }
}
