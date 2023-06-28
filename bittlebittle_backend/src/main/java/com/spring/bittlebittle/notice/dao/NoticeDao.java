package com.spring.bittlebittle.notice.dao;

import java.util.List;

import com.spring.bittlebittle.notice.vo.Notice;

public interface NoticeDao {

    List<Notice> getNoticeList();
    Notice getNotice(int noticeNo);
    boolean isAuthor(int noticeNo, int userNo);
}