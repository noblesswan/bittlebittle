package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.board.vo.BoardReply;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.tag.vo.UserTagInfo;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

import java.util.List;
import java.util.Map;


public interface UserService {

    // selectList
    List<User> getUsers(int userNo);

    // selectOne
    User getUser(User user);

    User loginUser(User user);
    // insert
    User registerUser(User user);

    // update
    User editUser(User user);

    // delete
    int removeUser(User user);

    // Boolean registerJwtWithIdx(UserAuthentication userAuthentication);

    UserJwt getUserJwt(UserJwt userJwt);

    UserJwt createUserJwt(UserJwt userJwt);

    UserJwt editUserJwt(UserJwt build);

    UserJwt getUserJwtBySubject(UserJwt build);

    int removeUserJwt(UserJwt userJwt);

    Map<String, Object> getUserTags(UserTagInfo userTagInfo);

	int addUserTags(int userNo, List<Integer> tagNoList) throws Exception;

//    void editUserTags(int userNo, List<Integer> tagNoList) throws Exception;

	int deleteUserTags(int userNo, List<Integer> tagNoList) throws Exception;

	boolean isUsernameDuplicate(String userId);

    boolean isNicknameuplicate(String nickname);

	boolean sendEmailAuth(String email);

	List<Review> getUserReviews(int userNo);

	List<BoardReply> getUserComments(int userNo);
	
	void withdrawUser(int userNo);
	
//	public List<User> findAllUsers(int userNo);
	
	public List<User> searchUsers(String searchCriteria, String searchKeyword);
	
	public int updateStatusToN(List<Long> userNos);
	
	public int updateUsermodal(User user);
	
	
	

}
