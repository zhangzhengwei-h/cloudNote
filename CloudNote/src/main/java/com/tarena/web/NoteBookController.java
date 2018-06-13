package com.tarena.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.entity.Note;
import com.tarena.entity.NoteBook;
import com.tarena.entity.Result;
import com.tarena.entity.User;
import com.tarena.service.NoteBookService;
import com.tarena.service.NoteService;

@Controller
@RequestMapping("/notebook")
public class NoteBookController extends BaseController {
	private static Logger log = Logger.getLogger(UserController.class.getName());

	@Resource
	private NoteBookService noteBookService;

	@Resource
	private NoteService noteService;
	
	@RequestMapping("/findNormal.do")
	@ResponseBody
	public Result findNormal(HttpSession session) {
		User user = (User) session.getAttribute("user");
		log.info(user.getCn_user_name()+"	普通笔记本查询!");
		List<NoteBook> list = noteBookService.findNormalNoteBook(user.getCn_user_id());
		log.info(user.getCn_user_name()+"	普通笔记本查询!"+"	成功");
		return new Result(list);
	}

	@RequestMapping("/findSpecial.do")
	@ResponseBody
	public Result findSpecial(HttpSession session) {
		User user = (User) session.getAttribute("user");
		log.info(user.getCn_user_name()+"	特殊笔记本查询!");
		Map<String, NoteBook> map = noteBookService.findSpecialNoteBook(user
				.getCn_user_id());
		log.info(user.getCn_user_name()+"	特殊笔记本查询!"+"	成功");
		return new Result(map);
	}

	@RequestMapping("/addNoteBook.do")
	@ResponseBody
	public Result addNoteBook(NoteBook noteBook, HttpSession session) {
		User user = (User) session.getAttribute("user");
		log.info(user.getCn_user_name()+"	新增笔记本："+noteBook.getCn_notebook_name());
		noteBook.setCn_user_id(user.getCn_user_id());
		noteBookService.addNoteBook(noteBook);
		log.info(user.getCn_user_name()+"	新增笔记本："+noteBook.getCn_notebook_name()+"	成功!");
		return new Result(noteBook);
	}

	@RequestMapping("/updateNoteBookName.do")
	@ResponseBody
	public Result updateNoteBookName(NoteBook noteBook) {
		log.info("修改笔记本："+noteBook.getCn_notebook_name());
		noteBookService.update(noteBook);
		log.info("修改笔记本："+noteBook.getCn_notebook_name()+"		成功");
		return new Result();
	}

	@RequestMapping("/deleteNoteBook.do")
	@ResponseBody
	public Result deleteNoteBook(String noteBookId) {
		log.info("删除笔记本，ID为："+noteBookId);
		noteService.deleteNoteByNoteBookId(noteBookId);
		noteBookService.deleteNoteBook(noteBookId);
		log.info("删除笔记本，ID为："+noteBookId+"	成功。");
		return new Result();
	}

	@RequestMapping("/findList.do")
	@ResponseBody
	public Result findList(HttpSession session) {
		User user = (User) session.getAttribute("user");
		log.info(user.getCn_user_name()+"笔记本列表查询!");
		List<NoteBook> list = noteBookService.findNoteBookList(user
				.getCn_user_id());
		log.info(user.getCn_user_name()+"笔记本列表查询"+"	成功");
		return new Result(list);
	}

}
