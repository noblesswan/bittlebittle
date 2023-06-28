package com.spring.bittlebittle.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.reply.dao.ReplyDao;
import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.reply.vo.ReplyNickname;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDao dao;
	
	@Override
	public List<ReplyNickname> getReplies(int reviewNo) {
		
		return dao.selectList(reviewNo);
	}
	
	@Override
	@Transactional
	public List<ReplyNickname> addReply(Reply reply) {
		
		dao.insertOne(reply);
		
		List<ReplyNickname> list=dao.selectList(reply.getReviewNo());
		
		return list;
	}
	
	@Override
	@Transactional
	public List<ReplyNickname> editReply(Reply reply) {
		
		dao.updateOne(reply);
		
		List<ReplyNickname> list=dao.selectList(reply.getReviewNo());
		
		return list;
	}
	
	@Override
	@Transactional
	public List<ReplyNickname> removeReply(int reviewNo, int replyNo) {
		
		dao.deleteOne(replyNo);
		
		List<ReplyNickname> list = dao.selectList(reviewNo);
		
		return list;
	}
}
