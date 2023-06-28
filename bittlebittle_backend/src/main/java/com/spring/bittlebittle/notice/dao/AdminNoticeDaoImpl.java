package com.spring.bittlebittle.notice.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.notice.vo.Notice;

@Repository
public class AdminNoticeDaoImpl implements AdminNoticeDao{
	@Autowired
    private SqlSession sqlSession;

    public List<Notice> getNoticeList() {
        return sqlSession.selectList("NoticeMapper.getNoticeList");
    }
    public Notice getNotice(int noticeNo) {
        return sqlSession.selectOne("NoticeMapper.getNotice", noticeNo);
    }
    public void addNotice(Notice notice) {
        sqlSession.insert("NoticeMapper.adminAddNotice", notice);
    }
    public void updateNotice(Notice notice) {
        sqlSession.update("NoticeMapper.adminUpdateNotice", notice);
    }
    public void deleteNotice(int noticeNo) {
        sqlSession.delete("NoticeMapper.adminDeleteNotice", noticeNo);
    }
}
