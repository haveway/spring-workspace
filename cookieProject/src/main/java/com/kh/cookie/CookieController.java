package com.kh.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CookieController {
	
	@RequestMapping("create")
	public String create(HttpServletResponse response) {
		// 쿠키는 객체를 생성한 다음 응답정보에 첨부해야 완전히 발급
		// name, value속성을 필수로 작성을 해줘야함. expire등은 선택
		// name과 value 모두 문자열만 가능(아스키코드만)
		
		Cookie ck = new Cookie("test", "academy");
		ck.setMaxAge(60 * 60 * 24 * 1);
		
		response.addCookie(ck);
		
		Cookie si = new Cookie("saveId", "kh");
		si.setMaxAge(60 * 60 * 24 * 1);
		response.addCookie(si);
		
		Cookie pop = new Cookie("pop", "ad");
		pop.setMaxAge(60 * 60 * 24 * 1);
		response.addCookie(pop);
		
		return "cookie/create";
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletResponse response) {
		// 쿠키는 삭제 명령어가 따로 없음
		// 0초로 만료시간을 지정 후 덮어 쓰기를 수행
		Cookie ck = new Cookie("test", "academy");
		ck.setMaxAge(0);
		
		response.addCookie(ck);
		return "cookie/delete";
	}
	
	@RequestMapping("list1")
	public String list1() {
		return "cookie/list1";
	}
	
	@ResponseBody
	@RequestMapping("list2")
	public String list2(@CookieValue(required = false) Cookie test) {
		
		if(test != null) {
			System.out.println(test.getValue());
			
		} else {
			System.out.println("쿠키없음");
		}
		
		return "list2";
	}

}
