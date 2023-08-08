package com.demo.DemoApp.service.mail;//package com.relationships.relationships.service;



public interface EmailService {
    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
