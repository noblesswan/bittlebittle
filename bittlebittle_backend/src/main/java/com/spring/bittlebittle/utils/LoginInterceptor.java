//package com.spring.bittlebittle.utils;
//
//import com.spring.bittlebittle.user.vo.UserJwt;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    private Logger log = LogManager.getLogger("case3");
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//            // 매개변수
//            // - request : 사용자의 요청, 즉, 사용자가 보낸 전달값
//            // - response : 요청에 대한 응답, 즉, 사용자에게 첨부할 내용물
//            // - handler : 이 요청은 누가 처리하기로 했는가?
//
//            // 로그인이 된 상태인지 검사하는 코드 추가 => session 에 loginUser 가 있는지 검사
//            String token = request.getHeader("Authorization").toString().replaceAll("\\[", "").replaceAll("\\]", "").substring(7);
//            String refreshToken = request.getHeader("RefreshTokenIdx").toString().replaceAll("\\[", "").replaceAll("\\]", "");
//
//            if(jwtUtil.validateToken(token, UserJwt.builder()
//                    .userJwtIdx(refreshToken)
//                    .build())) { // 로그인 된 상태
//                log.debug("success");
//                return true;
//            }
//            else { // 로그인이 되지 않은 상태
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "권한이 없는 접근");
//                log.debug("fail");
//                return false;
//            }
//    }
//}
