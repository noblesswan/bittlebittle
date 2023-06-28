package com.spring.bittlebittle.notice.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.notice.service.AdminNoticeServiceImpl;
import com.spring.bittlebittle.notice.vo.Notice;

@RestController
@RequestMapping("api/admin/notices")
public class AdminNoticeController {
	@Autowired
	private AdminNoticeServiceImpl adminNoticeServiceImpl;
	
	Logger log = LogManager.getLogger("case3");

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Notice> noticeList() {
		return adminNoticeServiceImpl.getNoticeList();
	}

	@GetMapping(value = "/{noticeNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notice> noticeDetail(@PathVariable int noticeNo) {
		Notice notice = adminNoticeServiceImpl.getNotice(noticeNo);
		if (notice != null) {
			return new ResponseEntity<>(notice, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notice> addNotice(@RequestBody Notice notice) {
		log.debug(notice);
		adminNoticeServiceImpl.addNotice(notice);
		return new ResponseEntity<>(notice, HttpStatus.CREATED);
	}

	@PostMapping(value = "/{noticeNo}/set-data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notice> updateNotice(@PathVariable int noticeNo, @RequestBody Notice notice) {
		
		log.debug(notice);
	
		adminNoticeServiceImpl.updateNotice(notice);
		return new ResponseEntity<>(notice, HttpStatus.OK);
	}

	@GetMapping(value = "/{noticeNo}/deletion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> deleteNotice(@PathVariable int noticeNo) {
		
		log.debug("삭제 소환");
		adminNoticeServiceImpl.deleteNotice(noticeNo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
