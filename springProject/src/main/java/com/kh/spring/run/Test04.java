package com.kh.spring.run;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test04 {

	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("sendfile")
	public String mail() throws MessagingException {
		
		// MimeMessage를 이용한 파일첨부
		// javax.activation.DataSource : 파일정보
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		String[] to = {"haveway@gmail.com"};
		helper.setTo(to);
		
		String[] cc = {"haveway@gmail.com"};
		helper.setCc(cc);
		
		helper.setSubject("파일테스트");
		helper.setText("파일가나여");
		
		// 첨부파일추가
		DataSource source = new FileDataSource("c:/bicycle.png");
		helper.addAttachment(source.getName(), source);
		
		sender.send(message); 
		
		return "redirect:/";
	}
	
}
