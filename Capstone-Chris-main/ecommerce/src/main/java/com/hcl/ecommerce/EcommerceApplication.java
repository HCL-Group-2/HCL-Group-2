package com.hcl.ecommerce;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
	
//	@Autowired
//	private JavaMailSender javaMailSender;
//	
//	@Override
//	public void run(String... args) {
//
//		System.out.println("Sending Email...");
//
//		try {
//			sendEmail();
//			sendEmailWithAttachment();
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("Done");
//		
//	}
//
//	void sendEmail() {
//
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setTo("1@gmail.com", "2@yahoo.com", "3@hotmail.com", "4@xyz.com");
//
//		msg.setSubject("Testing from Spring Boot");
//		msg.setText("Hello World \n Spring Boot Email");
//
//		javaMailSender.send(msg);
//
//	}
//
//	void sendEmailWithAttachment() throws MessagingException, IOException {
//
//		MimeMessage msg = javaMailSender.createMimeMessage();
//
//		// true = multipart message
//		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//		helper.setTo("1@gmail.com");
//
//		helper.setSubject("Testing from Spring Boot");
//
//		// default = text/plain
//		// helper.setText("Check attachment for image!");
//
//		// true = text/html
//		helper.setText("<h1>Check attachment for image!</h1>", true);
//
//		// FileSystemResource file = new FileSystemResource(new
//		// File("classpath:android.png"));
//
//		// Resource resource = new ClassPathResource("android.png");
//		// InputStream input = resource.getInputStream();
//
//		// ResourceUtils.getFile("classpath:android.png");
//
//		helper.addAttachment("my_photo.png", new ClassPathResource("ms1.png"));
//
//		javaMailSender.send(msg);
//
//	}

}
