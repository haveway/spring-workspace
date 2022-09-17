package com.kh.spring.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test02 {

	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("mail")
	public String mail() {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setSubject("스프링메일전송테스트");
		message.setText("잘간다~");
		
		String[] to = {"haveway@gmail.com"};
		message.setTo(to);	
	
		String[] cc = {"haveway@gmail.com"};
		message.setCc(cc);
			
		sender.send(message);
		
		return "redirect:/";
	}
	
}
