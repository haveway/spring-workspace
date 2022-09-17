package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	// 헤더의 게시판 클릭 시 => list.bo
	// 페이징바 클릭 시 => list.bo?currentPage=요청하는 페이지의 번호
	@RequestMapping("list.bo")
	public String selectList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		
		//System.out.println("cpage : " + currentPage);
		
		PageInfo pi = Pagination.getPageInfo(boardService.selectListCount(), currentPage, 10, 5);
		
		ArrayList<Board> list = boardService.selectList(pi);
		
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		
		// forwarding
		return "board/boardListView";
	}
	
	// ModelAndView 방식
	/*
	@RequestMapping("list.bo")
	public String selectList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv) {
		
		PageInfo pi = Pagination.getPageInfo(boardService.selectListCount(), currentPage, 10, 5);
		

		mv.addObject("pi", pi)
		  .addObject("list", boardService.selectList(pi))
		  .setViewName("board/boardListView");

		return mv;
	}
	*/
	
	@RequestMapping("enrollForm.bo")
	public String enrollForm() {
		return "board/boardEnrollForm";
	}
	
	@RequestMapping("insert.bo")
	public String insertBoard(Board b, MultipartFile upfile, HttpSession session, Model model) {
		
		//System.out.println(b);
		//System.out.println(upfile);
		
		// 전달된 파일이 있을 경우 => 파일명 수정 후 서버 업로드 => 원본명, 서버 업로드된 경로를 b에 담기(파일이 있을 때만)
		if( !upfile.getOriginalFilename().equals("") ) { // getOriginalFilename() == filename 필드값을 반환해줌
			
			String changeName = saveFile(upfile, session);
			
			// Board b에 originName과 ChangeName을 set해주기
			b.setOriginName(upfile.getOriginalFilename());
			b.setChangeName("resources/uploadFiles/" + changeName);
		}

		int result = boardService.insertBoard(b);
		
		if(result > 0) { // 성공=> 게시글 리스트 페이지(boardListView.jsp)
			session.setAttribute("alertMsg", "게시글 작성 성공!!");
			return "redirect:list.bo";
		} else { // 실패 => 에러페이지
			model.addAttribute("errorMsg", "게시글을 못 쓰겠어요 ㅠㅠ");
			return "common/errorPage";
		}
	}
	
	// 실제 넘어온 파일의 이름을 변경해서 서버에 업로드
	public String saveFile(MultipartFile upfile, HttpSession session) {
		// 파일 명 수정 후 서버에 업로드시키기("flower4.jpg" => 2022072235655.jpg)
		String originName = upfile.getOriginalFilename();
		
		// 2022072202251234012(년월일시분초)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		// 5자리 랜덤값
		int ranNum = (int)(Math.random() * 90000) + 10000; 
		
		// 확장자
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 수정된 첨부파일명
		String changeName = currentTime + ranNum + ext;
		 
		// 첨부파일 저장할 폴더의 물리적인 경로 
		String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
		
		// 
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return changeName;
		
	}
	/*
	@RequestMapping("detail.bo")
	public String selectBoard(int boardNo, Model model) {
		// 식별값 가지고 DB에 가서 어떤 조건식의 조건값으로 사용
		
		// 해당 게시글의 조회수 증가용 서비스를 호출 & 결과 받기 (update)
		int result = boardService.increaseCount(boardNo);
		// -> 조회수 증가 성공!
		// boardDetailView.jsp 상에 필요한 데이터를 조회(게시글 상세보기 조회용 서비스 호출)
		// 조회된 데이터 담아서 board/boardDetailView로 포워딩
		if(result > 0) {
			Board b = boardService.selectBoard(boardNo);
			model.addAttribute("b", b);
			return "board/boardDetailView";
		} else {
		// -> 조회수 증가 실패!
		// 에러문구 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "어딜 볼라고~~");
			return "common/errorPage";
		}
		
	}*/
	
	@RequestMapping("detail.bo")
	public ModelAndView selectBoard(ModelAndView mv, int bno) {
		
		int result = boardService.increaseCount(bno);
		
		if(result > 0) {
			Board b = boardService.selectBoard(bno);
			mv.addObject("b", b).setViewName("board/boardDetailView");
		} else {
			mv.addObject("errorMsg", "상세조회실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping("delete.bo")
	public String deleteBoard(int bno, String filePath, HttpSession session, Model model) {
		
		int result = boardService.deleteBoard(bno);
		
		if(result > 0) { // 삭제성공
			// 만약에 첨부파일이 있엇을 경우 삭제하기
			if(!filePath.equals("")) {
				// 기존에 존재하는 첨부파일 삭제
				// "resources/uploadFiles//xxxx.jpg" 이걸 찾으려면 물리적인 경로가 필요
				new File(session.getServletContext().getRealPath(filePath)).delete();
			}
			// 게시판 리스트페이지로 list.bo url재요청
			session.setAttribute("alertMsg", "게시글 삭제 성공");
			return "redirect:list.bo";
		} else {
			model.addAttribute("errorMsg", "게시글 삭제 실패");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("updateForm.bo")
	public String updateForm(int bno, Model model) {
		
		// 현재 내가 수정하기를 클릭한 게시글에 대한 정보를 가지고
		model.addAttribute("b", boardService.selectBoard(bno));
		
		return "board/boardUpdateForm";
	}
	
	@RequestMapping("update.bo") // @ModelAttribute가 항상 생략되고 있었음
	public String updateBoard(@ModelAttribute/*가시성을 위해 쓸수 있음*/ Board b, MultipartFile reupfile, HttpSession session, Model model) {
		
		// 새로 첨부파일이 넘어온 경우
		if(!reupfile.getOriginalFilename().equals("")) {
			// 기존에 첨부파일이 있었다면? => 기존의 첨부파일을 지우기
			if(b.getOriginName() != null) {
				new File(session.getServletContext().getRealPath(b.getChangeName())).delete();
			}
			// 새로 넘어온 첨부파일을 서버에 업로드 시키기
			// saveFile()을 호출해서 현재 넘어온 첨부파일을 서버에 저장시키자!
			String changeName = saveFile(reupfile, session);
			
			// b라는 Board객체에 새로운 정보(원본명, 저장경로) 담기
			b.setOriginName(reupfile.getOriginalFilename());
			b.setChangeName("resources/uploadFiles/" + changeName);
		}
		
		/*
		 * b에 boardTitle, boardContent
		 * 
		 *  1. 새로 첨부된 파일 X, 기존 첨부 파일 X
		 *  	=> originName : null, changeName = null
		 *  
		 *  2. 새로 첨부된 파일 X, 기존 첨부 파일 O
		 *  	=> originName : 기존 첨부파일의 이름, changeName : 기존 첨부파일 경로
		 *  
		 *  3. 새로 첨부된 파일O, 기존 첨부 파일 X
		 *  	=> originName : 새로 첨부된 파일의 이름, changeName : 새로 첨부된 파일 경로
		 *  
		 *  4. 새로 첨부된 파일 O, 기존 첨부 파일 O
		 *  	=> originName : 새로 첨부된 파일의 이름, changeName : 새로 첨부된 파일 경로
		 */
		int result = boardService.updateBoard(b);
		
		if(result > 0) {
			session.setAttribute("alertMsg", "게시글수정 성공");
			return "redirect:detail.bo?bno=" + b.getBoardNo();
		} else {
			model.addAttribute("errorMsg", "이번엔 무슨 잘못을 했느냐!!");
			return "common/errorPage";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "rlist.bo", produces = "application/json; charset=UTF-8")
	public String ajaxSelectReplyList(int bno) {
		
		// ArrayList<Reply> list = boardService.selectReply(bno);
		
		return new Gson().toJson(boardService.selectReply(bno));
	}
	
	@ResponseBody
	@RequestMapping("rinsert.bo")
	public String ajaxInsertReply(Reply r) { // 성공했을때는 success, 실패했을때는 fail
		
		return boardService.insertReply(r) > 0 ? "success" : "fail";
		
	}
	
	@ResponseBody
	@RequestMapping(value="topList.bo", produces="application/json; charset=UTF-8")
	public String ajaxTopBoardList() {
		
		return new Gson().toJson(boardService.selectTopBoardList());
	}
	
	
}
