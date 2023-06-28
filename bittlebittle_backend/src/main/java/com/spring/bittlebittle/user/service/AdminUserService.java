package com.spring.bittlebittle.user.service;


import java.util.List;

import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

public interface AdminUserService {

	// update
	int updateUser(User user);

	// delete
	int deleteUser(User user);

	// 전체조회

	List<User> selectUsers();

	// 선택조회

	User selectListbyNo(User user);
	
	//관리자등록
	
	 int adminUser(User user);
	 
	// Boolean registerJwtWithIdx(UserAuthentication userAuthentication);

	    UserJwt getUserJwt(UserJwt userJwt);

	    UserJwt createUserJwt(UserJwt userJwt);

	    UserJwt editUserJwt(UserJwt build);

	    UserJwt getUserJwtBySubject(UserJwt build);

	    int removeUserJwt(UserJwt userJwt);
	    
	    Boolean loginUser(User user);
	    
//	    // selectOne
//	    User getUser(User user);

}
