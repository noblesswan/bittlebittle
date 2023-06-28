package com.spring.bittlebittle.notice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private int noticeNo, userNo;
    private String noticeTitle, noticeContent, createDate, status;
}