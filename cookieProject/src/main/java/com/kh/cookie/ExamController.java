package com.kh.cookie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExamController {
	
	@RequestMapping("sign-in")
	public String signIn(){
		return "cookie/sign-in";
	}
	

	@RequestMapping("ad")
	public String ad(){
		return "cookie/ad";
	}
	
}
