package com.spring.bittlebittle.faq.dao;

import java.util.List;

import com.spring.bittlebittle.faq.vo.Faq;

public interface FaqDao {

    List<Faq> getFaqList();
    Faq getFaq(int faqNo);
    void addFaq(Faq faq);
    void updateFaq(Faq faq);
    void deleteFaq(int faqNo);
    boolean isAuthor(int faqNo, int userNo);

}
