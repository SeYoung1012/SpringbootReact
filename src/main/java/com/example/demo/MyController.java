package com.example.demo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BoardService;
import com.example.demo.dao.IBoardDao;
import com.example.demo.dao.IReplyDao;
import com.example.demo.dto.BoardDto;
import com.example.demo.dto.Criteria;
import com.example.demo.dto.PageDto;
import com.example.demo.dto.ReplyDto;

@RestController
public class MyController {
	
	@Autowired
	IBoardDao boardDao;
	
	@Autowired
	IReplyDao replyDao;
	
	@Autowired
	private BoardService boardService;
	
//	
//	  @GetMapping("hello")
//	    public String hello() {
//	        return "안녕하세요. 현재 서버시간 " + new Date() + "입니다.";
//	    }
	
	
	 @GetMapping("/root")
	    public String root() {
	        //return "root()함수 호출됨.";    	
	    	System.out.println("ListForm으로 리다이렉트 됨.");
	    	//return "redirect:listForm";
	    	return "redirect:listForm";
	    	
	    }
	 
	 
	 //리액트로 바꾸어주는 컨트롤러는 map형식
    @GetMapping("/listForm")
    @ResponseBody
    public Map<String, Object> listForm(Model model, Criteria cri){
    	
    	Map<String, Object> result = new HashMap<>();
    	result.put("list",boardDao.list());
    	
    	
//    	PageDto pageDto = new PageDto(cri);
//    	
//    	int totalCount = boardService.gettotalcount(cri);
//    	pageDto.setTotalCount(totalCount);
//    	
//    	result.put("pageMaker", pageDto);
    	
    	return result;
    	
    }
    
    @PostMapping("/writeForm")
    public String writeForm(Model model) {	
    	return "/"; //writeForm.jsp 디스패치해줌.
    }
    
    @PostMapping("/writeAction")
    @ResponseBody
    public String writeAction(
    		 @RequestParam(value = "board_name", required=false) String board_name,
			  @RequestParam(value = "board_ttl",  required=false) String board_ttl, 
              @RequestParam(value = "board_cn",  required=false) String board_cn) {
    	int result = boardDao.write(board_name, board_ttl, board_cn);
    	if (result == 1) {
    		System.out.println("글쓰기 성공");
    		return"<script>alert('글쓰기성공'); location.href='/'; </script>"; //listForm으로 리다이렉트 됨.
    	}else {
    		System.out.println("글쓰기 실패");
    		return "<script>alert('글쓰기 실패'); location.href='writeForm'; </script>";
    	}

    	
    }	
        
    
    @GetMapping("/contentForm")
    @ResponseBody
    public Map<String, Object> contentForm(@RequestParam(value = "board_idx", required =false, defaultValue="0") int board_idx, Model model) {
    	//게시글 보기
    	Map<String, Object> result = new HashMap<>();
    	
    	BoardDto dto = boardDao.viewDto(board_idx);
    	result.put("board", dto);
    	//model.addAttribute("dto", dto);	
    	//System.out.println("-----dto------" + dto);
    	
    	//댓글 리스트 가져오기.
    	List<ReplyDto> reply_list = replyDao.reply_list(board_idx);
    	System.out.println("댓글리스트 -----" + reply_list);
    	//reply_list는 contentForm.jsp에서 forEach로 넘겨줄 때 items임. 
    	result.put("replyList", reply_list);
    	//return "contentForm"; //contentForm 리다이렉트됨.
    	return result;
    	
    }
    
    @PostMapping("/updateAction")
    public String updateAction(@RequestParam(value = "board_idx", required =false, defaultValue="0")int board_idx,
    						   @RequestParam(value = "board_name", required=false) String board_name,
    						   @RequestParam(value = "board_ttl",  required=false) String board_ttl, 
    						   @RequestParam(value = "board_cn",  required=false) String board_cn) {
    	
    	int result = boardDao.updateDto(board_idx,board_name, board_ttl, board_cn);
    	if (result == 1) {
    		System.out.println("글수정 성공");
    		return"<script>alert('글수정 성공'); location.href='/'; </script>"; //listForm으로 리다이렉트 됨.
    	}else {
    		//System.out.println("글수정 실패");
    		//return "redirect:contentForm?board_id="+board_id; //updateForm으로 리다이렉트 됨.
    		return"<script>location.href='/'; </script>";
    	}
    }
    
    @GetMapping("/deleteAction")
    @ResponseBody
    public String deleteAction(@RequestParam(value = "board_idx", required =false)int board_idx) {
    	int result = boardDao.deleteDto(board_idx);
    	
    	if (result == 1) {
    		System.out.println("글삭제 성공");
    		return"<script>alert('글삭제 성공'); location.href='/'; </script>";
    	}else {
    		System.out.println("글삭제 실패");
    		return"<script>alert('글삭제 실패'); location.href='contentForm?board_idx=" +board_idx+"'; </script>";
    	}
    	
    }
    
    
    @PostMapping("/writeReplyAction")
    @ResponseBody
    public String writeReplyAction(@RequestParam(value = "reply_cn", required=false) String reply_cn,
    							   @RequestParam(value = "reply_name",  required=false) String reply_name,
    							   @RequestParam(value = "reply_board_idx",  required=false) int reply_board_idx) {

		int result = replyDao.reply_write(reply_cn, reply_name,reply_board_idx);
					
		if (result == 1) {
			System.out.println("댓글쓰기 성공");
		    return"<script>alert('댓글쓰기 성공'); location.href='/'; </script>";
		}else {
			System.out.println("댓글쓰기 실패");
			return"<script>alert('댓글쓰기 실패'); location.href='contentForm?board_idx=" +reply_board_idx+"'; </script>";
		}

    }	
    
    @GetMapping("/deleteReplyAction")
    @ResponseBody
    public String deleteReplyAction(@RequestParam(value = "reply_idx", required =false)int reply_idx,	
    								@RequestParam(value = "board_idx",  required=false) int board_idx) {
    	
    	int result = replyDao.reply_deleteDto(reply_idx);
    	//System.out.println("--------reply_idx" + reply_idx);
    	
    	if (result == 1) {
    		System.out.println("댓글삭제 성공");
    		return"<script>alert('댓글삭제 성공'); location.href='contentForm?board_idx=" +board_idx+"'; </script>";
    	}else {
    		System.out.println("댓글삭제 실패");
    		return"<script>alert('댓삭제 성공'); location.href='contentForm?board_idx=" +board_idx+"'; </script>";
    	}
    	
    }
    

    
    
}
