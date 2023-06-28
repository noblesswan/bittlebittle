package com.spring.bittlebittle.notice.controller;

import com.spring.bittlebittle.notice.service.NoticeService;
import com.spring.bittlebittle.notice.vo.Notice;
import com.spring.bittlebittle.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/notices")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@Autowired
	JwtUtil jwtUtil;

	private Logger log = LogManager.getLogger("case3");

	@GetMapping(produces = "application/json; charset=utf-8")
	public List<Notice> noticeList() {
		log.debug("12321312");
		List<Notice> list = noticeService.getNoticeList();
		log.debug(list);
		return list;
	}
	@GetMapping(value = "/{noticeNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notice> noticeDetail(@PathVariable int noticeNo) {
		Notice notice = noticeService.getNotice(noticeNo);
		log.debug(notice);
		if (notice != null) {
			return new ResponseEntity<>(notice, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}