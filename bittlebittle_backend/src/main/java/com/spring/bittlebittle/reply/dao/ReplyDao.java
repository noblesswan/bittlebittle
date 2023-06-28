package com.spring.bittlebittle.reply.dao;

import java.util.List;

import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.reply.vo.ReplyNickname;

public interface ReplyDao {

	List<ReplyNickname> selectList(int reviewNo);

	int insertOne(Reply reply);

	int updateOne(Reply reply);

	int deleteOne(int replyNo);

	void deleteByReview(int reviewNo);

}
