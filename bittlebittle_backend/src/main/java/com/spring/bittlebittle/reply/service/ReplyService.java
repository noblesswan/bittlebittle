package com.spring.bittlebittle.reply.service;

import java.util.List;
import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.reply.vo.ReplyNickname;

public interface ReplyService {

	List<ReplyNickname> getReplies(int reviewNo);

	List<ReplyNickname> addReply(Reply reply);

	List<ReplyNickname> editReply(Reply reply);

	List<ReplyNickname> removeReply(int reviewNo, int replyNo);

}
