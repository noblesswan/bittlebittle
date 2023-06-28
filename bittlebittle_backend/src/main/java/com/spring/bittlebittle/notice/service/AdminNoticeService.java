package com.spring.bittlebittle.notice.service;

import com.spring.bittlebittle.notice.vo.Notice;

import java.util.List;

public interface AdminNoticeService {
	List<Notice> getNoticeList();

	Notice getNotice(int noticeNo);

	void addNotice(Notice notice);

	void updateNotice(Notice notice);

	void deleteNotice(int noticeNo);
}
