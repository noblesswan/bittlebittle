package com.spring.bittlebittle.utils.OAuth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSocialOAuthToken {

        private String jwtToken;
        private int user_num;
        private String accessToken;
        private String tokenType;
}
