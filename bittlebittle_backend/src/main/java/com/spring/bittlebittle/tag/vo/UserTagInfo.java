package com.spring.bittlebittle.tag.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTagInfo {

    private int tagNo, tagTypeNo, userNo;
    private String tagName, tagTypeName, userName;

}
