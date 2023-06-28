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

import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.tag.vo.TagType;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.JwtUtil;

@RestController
@RequestMapping(value="/api/admin/tagtypes", produces="application/json; charset=UTF-8")
public class AdminTagTypeController {

	@Autowired
	private TagService tservice;
	@Autowired
	private JwtUtil jwtUtil;
	
	Logger log = LogManager.getLogger("case3");
	
	// 조회 (확인완료)
	@GetMapping
	public List<TagType> getTagTypes(){
		
		List<TagType> tagTypeList = tservice.getAllTagTypes();
		
		return tagTypeList;
	}
	
	// 작성 (확인완료)
	@PostMapping
	public List<TagType> addTagType(@RequestBody TagType tagType, HttpServletRequest request){
												// tagTypeName으로 받으면 재료 이렇게만 써야됨	
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {
			
			List<TagType> tagTypeList = tservice.addTagType(tagType.getTagTypeName());
			
			return tagTypeList;
			
		} else {
			
			List<TagType> tagTypeList = tservice.getAllTagTypes();
			
			return tagTypeList;
		}
		
	}
	
	// 수정 (확인완료)
	@PostMapping(value="/set-data")
	public List<TagType> editTagType(@RequestBody TagType tagType, HttpServletRequest request){
										//tagTypeNo, tagTypeName
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {
			
			List<TagType> tagTypeList = tservice.editTagType(tagType);
			
			return tagTypeList;
			
		} else {
			
			List<TagType> tagTypeList = tservice.getAllTagTypes();
			
			return tagTypeList;
		}
		
	}
	
	// 삭제 (확인완료)
	@GetMapping(value="/{tagTypeNo}/deletion")
	public List<TagType> removeTagType(@PathVariable int tagTypeNo, HttpServletRequest request){
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {
			
			List<TagType> tagTypeList = tservice.removeTagType(tagTypeNo);
			
			return tagTypeList;
			
		} else {
			
			List<TagType> tagTypeList = tservice.getAllTagTypes();
			
			return tagTypeList;
		}
		
	}
	
}
