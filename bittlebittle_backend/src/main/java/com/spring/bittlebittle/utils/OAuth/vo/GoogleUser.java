package com.spring.bittlebittle.utils.OAuth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//구글(서드파티)로 액세스 토큰을 보내 받아올 구글에 등록된 사용자 정보
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoogleUser {
    public String id;
    public String email;
    public Boolean verifiedEmail;
    public String name;
    public String givenName;
    public String familyName;
    public String picture;
    public String locale;
}