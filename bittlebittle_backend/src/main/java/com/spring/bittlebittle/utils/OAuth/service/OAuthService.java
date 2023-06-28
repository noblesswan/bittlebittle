package com.spring.bittlebittle.utils.OAuth.service;


import com.spring.bittlebittle.utils.OAuth.vo.GetSocialOAuthToken;
import com.spring.bittlebittle.utils.OAuth.vo.GoogleOAuthToken;
import com.spring.bittlebittle.utils.OAuth.dao.GoogleOauth;
import com.spring.bittlebittle.utils.OAuth.vo.GoogleUser;
import com.spring.bittlebittle.utils.OAuth.vo.SocialLoginType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final GoogleOauth googleOauth;
    private final HttpServletResponse response;

    public void request(SocialLoginType socialLoginType) throws IOException {
        String redirectUrl = null;
        switch (socialLoginType) {
            case KAKAO: {} break;
            case NAVER: {} break;
            case GOOGLE: { redirectUrl = googleOauth.getOauthRedirectURL(); } break;
            default: {
                throw new IllegalArgumentException("올바르지 않은 소셜 로그인 발식입니다.");
            }
        }
        response.sendRedirect(redirectUrl);
    }

    public GetSocialOAuthToken oAuthLogin(SocialLoginType socialLoginType, String code) throws IOException {
        switch (socialLoginType) {
            case GOOGLE: {
                //구글로 일회성 코드를 보내 액세스 토큰이 담긴 응답객체를 받아옴
                ResponseEntity<String> accessTokenResponse = googleOauth.requestAccessToken(code);
                //응답 객체가 JSON형식으로 되어 있으므로, 이를 deserialization해서 자바 객체에 담을 것이다.
                GoogleOAuthToken oAuthToken = googleOauth.getAccessToken(accessTokenResponse);

                //액세스 토큰을 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체를 받아온다.
                ResponseEntity<String> userInfoResponse = googleOauth.requestUserInfo(oAuthToken);
                //다시 JSON 형식의 응답 객체를 자바 객체로 역직렬화한다.
                GoogleUser googleUser = googleOauth.getUserInfo(userInfoResponse);

                String user_id = googleUser.getEmail();

                return null;

            }
            default: {
                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
            }

        }
    }
}
