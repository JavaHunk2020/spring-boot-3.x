package com.kuebiko.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kuebiko.dto.SignupDTO;

@Service
public class EmailService {
	
	
	@PostConstruct
	public void init() throws MessagingException, IOException {
		SignupDTO user =new SignupDTO();
		 user.setName("Nagendra Kumar");
		 user.setEmail("technohunk444@gmail.com");
		 this.sendMail(user);
		 System.out.println("Yeah email is sent");
	}


    private final TemplateEngine templateEngine;

    /**
     * sendMail
     * spring.mail.username=amogh13051981@gmail.com
spring.mail.password=ykbeqkolhdmzqwzi
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.protocol=smtp
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
     */
    private final JavaMailSender javaMailSender;

    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public String sendMail(SignupDTO user) throws MessagingException, IOException {
        Context context = new Context();
        context.setVariable("user", user);

        //welcome.html
        String process = templateEngine.process("emails/welcome", context);
        
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        
        helper.setSubject("Welcome " + user.getName());
        helper.setText(process, true);
        helper.setTo(user.getEmail());
        
		/*
		 * InputStreamSource cimageSource =new ByteArrayResource(photo);
		 * helper.addInline("cardicon", cimageSource, "image/png");
		 */

        File file=new ClassPathResource("images/logo.png", EmailService.class.getClassLoader()).getFile();
        byte[] bytes=Files.readAllBytes(file.toPath());
        InputStreamSource imageSource =new ByteArrayResource(bytes);
        helper.addInline("bankIcon", imageSource, "image/png");
        
        javaMailSender.send(mimeMessage);
        return "Sent";
    }
}