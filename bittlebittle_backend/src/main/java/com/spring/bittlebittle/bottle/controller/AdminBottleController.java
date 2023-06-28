package com.spring.bittlebittle.bottle.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.spring.bittlebittle.bottle.service.BottleService;
import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleAll;
import com.spring.bittlebittle.bottle.vo.BottleInfo;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.ImageUploadUtil;
import com.spring.bittlebittle.utils.JwtUtil;

@RestController
@RequestMapping(value="/api/admin/bottles", produces="application/json; charset=UTF-8")
public class AdminBottleController {

	@Autowired
	private BottleService bservice;
	@Autowired
	private ReviewService rservice;
	@Autowired
	private TagService tservice;
	@Autowired
	private ImageUploadUtil imageUploadUtil;

	@Autowired
	private Gson gson;

	@Autowired
	private JwtUtil jwtUtil;
	
	Logger log = LogManager.getLogger("case3");
	
	// 조회 (확인완료)
	@GetMapping(value="/{bottleNo}")
	public Map<String, Object> getBottle(@PathVariable int bottleNo) {
		
		Map<String, Object> map = bservice.getBottleByAdmin(bottleNo);
		
		
		return map;
	}
	
//	// 추가창 들어갈때(확인완료)
//	@GetMapping(value="/addition")
//	public Map<String, Object> getTags() {
//		
//		// 태그 선택할 수 있도록 태그 선택지 불러오기
//		List<TagType> tagTypeList = tservice.getAllTagTypes();
//		List<Tag> tagList = tservice.getAllTags();
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("tagTypeList", tagTypeList);
//		map.put("tagList", tagList);
//		
//		return map;
//	}
	
	// 추가 완료할 때(확인완료)
	@PostMapping
	public ResponseEntity<Object> addBottle(@ModelAttribute BottleInfo bottle,
									@RequestParam("imgUrlOrigin") MultipartFile upfile,
									HttpServletRequest request) throws MalformedURLException {

		Map<String, Object> map = new HashMap<>();

		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder().userJwtIdx(refreshTokenIdx).build())) {

			// 전달된 파일이 있을 경우
			// 1. 파일명 수정 => yyyymmddhhmmssxxxxx.확장자
			// 2. 서버로 업로드
			// 3. 원본명, 서버에 업로드된 수정명, 경로를 db 로 insert
			if (!upfile.getOriginalFilename().equals("")) {

				// saveFile 메소드로 위의 코드를 따로 정의함
				// 필요한 인자로는 업로드한 파일과 webapp 경로를 찾기 위한 request, image/food||bottle 을 구분하는 텍스트
				String changeName = imageUploadUtil.saveFile(upfile, request, "bottle");
				bottle.setImgUrl(upfile.getOriginalFilename());
				bottle.setImgCusUrl(changeName);
			}
			// Service 단으로 b 를 넘겨서 insert 요청
			// 넘어온 첨부파일이 있을 경우 b : 제목, 내용, 작성자, 원본파일명, 수정파일명
			// 넘어온 첨부파일이 없을 경우 b : 제목, 내용, 작성자
			List<BottleAll> bottleList = bservice.addBottle(bottle);
//			map.put("bottleList", bottleList);
//			map.put("request", "success");
			// 리스트 or 새로운 bottle
			return ResponseEntity.ok().body(bottleList);
		} else {
			map.put("request", "error");
			return ResponseEntity.ok().body(map);
		}
	}

	// 수정완료 (확인완료)
	@PostMapping(value="/set-data")
	public ResponseEntity<Object> editBottle(@ModelAttribute BottleInfo editBottle,
										  @RequestParam(value="reupfile", required=false) MultipartFile reupfile,
										  HttpServletRequest request) throws MalformedURLException {

		Map<String, Object> map = null;

		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);

		// 토큰이 유효하다면
		if (jwtUtil.validateToken(token, UserJwt.builder().userJwtIdx(refreshTokenIdx).build())) {

			/*
			1. 새로 업로드된 파일이 있냐 없냐. 있으면 2번, 없으면 3번
			2. 있다면, 기존의 파일이 있냐?
				2-1) 있다.
					2-1-1) 있다면, 새 파일과 기존 파일의 이름이 동일하지 않으면 기존 파일 삭제 후 새 파일 저장.
					2-1-2) 동일하면 종료
				2-2) 없다면, 저장
			3. 없다면, 기존 파일이 존재하는가?
				3-1) 있으면 삭제
				3-2) 없으면 텍스트만 수정
			 */

			// 1. 새로 업로드된 파일이 있다.
			if (reupfile != null) {
			// !reupfile.getOriginalFilename().equals("") &&
				// 2. 기존의 이미지 파일이 있다
				if(editBottle.getImgCusUrl() != null && !editBottle.getImgCusUrl().equals("")) {

					// 2-1-1) 만약 들어온 reupfile 과 기존의 db 파일이름이 동일하지 않으면!
					if (!reupfile.getOriginalFilename().equals(editBottle.getImgCusUrl())) {

						// 기존 파일 삭제
						// 기존에 업로드되어 있던 파일의 경로.
						String filePath = request.getServletContext().getRealPath("/resources/static/image/" + "bottle" + File.separator + editBottle.getImgCusUrl());
						File file = new File(filePath);
						if (file.exists() && file.isFile()) {
							file.delete();
						}

						// 새로운 파일을 저장
						String changeName = imageUploadUtil.saveFile(reupfile, request, "bottle");
						editBottle.setImgUrl(reupfile.getOriginalFilename());
						editBottle.setImgCusUrl(changeName);
						map = bservice.editBottle(editBottle);

					} // 2-1-2) 들어온 reupfile 과 기존의 db 파일 이름이 동일하다면 다른 과정이 불필요.

				// 2-2). 기존 파일은 없고 새로 업로드만 됐다.
				} else {
					// 새로운 파일을 저장
					String changeName = imageUploadUtil.saveFile(reupfile, request, "bottle");
					editBottle.setImgUrl(reupfile.getOriginalFilename());
					editBottle.setImgCusUrl(changeName);
					map = bservice.editBottle(editBottle);
				}
			// 3. 새로 업로드된 파일이 없다.
			} else {
				// 3-1). 새로 업로드된 파일이 없고, 기존 파일이 있다면 삭제
				if(editBottle.getImgCusUrl() != null && !editBottle.getImgCusUrl().equals("")) {
					// 기존 파일 삭제
					// 기존에 업로드되어 있던 파일의 경로.
					String filePath = request.getServletContext().getRealPath("/resources/static/image/" + "bottle" + File.separator + editBottle.getImgCusUrl());
					File file = new File(filePath);
					if (file.exists() && file.isFile()) {
						file.delete();
					}
				}
				editBottle.setImgUrl("");
				editBottle.setImgCusUrl("");
				map = bservice.editBottle(editBottle);
			}
			return ResponseEntity.ok().body(map);
		} else {
			// 토큰이 유효하지 않다면,
			map = new HashMap<>();
			map.put("request", "error");
			return ResponseEntity.ok().body(gson.toJson(map));
		}
	}
	
	// 삭제 (확인완료)
	@GetMapping(value="/{bottleNo}/deletion")
	public List<BottleAll> removeBottle(@PathVariable int bottleNo){
		                                                                                                                                                                                                         
		
		List<BottleAll> bottleList = bservice.removeBottle(bottleNo);
		
		// 원래 없애야 하지만 DB 보존을 위해..
		// tservice.removeBottleTag(bottleNo);
		
		return bottleList;
	}
}
