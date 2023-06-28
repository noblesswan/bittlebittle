package com.spring.bittlebittle.notice.service;

import com.spring.bittlebittle.notice.vo.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getNoticeList();
    Notice getNotice(int noticeNo);
}
