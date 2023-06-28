package com.spring.bittlebittle.tag.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.JwtUtil;



@RestController
@RequestMapping(value="/api/admin/tags", produces="application/json; charset=UTF-8")
public class AdminTagController {

	@Autowired
	private TagService tservice;
	@Autowired
	private JwtUtil jwtUtil;
	
	Logger log = LogManager.getLogger("case3");
	
	// 태그 창 들어갔을 때 (확인완료)
	@GetMapping

	public List<Tag>  getTags() {

		List<Tag> tagList = tservice.getAllTags();
		
		return tagList;
	}
	// 작성 (확인완료)
	@PostMapping
	public List<Tag> addTag(@RequestBody Tag tag, HttpServletRequest request){
								// keyTypeNo, tagName
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {
			
			List<Tag> tagList = tservice.addTag(tag);
			
			return tagList;
			
		} else {
			
			List<Tag> tagList = tservice.getAllTags();
			
			return tagList;
		}
	
	}
	
	// 수정 (확인완료)
	@PostMapping(value="/set-data")
	public List<Tag> editTag(@RequestBody Tag tag, HttpServletRequest request){
										// TagNo, TagType, keyTypeNo
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {
			
			List<Tag> tagList = tservice.editTag(tag);
			
			return tagList;
			
		} else {
			
			List<Tag> tagList = tservice.getAllTags();
			
			return tagList;
		}
		
	}
	
	// 삭제 (확인완료)
	@GetMapping(value="/{tagNo}/deletion")
	public List<Tag> removeTagType(@PathVariable int tagNo, HttpServletRequest request){
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {
			
			List<Tag> tagList = tservice.removeTag(tagNo);
			
			return tagList;
			
		} else {
			
			List<Tag> tagList = tservice.getAllTags();
			
			return tagList;
		}
		
	}
	
}
