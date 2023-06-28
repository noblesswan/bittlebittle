package com.spring.bittlebittle.user.service;


import java.util.List;

import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

public interface AdminUserService {

	// update
	int updateUser(User user);

	// delete
	int deleteUser(User user);

	// ��ü��ȸ

	List<User> selectUsers();

	// ������ȸ

	User selectListbyNo(User user);
	
	//�����ڵ��
	
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
