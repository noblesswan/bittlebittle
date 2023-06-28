package com.spring.bittlebittle.tag.controller;

import com.spring.bittlebittle.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value="/api/tags", produces="application/json; charset=UTF-8")
public class TagController {

    @Autowired
    private TagService tservice;

    @GetMapping
    public Map<String, Object> getTags() {

        Map<String, Object> map = tservice.getTags();

        return map;
    }
}
