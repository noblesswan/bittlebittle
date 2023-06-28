package com.spring.bittlebittle.user.controller;


import com.google.gson.Gson;
import com.spring.bittlebittle.board.vo.BoardReply;
import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.tag.vo.UserTagInfo;
import com.spring.bittlebittle.user.service.UserService;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.JwtUtil;
import com.spring.bittlebittle.utils.OAuth.service.OAuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // @Controller + @ResponseBody
@RequestMapping(value = "api/users", produces = "application/json; charset=utf-8")
public class UserController {

    private Logger log = LogManager.getLogger("case3");
    @Autowired
    private UserService service;
    @Autowired
    private OAuthService oService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private Gson gson;

    @PostMapping
    public ResponseEntity registerUser(@RequestBody User user) {
        log.debug(user.toString());
        Map<String, Object> map = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        User registerUser = service.registerUser(user);
        log.debug(registerUser.toString());
        if (  registerUser != null ) {
            String accessToken = jwtUtil.createAccessJwt(user.getUserId());
            String userJwtIdx = jwtUtil.createRefreshJwt(user.getUserId());
            jwtUtil.setHeaderAccessToken(headers, accessToken);
            jwtUtil.setHeaderRefreshToken(headers, userJwtIdx);
            map.put("request", true);
            map.put("userNo", registerUser.getUserNo());
        } else {
            map.put("request", false);
        }
        return ResponseEntity.ok().headers(headers).body(gson.toJson(map));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) Integer userNo) {
        List<User> users = service.getUsers(userNo);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

	// 정보삭제(탈퇴)
    @GetMapping(value = "/{userNo}/deletion")
	public ResponseEntity<String> deleteUser(@PathVariable int userNo) {
		int result = service.removeUser(User.builder().userNo(userNo).build());
		if (result > 0) {
			return ResponseEntity.ok("User has been deleted.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user.");
		}
	}

	// 회원정보수정
	@PostMapping(value = "/set-data", produces = "application/json; charset=utf-8")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		User updateUser = service.editUser(user);
		if (updateUser != null ) {
			return ResponseEntity.ok("User information has been updated.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user information.");
		}
	}

	
//////////////////////
//아래는 tag 관련
	@GetMapping("/{userNo}/tags")
    public ResponseEntity<Object> getUserTags(@PathVariable int userNo,
                                              HttpServletRequest request) {
        log.debug(userNo);
        Map<String, Object> map = null;
        String token = jwtUtil.resolveAccessToken(request);
        if(jwtUtil.validateToken(token, UserJwt.builder()
                .userJwtIdx(jwtUtil.resolveRefreshToken(request))
                .build())){
            map = service.getUserTags(UserTagInfo.builder().userNo(userNo).build());
            map.put("request", true);
            return ResponseEntity.ok().body(map);
        } else {
            map = new HashMap<>();
            map.put("token", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }

	@PostMapping(value = "/{userNo}/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addUserTags(@PathVariable int userNo,
                                            @RequestBody Map<String, Object> requestMap,
                                            HttpServletRequest request) throws Exception {
        log.debug(userNo);
        List<Object> inputList = (List<Object>) requestMap.get("tagNoList");
        List<Integer> tagNoList = new ArrayList<>();
        for (Object obj : inputList) {
            // Double 값을 Integer로 변환하고 리스트에 추가
            tagNoList.add(((Double) obj).intValue());
        }
        log.debug(tagNoList.toString());
        Map<String, Object> map = new HashMap<>();
        String token = jwtUtil.resolveAccessToken(request);
        if(jwtUtil.validateToken(token, UserJwt.builder()
                .userJwtIdx(jwtUtil.resolveRefreshToken(request))
                .build())){
            service.addUserTags(userNo, tagNoList);
            map.put("request", true);
            return ResponseEntity.ok().body(map);
        } else {
            map.put("token", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }

//    @PostMapping(value = "/{userNo}/tags/set-data", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> editUserTags(@PathVariable int userNo,
//                                              @RequestBody List<Integer> tagNoList,
//                                              HttpServletRequest request) throws Exception {
//
//        Map<String, Object> map = new HashMap<>();
//        String token = jwtUtil.resolveAccessToken(request);
//        if(jwtUtil.validateToken(token, UserJwt.builder().userJwtIdx(jwtUtil.resolveRefreshToken(request)).build())){
//            service.editUserTags(userNo, tagNoList);
//            map.put("request", true);
//            return ResponseEntity.ok().body(map);
//        } else {
//            map.put("token", false);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
//        }
//    }

    @PostMapping(value = "{userNo}/tags/deletion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUserTags(@PathVariable int userNo,
                                               @RequestBody List<Integer> tagNoList,
                                               HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        log.debug(userNo);
        tagNoList.stream().forEach(tagNo -> log.debug(tagNo));
        String token = jwtUtil.resolveAccessToken(request);
        if(jwtUtil.validateToken(token, UserJwt.builder().userJwtIdx(jwtUtil.resolveRefreshToken(request)).build())){

            service.deleteUserTags(userNo, tagNoList);
            map.put("request", true);
            return ResponseEntity.ok().body(map);
        } else {
            map = new HashMap<>();
            map.put("token", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }

    //이메일인증, 아이디 중복확인
    
    @PostMapping(value="/check-duplicate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> checkDuplicate(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        boolean isDuplicate = service.isUsernameDuplicate(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value="/check-duplicate-nickname", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> checkDuplicateNickname(@RequestBody Map<String, String> request) {
        String nickname = request.get("nickname");
        boolean isDuplicate = service.isNicknameuplicate(nickname);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return ResponseEntity.ok().body(response);
    }
	
//    @PostMapping(value="/send-email-auth", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> sendEmailAuth(@RequestBody Map<String, String> request) {
//        String email = request.get("email");
//        boolean success = service.sendEmailAuth(email);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("success", success);
//        return ResponseEntity.ok(response);
//    }
	

//    @GetMapping(value = "/accounts/auth/{socialLoginType}")
//    public void socialLoginRedirect(@PathVariable(value = "socialLoginType") String socialLoginPath) throws IOException {
//        SocialLoginType socialLoginType = SocialLoginType.valueOf(socialLoginPath.toUpperCase());
//        oService.request(socialLoginType);
//    }

    
    @GetMapping(value = "/{userNo}")
    public ResponseEntity<Object> getUser(@PathVariable int userNo, HttpServletRequest request){
        log.debug("유저 조회");
        // access token 디코딩
        String token = jwtUtil.resolveAccessToken(request);
        // access token 과 refreshtokenIdx 를 가지고 조건 검사. 리턴 타입은 boolean
        if(jwtUtil.validateToken(token, UserJwt.builder()
                                        .userJwtIdx(jwtUtil.resolveRefreshToken(request))
                                        .build())){
            // 토큰이 유효하다면 유저 정보 조회
            User user = service.getUser(User.builder().userNo(userNo).build());
            log.debug(user.toString());
            return ResponseEntity.ok().body(user);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("token", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }

    /*
    if(jwtUtil.validateToken(token, UserJwt.builder()
            .userJwtIdx(jwtUtil.resolveRefreshToken(request))
            .build())){
    } else {
        Map<String, Object> map = new HashMap<>();
        map.put("token", false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }
    */
    @PostMapping(value = "/login")
    public ResponseEntity<Object> loginUserToken(@RequestBody User user) {

        User loginUser = service.loginUser(user);
        Map<String, Object> map = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        if(loginUser != null) {
            String accessToken = jwtUtil.createAccessJwt(user.getUserId());
            String userJwtIdx = jwtUtil.createRefreshJwt(user.getUserId());
            jwtUtil.setHeaderAccessToken(headers, accessToken);
            jwtUtil.setHeaderRefreshToken(headers, userJwtIdx);
            map.put("success", true);
            map.put("userNo", loginUser.getUserNo());
            map.put("adminYn", loginUser.getAdminYn());
        }
        else {
            map.put("success", false);
        }
        String json = gson.toJson(map);
        return ResponseEntity.ok()
                .headers(headers)
                .body(json);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Object> logoutUser(HttpServletRequest request) {
        log.debug("로그 아웃");

        // access token header 에서 추출
        String token = jwtUtil.resolveAccessToken(request);
        String subject = jwtUtil.getSubject(token);
        String userJwtIdx = jwtUtil.resolveRefreshToken(request);

        // access token 이 만료되었다면?
        // subject 를 가져올 수 없게 된다.
        // 그러면 그냥 idx 를 기반으로 조회해서 삭제해준다.
        UserJwt userJwt = UserJwt.builder()
                .userJwtIdx(userJwtIdx)
                .subject(subject)
                .build();
        Map<String, Object> map = new HashMap<>();
        if(jwtUtil.validateToken(token, userJwt)) {
            if(jwtUtil.removeRefreshJwt(token, userJwt)) {
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        } else {
            map.put("validate", false);
        }
        return ResponseEntity.ok().body(map);
    }
    
    @GetMapping("/{userNo}/reviews")
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable("userNo") int userNo) {
        log.debug("리뷰 조회 실행");
        List<Review> reviews = service.getUserReviews(userNo);
        log.debug(reviews);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{userNo}/comments")
    public ResponseEntity<Object> getUserComments(@PathVariable("userNo") int userNo) {
        log.debug("댓글 조회 실행");
        List<BoardReply> comments = service.getUserComments(userNo);
        log.debug(comments);
        return ResponseEntity.ok(comments);
    }
    
 // 회원 탈퇴 API 추가
    @PutMapping("/withdraw/{userNo}")
    public ResponseEntity<Void> withdrawUser(@PathVariable("userNo") int userNo) {
    	service.withdrawUser(userNo);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    
//    @GetMapping
//    public ResponseEntity<List<User>> findAllUsers(int userNo) {
//        List<User> users = service.findAllUsers(userNo);
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
    
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam("searchCriteria") String searchCriteria,
            @RequestParam("searchKeyword") String searchKeyword) {
        List<User> users = service.searchUsers(searchCriteria, searchKeyword);
        return ResponseEntity.ok(users);
    }
    
    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStatusToN(@RequestBody Map<String, List<Long>> payload) {
        List<Long> userNos = payload.get("userNos");
        int result = service.updateStatusToN(userNos);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping(value = "/{userNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable Long userNo, @RequestBody User user) {
        int result = service.updateUsermodal(user);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    
    
    
    
    /*

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> createUser(@RequestBody User user) {
    Map<String, Object> response = new HashMap<>();
    if (userService.checkUserExists(user.getUserName())) {
    response.put("status", "error");
    response.put("message", "User already exists");
    } else {
    userService.insertUser(user);
    response.put("status", "success");
    response.put("message", "User created successfully");
    }
    return response;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getAllUsers(User user) {
    List<User> users = userService.getAllUsers(user);
    return users;
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getUserById(@PathVariable String userId) {
    Map<String, Object> response = new HashMap<>();
    User user = userService.getUserById(userId);
    if (user == null) {
    response.put("status", "error");
    response.put("message", "User not found");
    } else {
    response.put("status", "success");
    response.put("user", user);
    }
    return response;
    }

    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> updateUser(@PathVariable String userId, @RequestBody User user) {
    Map<String, Object> response = new HashMap<>();
    User existingUser = userService.getUserById(userId);
    if (existingUser == null) {
    response.put("status", "error");
    response.put("message", "User not found");
    } else {
    user.setUserId(userId);
    userService.updateUser(user);
    response.put("status", "success");
    response.put("message", "User updated successfully");
    }
    return response;
    }

    @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> deleteUser(@PathVariable String userId) {
    Map<String, Object> response = new HashMap<>();
    User existingUser = userService.getUserById(userId);
    if (existingUser == null) {
    response.put("status", "error");
    response.put("message", "User not found");
    } else {
    userService.deleteUser(userId);
    response.put("status", "success");
    response.put("message", "User deleted successfully");
    }
    return response;
    }

     */
}
