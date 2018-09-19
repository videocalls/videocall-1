package com.coderby.myapp.board.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderby.myapp.board.model.Board;
import com.coderby.myapp.board.model.BoardCategory;
import com.coderby.myapp.board.model.BoardFile;
import com.coderby.myapp.board.service.IBoardCategoryService;
import com.coderby.myapp.board.service.IBoardService;

@Controller
public class BoardController {
	static final Logger logger = Logger.getLogger(BoardController.class);

	@Autowired
	IBoardService boardService;
	
	@Autowired
	IBoardCategoryService categoryService;
		
	@RequestMapping("/board/cat/{categoryId}")
	public String getListByCategory(@PathVariable int categoryId, HttpSession session, Model model) {
		return getListByCategory(categoryId, 1, session, model);
	}

	@RequestMapping("/board/cat/{categoryId}/{page}")
	public String getListByCategory(@PathVariable int categoryId, @PathVariable int page, HttpSession session, Model model) {
		session.setAttribute("page", page);
//		model.addAttribute("page", page);
		model.addAttribute("categoryId", categoryId);

		List<Board> boardList = boardService.selectArticleListByCategory(categoryId, page);
		model.addAttribute("boardList", boardList);

		// paging start
		int bbsCount = boardService.selectTotalArticleCountByCategoryId(categoryId);
		int totalPage = 0;
		if(bbsCount > 0) {
			totalPage= bbsCount/10;
		}
		if( (bbsCount % 10) != 0 || totalPage==0 ) {
			totalPage = totalPage+1;
		}
		model.addAttribute("totalPageCount", totalPage);
		model.addAttribute("page", page);
	
		return "board/list";
	}
	
	@RequestMapping("/board/list")
	public String getBoardList(HttpSession session, Model model) {
//		model.addAttribute("page", page);
		
		List<Board> boardList = boardService.selectArticleList((Integer)session.getAttribute("page"));
		model.addAttribute("boardList", boardList);

		// paging start
		int bbsCount = boardService.selectTotalArticleCount();
		int totalPage = 0;
		if(bbsCount > 0) {
			totalPage= bbsCount/10;
		}
		if( (bbsCount % 10) != 0 || totalPage==0 ) {
			totalPage = totalPage+1;
		}
		model.addAttribute("totalPageCount", totalPage);
	
		return "board/list";
	}

	@RequestMapping("/board/{boardId}/{page}")
	public String getBoardDetails(@PathVariable int boardId, @PathVariable int page, Model model) {
		Board board = boardService.selectArticle(boardId);

		if(board.getContent() != null) {
			board.setContent(board.getContent().replaceAll("\\[\\[h4", "<h4"));
			board.setContent(board.getContent().replaceAll("\\[\\[/h4", "</h4"));
			board.setContent(board.getContent().replaceAll("\\[\\[b", "<b"));
			board.setContent(board.getContent().replaceAll("\\[\\[/b", "</b"));
			board.setContent(board.getContent().replaceAll("\\[\\[/pre", "</pre"));
			board.setContent(board.getContent().replaceAll("\\[\\[pre", "<pre"));
			board.setContent(board.getContent().replaceAll("\\[\\[img", "<img"));
			board.setContent(board.getContent().replaceAll("\\[\\[video", "<video"));
			board.setContent(board.getContent().replaceAll("\\[\\[/video", "</video"));
			board.setContent(board.getContent().replaceAll("\\[\\[source", "<source"));
			board.setContent(board.getContent().replaceAll("\\[\\[/source", "</source"));
			board.setContent(board.getContent().replaceAll("\\[\\[a", "<a"));
			board.setContent(board.getContent().replaceAll("\\[\\[/a", "</a"));
			board.setContent(board.getContent().replaceAll("\\]\\]", ">"));	
		}
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		model.addAttribute("categoryId", board.getCategoryId());
		logger.info("getBoardDetails " + board.toString());
		return "board/view";
	}

	@RequestMapping("/board/{boardId}")
	public String getBoardDetails(@PathVariable int boardId, Model model) {
		Board board = boardService.selectArticle(boardId);

		if(board.getContent() != null) {
			board.setContent(board.getContent().replaceAll("\\[\\[h4", "<h4"));
			board.setContent(board.getContent().replaceAll("\\[\\[/h4", "</h4"));
			board.setContent(board.getContent().replaceAll("\\[\\[b", "<b"));
			board.setContent(board.getContent().replaceAll("\\[\\[/b", "</b"));
			board.setContent(board.getContent().replaceAll("\\[\\[/pre", "</pre"));
			board.setContent(board.getContent().replaceAll("\\[\\[pre", "<pre"));
			board.setContent(board.getContent().replaceAll("\\[\\[img", "<img"));
			board.setContent(board.getContent().replaceAll("\\[\\[video", "<video"));
			board.setContent(board.getContent().replaceAll("\\[\\[/video", "</video"));
			board.setContent(board.getContent().replaceAll("\\[\\[source", "<source"));
			board.setContent(board.getContent().replaceAll("\\[\\[/source", "</source"));
			board.setContent(board.getContent().replaceAll("\\[\\[a", "<a"));
			board.setContent(board.getContent().replaceAll("\\[\\[/a", "</a"));
			board.setContent(board.getContent().replaceAll("\\]\\]", ">"));	
		}
		model.addAttribute("board", board);
		model.addAttribute("categoryId", board.getCategoryId());
		logger.info("getBoardDetails " + board.toString());
		return "board/view";
	}
	
	@RequestMapping(value="/board/write/{categoryId}", method=RequestMethod.GET)
	public String writeArticle(@PathVariable int categoryId, Model model) {
		List<BoardCategory> categoryList = categoryService.selectAllCategory();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", categoryId);
		return "board/write";
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String writeArticle(Board board, BindingResult result, RedirectAttributes redirectAttrs) {
		logger.info("/board/write : " + board.toString());

//	    if(result.hasErrors()) {
//	    	logger.debug(result.getErrorCount());
//	        return "board/write";
//	    }

		try{
			MultipartFile mfile = board.getFile();
			if(mfile!=null && !mfile.isEmpty()) {
				logger.info("/board/write : " + mfile.getOriginalFilename());
				BoardFile file = new BoardFile();
				file.setFileName(mfile.getOriginalFilename());
				file.setFileSize(mfile.getSize());
				file.setFileContentType(mfile.getContentType());
				file.setFileData(mfile.getBytes());
				logger.info("/board/write : " + file.toString());

				boardService.insertArticle(board, file);
			}else {
				boardService.insertArticle(board);
			}
		}catch(Exception e){
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/board/cat/"+board.getCategoryId();
	}

	@RequestMapping("/file/{fileId}")
	public ResponseEntity<byte[]> getFile(@PathVariable int fileId) {
		BoardFile file = boardService.getFile(fileId);
		logger.info("getFile " + file.toString());
		final HttpHeaders headers = new HttpHeaders();
		String[] mtypes = file.getFileContentType().split("/");
		headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
		headers.setContentLength(file.getFileSize());
		headers.setContentDispositionFormData("attachment", file.getFileName(), Charset.forName("UTF-8"));
		return new ResponseEntity<byte[]>(file.getFileData(), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/board/reply/{boardId}", method=RequestMethod.GET)
	public String replyArticle(@PathVariable int boardId, Model model) {
		Board board = boardService.selectArticle(boardId);
		board.setWriter("");
		board.setEmail("");
		board.setTitle("[Re]"+board.getTitle());
		board.setContent("\n\n\n----------\n" + board.getContent());
		model.addAttribute("board", board);
		model.addAttribute("next", "reply");
		return "board/reply";
	}
	
	@RequestMapping(value="/board/reply", method=RequestMethod.POST)
	public String replyArticle(Board board, BindingResult result, RedirectAttributes redirectAttrs, HttpSession session) {
		logger.info("/board/reply : " + board.toString());

//	    if(result.hasErrors()) {
//	    	logger.debug(result.getErrorCount());
//	        return "board/write";
//	    }

		try{
			MultipartFile mfile = board.getFile();
			if(mfile!=null && !mfile.isEmpty()) {
				logger.info("/board/reply : " + mfile.getOriginalFilename());
				BoardFile file = new BoardFile();
				file.setFileName(mfile.getOriginalFilename());
				file.setFileSize(mfile.getSize());
				file.setFileContentType(mfile.getContentType());
				file.setFileData(mfile.getBytes());
				logger.info("/board/reply : " + file.toString());

				boardService.replyArticle(board, file);
			}else {
				boardService.replyArticle(board);
			}
		}catch(Exception e){
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		
		if(session.getAttribute("page") != null) {
			return "redirect:/board/cat/"+board.getCategoryId()+"/"+(Integer)session.getAttribute("page");
		}else {
			return "redirect:/board/cat/"+board.getCategoryId(); 
		}
	}

	@RequestMapping(value="/board/update/{boardId}", method=RequestMethod.GET)
	public String updateArticle(@PathVariable int boardId, Model model) {
		List<BoardCategory> categoryList = categoryService.selectAllCategory();
		model.addAttribute("categoryList", categoryList);
		Board board = boardService.selectArticle(boardId);
		model.addAttribute("categoryId", board.getCategoryId());
		model.addAttribute("board", board);
		return "board/update";
	}

	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String updateArticle(Board board, BindingResult result, HttpSession session, RedirectAttributes redirectAttrs) {
		logger.info("/board/update " + board.toString());
		try{
			MultipartFile mfile = board.getFile();
			if(mfile!=null && !mfile.isEmpty()) {
				logger.info("/board/update : " + mfile.getOriginalFilename());
				BoardFile file = new BoardFile();
				file.setFileId(board.getFileId());
				file.setFileName(mfile.getOriginalFilename());
				file.setFileSize(mfile.getSize());
				file.setFileContentType(mfile.getContentType());
				file.setFileData(mfile.getBytes());
				logger.info("/board/update : " + file.toString());
				boardService.updateArticle(board, file);
			}else {
				boardService.updateArticle(board);
			}
		}catch(Exception e){
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}

		return "redirect:/board/"+board.getBoardId();
	}

	@RequestMapping(value="/board/delete/{boardId}", method=RequestMethod.GET)
	public String deleteArticle(@PathVariable int boardId, Model model) {
		Board board = boardService.selectDeleteArticle(boardId);
		model.addAttribute("categoryId", board.getCategoryId());
		model.addAttribute("boardId", boardId);
		model.addAttribute("replyNumber", board.getReplyNumber());
		return "board/delete";
	}
	
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public String deleteArticle(Board board, BindingResult result, HttpSession session, Model model) {
		try {
			String dbpw = boardService.getPassword(board.getBoardId());

			if(dbpw.equals(board.getPassword())) {
				boardService.deleteArticle(board.getBoardId(), board.getReplyNumber());
				return "redirect:/board/cat/"+board.getCategoryId()+"/"+(Integer)session.getAttribute("page");
			}else {
				model.addAttribute("message", "WRONG_PASSWORD_NOT_DELETED");
				return "board/error";
			}
		}catch(Exception e){
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "board/error";
		}
	}

	@RequestMapping("/board/search")
	public String search(@RequestParam(required=false, defaultValue="") String keyword, Model model) {

		List<Board> boardList = boardService.searchListByContentKeyword(keyword);
		model.addAttribute("boardList", boardList);

		// paging start
		int bbsCount = boardService.selectTotalArticleCount();
		int totalPage = 0;
		if(bbsCount > 0) {
			totalPage= bbsCount/10;
		}
		if( (bbsCount % 10) != 0 || totalPage==0 ) {
			totalPage = totalPage+1;
		}
		model.addAttribute("totalPageCount", totalPage);
		
		return "board/dlist";
	}

}
