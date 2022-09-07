package com.hcl.ecommerce.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.OrderItem;

@Service
public class MailSenderService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	void sendEmail(String emailAddress) {

		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setTo(emailAddress);
		msg.setSubject("Testing from Spring Boot");
		msg.setText("Hello World \n Spring Boot Email");

		javaMailSender.send(msg);

	}
	
	String buildHtmlString(Order order) {
		List<OrderItem> orderItems = order.getOrderItems();
		String str = "<h1>Thank you for your order, " + order.getUser().getFirstName() + "</h1>\r\n"
				+ "\r\n"
				+ "<h4>Order confirmation</h4>\r\n"
				+ "\r\n"
				+ "<h4>Order Detail</h4>\r\n"
				+ "\r\n"
				+ "<table cellspacing=\"4\" cellpadding=\"4\">\r\n"
				+ "    <tr>\r\n"
				+ "        <th>Product name</th>\r\n"
				+ "        <th>Quantity</th>\r\n"
				+ "        <th>Price</th>\r\n"
				+ "        <th>Subtotal</th>\r\n"
				+ "    </tr>";
		for (OrderItem item : orderItems) {
			str += "    <tr>\r\n"
					+ "        <td> " + item.getProduct().getName() + " </td>\r\n"
					+ "        <td> " + item.getQuantity() + " </td>\r\n"
					+ "        <td> $" + item.getProduct().getPrice() + " </td>\r\n"
					+ "        <td> $" + item.getSubtotal() + " </td>\r\n"
					+ "    </tr>";
		}
		str += "</table>\r\n"
				+ "\r\n"
				+ "<h4>Order total: $" + order.getOrderTotal() + "</h4>";
		return str;
	}
	
	void sendEmailWithAttachment(String emailAddress, Order order) throws MessagingException, IOException {

		MimeMessage msg = javaMailSender.createMimeMessage();

		// true = multipart message
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
		helper.setTo(emailAddress);
		helper.setSubject("Order confirmation");

		// default = text/plain
		// helper.setText("Check attachment for image!");

		// true = text/html
		helper.setText(buildHtmlString(order), true);

		// FileSystemResource file = new FileSystemResource(new
		// File("classpath:android.png"));

		// Resource resource = new ClassPathResource("android.png");
		// InputStream input = resource.getInputStream();

		// ResourceUtils.getFile("classpath:android.png");

		// helper.addAttachment("my_photo.png", new ClassPathResource("ms1.png"));

		javaMailSender.send(msg);

	}
	
	void sendEmailWithAttachment(String emailAddress) throws MessagingException, IOException {

		MimeMessage msg = javaMailSender.createMimeMessage();

		// true = multipart message
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
		helper.setTo(emailAddress);
		helper.setSubject("Testing from Spring Boot");

		// default = text/plain
		// helper.setText("Check attachment for image!");

		// true = text/html
		helper.setText("<h1>Check attachment for image!</h1>", true);

		// FileSystemResource file = new FileSystemResource(new
		// File("classpath:android.png"));

		// Resource resource = new ClassPathResource("android.png");
		// InputStream input = resource.getInputStream();

		// ResourceUtils.getFile("classpath:android.png");

		helper.addAttachment("my_photo.png", new ClassPathResource("ms1.png"));

		javaMailSender.send(msg);

	}

}
