package com.spring.bittlebittle.faq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.faq.service.FaqService;
import com.spring.bittlebittle.faq.vo.Faq;

@RestController
@RequestMapping("/api/faqs")
public class FaqController {

	@Autowired
	private FaqService faqService;
	
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Faq> faqList() {
		return faqService.getFaqList();
	}

	@GetMapping(value = "/{faqNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Faq> faqDetail(@PathVariable int faqNo) {
		Faq faq = faqService.getFaq(faqNo);
		if (faq != null) {
			return new ResponseEntity<>(faq, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Faq> addNotice(@RequestBody Faq faq) {
		faqService.addFaq(faq);
		return new ResponseEntity<>(faq, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{faqNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Faq> updateNotice(@PathVariable int faqNo, @RequestBody Faq faq) {
		faq.setFaqNo(faqNo);
		faqService.updateFaq(faq);
		return new ResponseEntity<>(faq, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{faqNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> deleteFaq(@PathVariable int faqNo, @RequestParam int userNo) {
		faqService.deleteFaq(faqNo, userNo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}