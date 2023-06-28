package com.spring.bittlebittle.notice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.notice.vo.Notice;

@Repository
public class NoticeDaoImpl implements NoticeDao{

    
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Notice> getNoticeList() {
        return sqlSession.selectList("NoticeMapper.getNoticeList");
    }

    @Override
    public Notice getNotice(int noticeNo) {
        return sqlSession.selectOne("NoticeMapper.getNotice", noticeNo);
    }
    
    @Override
    public boolean isAuthor(int noticeNo, int userNo) {
        Map<String, Integer> params = new HashMap<>();
        params.put("noticeNo", noticeNo);
        params.put("userNo", userNo);
        Integer count = sqlSession.selectOne("NoticeMapper.isAuthor", params);
        return count != null && count > 0;
    }
}