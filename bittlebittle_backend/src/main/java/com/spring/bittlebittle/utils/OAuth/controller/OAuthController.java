package com.spring.bittlebittle.utils.OAuth.controller;

import com.spring.bittlebittle.utils.OAuth.service.OAuthService;
import com.spring.bittlebittle.utils.OAuth.vo.GetSocialOAuthToken;
import com.spring.bittlebittle.utils.OAuth.vo.SocialLoginType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class OAuthController {

    private Logger log = LogManager.getLogger("case3");

    @Autowired
    private OAuthService oService;

    @GetMapping(value = "accounts/auth/{socialLoginType}")
    public void socialLoginRedirect(@PathVariable(value = "socialLoginType") String socialLoginPath) throws IOException {
        SocialLoginType socialLoginType = SocialLoginType.valueOf(socialLoginPath.toUpperCase());
        oService.request(socialLoginType);
        log.debug("1차 접속");

    }

//    @GetMapping(value = "api/accounts/auth/{socialLoginType}/callback")
    @GetMapping(value = "accounts/auth/{socialLoginType}/callback")
    public ResponseEntity<GetSocialOAuthToken> callback (
            @PathVariable(name = "socialLoginType") String socialLoginPath,
            @RequestParam(name = "code") String code)throws IOException {

        log.debug(">> 소셜 로그인 API 서버로부터 받은 code :"+ code);
        SocialLoginType socialLoginType = SocialLoginType.valueOf(socialLoginPath.toUpperCase());
        GetSocialOAuthToken getSocialOAuthToken = oService.oAuthLogin(socialLoginType,code);
        return ResponseEntity.ok().body(getSocialOAuthToken);
    }

    @GetMapping(value = "accounts/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/");
    }


}
