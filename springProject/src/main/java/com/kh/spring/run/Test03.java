package com.kh.spring.run;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class Test03 {

	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("hypermail")
	public String mail() throws MessagingException {
		
		// MimeMessage
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
		
		String[] to = {"haveway@gmail.com"};
		helper.setTo(to);
		
		String[] cc = {"haveway@gmail.com"};
		helper.setCc(cc);
		
		helper.setSubject("마임메시지 안녕");
		//helper.setText("<h1>메롱</h1>", true);
		
		// 이사이트의 대문으로 들어오는 링크 발송
		//helper.setText("<a href='http://localhost:8006/spring/'>웹사이트로 이동</a>", true);

		String url = ServletUriComponentsBuilder
					 .fromCurrentContextPath()
					 .port(8006).path("/")
					 .queryParam("test", "hello")
					 .queryParam("bye", "bye")
					 .toUriString();
		helper.setText("<a href='" + url + "'>웹사이트로 이동</a>", true);
		
		
		
		sender.send(message);
		
		
		return "redirect:/";
	}
}
