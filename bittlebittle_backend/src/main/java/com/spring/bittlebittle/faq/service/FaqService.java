package com.spring.bittlebittle.faq.service;

import java.util.List;

import com.spring.bittlebittle.faq.vo.Faq;

public interface FaqService {
    List<Faq> getFaqList();
    Faq getFaq(int faqNo);
    void addFaq(Faq faq);
    void updateFaq(Faq faq);
    void deleteFaq(int faqNo, int userNo);
}
