package com.tarena.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.common.BaseReader;
import com.tarena.entity.Note;
import com.tarena.entity.Result;
import com.tarena.entity.Share;
import com.tarena.entity.User;
import com.tarena.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {
	private static Logger log = Logger.getLogger(UserController.class.getName());

	@Resource
	private NoteService noteService;

	@RequestMapping("/findNote.do")
	@ResponseBody
	public Result findNote(String noteBookId) {
		log.info("笔记查询请求");
		List<Note> list = noteService.findNote(noteBookId);
		log.info("笔记查询请求成功");
		return new Result(list);
	}

	@RequestMapping("/findNoteDetail.do")
	@ResponseBody
	public Result findNoteDetail(String noteId) {
		log.info(noteId+"笔记内容查询请求");
		Note note = noteService.findNoteDetail(noteId);
		log.info(noteId+"笔记内容查询成功");
		return new Result(note);
	}

	@RequestMapping("/addNote.do")
	@ResponseBody
	public Result addNote(Note note, HttpSession session) {
		log.info("新增笔记");
		User user = (User) session.getAttribute("user");
		note.setCn_user_id(user.getCn_user_id());
		noteService.addNote(note);
		log.info(note);
		log.info("笔记新增成功");
		return new Result(note);
	}

	@RequestMapping("/updateNote.do")
	@ResponseBody
	public Result updateNote(Note note) {
		log.info("笔记修改");
		noteService.updateNoteBody(note);
		log.info(note);
		log.info("笔记修改成功");
		return new Result();
	}

	@RequestMapping("/deleteNote.do")
	@ResponseBody
	public Result deleteNote(String noteId) {
		log.info("笔记删除");
		noteService.deleteNote(noteId);
		log.info("笔记删除成功");
		return new Result();
	}

	@RequestMapping("/moveNote.do")
	@ResponseBody
	public Result moveNote(String noteId, String noteBookId) {
		noteService.moveNote(noteId, noteBookId);
		return new Result();
	}

	@RequestMapping("/shareNote.do")
	@ResponseBody
	public Result shareNote(String noteId) {
		log.info("分享笔记");
		noteService.addShareNote(noteId);
		log.info("分享笔记成功");
		return new Result();
	}

	@RequestMapping("/deleteRecycleNote.do")
	@ResponseBody
	public Result deleteRecycleNote(String noteId) {
		log.info("彻底删除笔记");
		noteService.deleteNoteReally(noteId);
		log.info("彻底笔记成功");
		return new Result();
	}

	@RequestMapping("/searchShareNote.do")
	@ResponseBody
	public Result searchShareNote(String searchKey, int currentPage) {
		log.info("搜索分享笔记");
		List<Share> list = noteService.findShareNote(searchKey, currentPage,
				BaseReader.getPageSize());
		log.info("搜索分享笔记成功");
		return new Result(list);
	}

	@RequestMapping("/findShareNoteDetail.do")
	@ResponseBody
	public Result findShareNoteDetail(String shareId) {
		log.info("搜索分享笔记内容");
		Share share = noteService.findShareNoteDetail(shareId);
		log.info("搜索分享笔记内容成功");
		return new Result(share);
	}

	@RequestMapping("/likeShareNote.do")
	@ResponseBody
	public Result likeShareNote(String shareId, HttpSession session) {
		log.info("收藏分享笔记");
		User user = (User) session.getAttribute("user");
		noteService.likeShareNote(shareId, user.getCn_user_id());
		log.info("收藏分享笔记成功");
		return new Result();
	}

	@RequestMapping("/likeActivityNote.do")
	@ResponseBody
	public Result likeActivityNote(String noteActivityId, HttpSession session) {
		log.info("收藏活动笔记");
		User user = (User) session.getAttribute("user");
		noteService.likeActivityNote(noteActivityId, user.getCn_user_id());
		log.info("收藏活动笔记成功");
		return new Result();
	}

}
