package com.kh.spring.member.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.entity.CertVo;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@Controller // Controller타입의 어노테이션을 빈(Bean) 스캐닝을 통해 자동으로 빈등록
public class MemberController {
	
	// private MemberService mService = new MemberServiceImpl();
	
	/*
	 * 기존 객체 생성 방식
	 * 객체간의 결합도가 높아짐(소스코드의 수정이 일어날 경우 하나하나 전부 다 바꿔줘야한다.)
	 * 서비스가 동시에 매우 많은 횟수가 요청될 경우 그만큼 객체가 생성된다
	 * 
	 * Spring의 DI(Dependency Injection)를 이용한 방식
	 * 객체를 생성해서 주입해줌
	 * new라는 키워드 없이 선언만!!!! @Autowired라는 어노테이션을 반드시 사용해야함!!!!!!!!!!!
	 */
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private JavaMailSender sender;
	
	/*
	 * 				   value 안써도 됨!
	@RequestMapping(value="login.me") // RequestMapping타입의 어노테이션을 붙여줌으로써  HandlerMapping 등록!
	public void loginMember() {
		
	}
	*/
	
	/*
	 * Spring에서 파라미터(요청 시 전달 값)를 받는 방법
	 * 
	 * 1. HttpServletRequest를 이용해서 전달받기(JSP/Servlet 방식)
	 * 			해당 메소드의 매개변수로 HttpServletRequest를 작성해두면
	 * 			스프링 컨테이너가 해당 메소드를 호출 시 자동으로 해당 객체를 생성해서 매개변수로 주입해줌!
	 */
	
	/*
	 *1번 방법
		@RequestMapping("login.me")
		public String loginMember(HttpServletRequest request) {
			
			String userId = request.getParameter("id");
			String userPwd = request.getParameter("pwd");
			
			System.out.println("userId : " + userId);
			System.out.println("userPwd : " + userPwd);
			
			return "main";
			/WEB-INF/views/main.jsp
			
		}
	*/
	
	/*
	 * 2. @RequestParam 어노테이션을 이용하는 방법
	 * request.getParameter("키")로 밸류를 뽑아오는 역할을 대신해주는 어노테이션
	 * value속성의 밸류로 jsp에서 작성했던 name속성값을 담으면 알아서 해당 매개변수로 받아올 수 있다.
	 * 만약, 넘어온 값이 비어있는 형태라면 defaultValue속성으로 기본값을 지정해 줄 수 있다.
	 */
	
	/*
	@RequestMapping("login.me")
	public String loginMember(@RequestParam(value="id", defaultValue="aaa") String userId, @RequestParam("pwd") String userPwd) {
		
		System.out.println("유저아이디 : " + userId);
		System.out.println(" 유저비번  : " + userPwd);
		
		return "main";
	}
	*/
	
	/*
	 * 3. @RequestParam 어노테이션을 생략하는 방법
	 * 단, 매개변수명을 jsp의 name속성값(요청 시 전달하는 값의 키값)과 동일하게 세팅해줘야 자동으로 값이 주입됨
	 * 위에서 썼던 defaultValue 추가 속성은 사용할 수 없음
	 */
	
	/*
	@RequestMapping("login.me")
	public String loginMember(String id, String pwd) {
		
		System.out.println("유저아이디 : " + id);
		System.out.println("  유저비번  : " + pwd);
		
		Member m = new Member();
		m.setUserId(id);
		m.setUserPwd(pwd);
		// 서비스에 전달~~어쩌고저쩌고~~
		
		return "main";
	}
	*/
	
	/*
	 * 4. 커맨드 객체 방식
	 * 
	 * 해당 메소드의 매개변수로
	 * 요청 시 전달값을 담고자하는 VO클래스의 타입을 셋팅 후
	 * 요청 시 전달값의 키값(jsp의 name속성값)을 VO클래스에 담고자하는 필드명으로 작성
	 * 
	 * ** 반드시!!! name속성값과 담고자하는 필드명이 동일해야함!!! + 기본생성자 + setter
	 * 
	 */
	
	/*
	@RequestMapping("login.me")
	public String loginMember(Member m) {
		
		//System.out.println("유저아이디 : " + m.getUserId());
		//System.out.println("  유저비번  : " + m.getUserPwd());
		Member loginUser = memberService.loginMember(m);
		
		if(loginUser == null) { // 로그인 실패 => 에러 문구를 requestScope에 담고 에러페이지로 포워딩
			System.out.println("로그인 실패");
		} else { // 로그인 성공 => sessionScope에 담고 메인페이지로 url 재요청
			System.out.println("로그인 성공");
		}
		
		return "main";
	}
	*/
	
	/*
	 * 요청 처리 후 응답데이터를 담고 응답페이지로 포워딩 또는 url재요청 하는 방법
	 * 
	 * 1. 스프링에서 제공하는 Model객체를 사용하는 방법
	 * 포워딩할 응답뷰로 전달하고자하는 데이터를 맵형식(key-value)으로 담을 수 있는 영역
	 * Model객체는 requestScope
	 * 단, setAttribute가 아닌 addAttribute메소드를 이용해야한다!!!
	 * 
	 */
	
	/*
	@RequestMapping("login.me")
	public String loginMember(Member m, Model model, HttpSession session) {
		
		Member loginUser = memberService.loginMember(m);
		
		if(loginUser == null) { // 로그인 실패 => 에러문구를 requestScope에 담아서 에러페이지로 포워딩
			
			model.addAttribute("errorMsg", "에러났다~ 켘케케켘케케케케케");
			
			// * 포워딩 방식(파일 경로를 포함한 파일명을 제시)
			// return "XXX";
			// servlet-context.xml의 주소의 자동완성을 등록해주는 bean(도구) : view resolver
			// 접두어 : /WEB-INF/views/
			// - 내가 적을 중간 : XXX(=파일명)
			// 접미어 : .jsp
			
			// 		/WEB-INF/views/	     /common/errorPage          .jsp
			return "/common/errorPage";
			
		} else { // 로그인 성공 => loginUser를 sessionScope에 담고 메인페이지로 url재요청
			
			session.setAttribute("loginUser", loginUser);
			
			// * url 재요청 방식 == sendRedirect 방식 (url제시)
			// redirect : 요청할 url
			
			// localhost:8006/spring + /
			
			return "redirect:/";
		}
	}
	*/
	
	/*
	 * 2. 스프링에서 제공하는 ModelAndView 객체를 사용하는 방법
	 * Model은 데이터를 key-value세트로 담을 수 있는 공간이라고 한다면
	 * View는 응답뷰에 대한 정보를 담을 수 있는 공간
	 * 
	 * 단, Model객체는 단독 존재가 가능하지만, View객체는 그럴 수 없다.
	 * Model하고View가 결합된 형태
	 */
	
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {
		
		// 암호화 작업 전
		/*
		Member loginUser = memberService.loginMember(m);
		
		if(loginUser == null) {
			// model.addAttribute("키", 밸류);
			mv.addObject("errorMsg", "로그인안돼 ~ 돌아가 ~ ㅎㅎ");
			mv.setViewName("common/errorPage"); 
		} else {
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		}
		*/
		
		// 암호화 작업 후
		// Member m의 userId : 사용자가 입력한 아이디
		// 			 userPwd : 사용자가 입력한 비밀번호(평문)
		
		Member loginUser = memberService.loginMember(m);
		
		// loginUser : 오로지 아이디만을 가지고 조회된 회원
		// loginUser의 userPwd필드 : DB에 기록된 암호화된 비밀번호
		
		// BCryptPasswordEncoder 객체 matches()
		// matches(평문, 암호문)을 작성하면 내부적으로 복호화 등의 작업이 이루어짐
		// 두 구문이 일치하는지 비교 후 일치하면 true
		
		if( loginUser != null && bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd() )) {
			// 로그인 성공
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		} else {
			// 로그인 실패
			mv.addObject("errorMsg", "로그인 실패!").setViewName("common/errorPage");;
		}
		return mv;
	}
	
	/*
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	*/
	
	@RequestMapping("logout.me")
	public ModelAndView logoutMember(HttpSession session, ModelAndView mv) {
		
		session.removeAttribute("loginUser");
		
		mv.setViewName("redirect:/");
		
		return mv;
	}
	
	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		return "member/memberEnrollForm";
	}
	
	
	@RequestMapping("insert.me")
	public String insertMember(Member m, Model model, HttpSession session) {
		// System.out.println(m);
		// 1. 한글 깨짐 문제 발생 => web.xml에 스프링에서 제공하는 인코딩 필터 등록
		// 2. 나이를 입력하지 않았을 경우 int자료형에 빈 문자열이 넘어와 자료형이 맞지 않는 문제 발생
		// (400 Bad Request Error 발생)
		// => Member클래스의 age필드의 int형 -> String형으로 변경
		// 3. 비밀번호가 사용자가 입력한 있는 그대로의 평문
		// Bcrypt방식 사용할거임
		// => 스프링 시큐리티 모듈에서 제공(pom.xml에 라이브러리 추가)
		
		//System.out.println("평문 : " + m.getUserPwd());
		
		// 암호화 작업(암호문을 만들어내는 과정)
		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
		
		//System.out.println("암호문 : " + encPwd);
		
		m.setUserPwd(encPwd); // Member객체에 userPwd필드에 평문이 아닌 암호문으로 변경
		
		int result = memberService.insertMember(m);
		
		if(result > 0) {
			session.setAttribute("alertMsg", "성공적으로 회원가입이 되었습니다.");
			return "redirect:/";
		} else {
			model.addAttribute("errorMsg", "회원가입실패");
			return "common/errorPage";
		}
		
	}
	
	@RequestMapping("myPage.me")
	public String myPage() {
		return "member/myPage";
	}
	
	@RequestMapping("update.me")
	public String updateMember(Member m, Model model, HttpSession session) {
		
		int result = memberService.updateMember(m);
		
		if(result > 0) {
			// DB로부터 수정된 회원정보를 다시 조회해서
			// session에 loginUser라는 키값으로 덮어씌워야함!!
			session.setAttribute("loginUser", memberService.loginMember(m));
			session.setAttribute("alertMsg", "회원정보 수정 성공~!");
			return "redirect:myPage.me";
		} else {
			model.addAttribute("errorMsg", "정보수정에 실패했습니다!!!");
			return "common/errorPage";
		}
		
	}
	
	// 내가 쓴 회원탈퇴 기능
	/*
	@RequestMapping("delete.me")
	public String deleteMember(Member m, Model model, HttpSession session) {
		int result = 0;
		Member loginUser = memberService.loginMember(m);
		if(loginUser != null && bcryptPasswordEncoder.matches(m.getUserPwd(), ( loginUser.getUserPwd())) ) {
			result = memberService.deleteMember(m.getUserId());
		}
			if(result > 0) {
				session.removeAttribute("loginUser");
				session.setAttribute("alertMsg", "회원 탈퇴 성공!!!");
				return "redirect:/";
			} else {
				model.addAttribute("errorMsg", "회원 탈퇴 실패!!!");
				return "common/errorPage";
			}
	}
	*/
	
	@RequestMapping("delete.me")
	public String deleteMember(Member m, Model model, HttpSession session) {
		
		String encPwd = ((Member)session.getAttribute("loginUser")).getUserPwd();
		
		if(bcryptPasswordEncoder.matches(m.getUserPwd(), encPwd)) { // 비밀번호 일치
			
			int result = memberService.deleteMember(m.getUserId());
			if(result > 0) { // 탈퇴처리 성공 
				session.removeAttribute("loginUser");
				session.setAttribute("alertMsg", "탈퇴가 성공적으로 이루어졌습니다.");
				return "redirect:/";
			} else { // 탈퇴처리 실패
				session.setAttribute("alertMsg", "먼가..문제가 있음...");
				return "common/errorPage";
			}
			
		} else { // 비밀번호 불일치
			session.setAttribute("alertMsg", "비밀번호를 다시 입력해주세요!");
			return "redirect:myPage.me";
		}
		
	}
	@ResponseBody
	@RequestMapping("idCheck.me")
	public String idCheck(String checkId) {
		
		/*
		int result = memberService.idCheck(checkId);
		if(result > 0) { // 이미 존재하는 아이디 => 사용불가능(NNNNN)
			return "NNNNN";
		} else { // 사용가능(NNNNY)
			return "NNNNY";
		}
		*/
		return memberService.idCheck(checkId) > 0 ? "NNNNN" : "NNNNY";
	}
	
	@GetMapping("input")
	public String input() {
		return "member/input";
	}
	
	@GetMapping("check")
	public String check() {
		return "member/check";
	}
	
	@PostMapping("input")
	public String input(String email, HttpServletRequest request) throws MessagingException {
		
		String ip = request.getRemoteAddr();
		
		String secret = memberService.sendMail(ip);
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(email);
		helper.setSubject("인증해라이놈아");
		helper.setText("인증번호 : " + secret);
		
		sender.send(message);
		
		
		return "redirect:check";
	}
	
	@ResponseBody
	@PostMapping("check")
	public String check(String secret, HttpServletRequest request) {
		
		boolean result = memberService.validate(CertVo.builder().who(request.getRemoteAddr()).secret(secret).build());
		
		return "abc";
	}
	
}
