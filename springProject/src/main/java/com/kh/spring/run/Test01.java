package com.kh.spring.run;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

// 직접 도구를 생성해서 이메일을 보내는 예제

// 필요한 외부도구
// gmail계정
// ezfkukuesroejkqz
public class Test01 {

	public static JavaMailSenderImpl sender; // 얘는 전송도구
	public static void main(String[] args) {
		
		JavaMailSenderImpl impl = new JavaMailSenderImpl();
		
		// impl에 필요한 설정을 수행 -> 나는 이제 Gmail을 사용할거야
		// 계정설정
		impl.setHost("smtp.gmail.com");
		impl.setPort(587);
		impl.setUsername("haveway@gmail.com");
		impl.setPassword("ezfkukuesroejkqz");

		// 옵션설정 Map<Object, Object> 형태
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", true);
		
		impl.setJavaMailProperties(prop);
		
		// sender에 대입
		sender = impl;
		
		// 메시지 생성
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setSubject("이러면가나?");
		message.setText("잘간다~");
		
		String[] to = {"haveway@gmail.com"};
		message.setTo(to);	
	
		String[] cc = {"haveway@gmail.com"};
		message.setCc(cc);
		
		sender.send(message);
		
	}

}
