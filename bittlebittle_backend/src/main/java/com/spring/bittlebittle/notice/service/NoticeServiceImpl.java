package com.spring.bittlebittle.notice.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.notice.dao.NoticeDaoImpl;
import com.spring.bittlebittle.notice.vo.Notice;


@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDaoImpl noticeDaoImpl;

    @Override
    public List<Notice> getNoticeList() {
        return noticeDaoImpl.getNoticeList();
    }

    @Override
    public Notice getNotice(int noticeNo) {
        return noticeDaoImpl.getNotice(noticeNo);
    }
}