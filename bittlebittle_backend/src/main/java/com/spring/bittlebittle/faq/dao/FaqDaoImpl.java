package com.spring.bittlebittle.faq.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.faq.vo.Faq;

@Repository
public class FaqDaoImpl implements FaqDao{

    
    @Autowired
    private SqlSession sqlSession;

    public List<Faq> getFaqList() {
        return sqlSession.selectList("FaqMapper.getFaqList");
    }

    public Faq getFaq(int faqNo) {
        return sqlSession.selectOne("FaqMapper.getFaq", faqNo);
    }

    public void addFaq(Faq faq) {
        sqlSession.insert("FaqMapper.addFaq", faq);
    }

    public void updateFaq(Faq faq) {
        sqlSession.update("FaqMapper.updateFaq", faq);
    }

    public void deleteFaq(int faqNo) {
        sqlSession.delete("FaqMapper.deleteFaq", faqNo);
    }

    public boolean isAuthor(int faqNo, int userNo) {
        Map<String, Integer> params = new HashMap<>();
        params.put("faqNo", faqNo);
        params.put("userNo", userNo);
        Integer count = sqlSession.selectOne("FaqMapper.isAuthor", params);
        return count != null && count > 0;
    }
}