package com.spring.bittlebittle.user.dao;

import com.spring.bittlebittle.board.vo.BoardReply;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.tag.vo.UserTagInfo;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

import java.util.List;

public interface UserDao  {

    List<User> selectUsers(int userNo);

    List<User> selectUsersByKeyword(User user);

    User selectUser(User user);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(User user);

    UserJwt selectUserJwt(UserJwt userJwt);

    int insertJwtWithIdx(UserJwt userJwt);

    int updateUserJwt(UserJwt userJwt);

    UserJwt selectUserJwtBySubject(UserJwt userJwt);

    int deleteUserJwt(UserJwt userJwt);

	User selectLoginUser(User user);

    List<UserTagInfo> getUserTags(UserTagInfo userTagInfo);

    List<UserTagInfo> getUserTagTypes(UserTagInfo userTagInfo);

	int addUserTags(int userNo, List<Integer> tagNoList) throws Exception;

//    int updateUserTags(int userNo, List<Integer> tagNoList) throws Exception;

	int deleteUserTags(int userNo) throws Exception;

//    void deleteUserTags(int userNo) throws Exception;

//    String getTagName(int tagNo) throws Exception;

	User findByUserId(String userId);

    User findByNickname(String nickname);

	public List<Review> getUserReviews(int userNo);
	public List<BoardReply> getUserComments(int userNo);
	
	void updateStatusToWithdraw(int userNo);
	
//	List<User> findAllUsers(int userNo);
	
	List<User> searchUsers(String searchCriteria, String searchKeyword);
	
	int updateStatusToN(List<Long> userNos);
	
	int updateUsermodal(User user);
	
	
	
	

}
