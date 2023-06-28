package com.spring.bittlebittle.faq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.faq.dao.FaqDaoImpl;
import com.spring.bittlebittle.faq.vo.Faq;

@Service
@Transactional

public class FaqServiceImpl implements FaqService {
    @Autowired
    private FaqDaoImpl dao;

    @Override
    public List<Faq> getFaqList() {
        return dao.getFaqList();
    }

    @Override
    public Faq getFaq(int faqNo) {
        return dao.getFaq(faqNo);
    }

    @Override
    public void addFaq(Faq faq) {
        dao.addFaq(faq);
    }
    
    @Override
    public void updateFaq(Faq faq) {
        int faqNo = faq.getFaqNo();
        int userNo = faq.getFaqNo();
        if (dao.isAuthor(faqNo, userNo)) {
            dao.updateFaq(faq);
        }
    }
    @Override
    public void deleteFaq(int faqNo, int userNo) {
        if (dao.isAuthor(faqNo, userNo)) {
            dao.deleteFaq(faqNo);
        }
    }
}