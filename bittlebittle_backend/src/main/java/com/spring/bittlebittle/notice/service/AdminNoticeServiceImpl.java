package com.spring.bittlebittle.notice.service;

import com.spring.bittlebittle.notice.dao.AdminNoticeDaoImpl;
import com.spring.bittlebittle.notice.vo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminNoticeServiceImpl implements AdminNoticeService {

	@Autowired
	private AdminNoticeDaoImpl adminNoticeDaoImpl;

	@Override
	public List<Notice> getNoticeList() {
		return adminNoticeDaoImpl.getNoticeList();
	}

	@Override
	public Notice getNotice(int noticeNo) {
		return adminNoticeDaoImpl.getNotice(noticeNo);
	}

	@Override
	public void addNotice(Notice notice) {
		adminNoticeDaoImpl.addNotice(notice);
	}

	@Override
	public void updateNotice(Notice notice) {
		adminNoticeDaoImpl.updateNotice(notice);
	}

	@Override
	public void deleteNotice(int noticeNo) {
		adminNoticeDaoImpl.deleteNotice(noticeNo);
	}
}
