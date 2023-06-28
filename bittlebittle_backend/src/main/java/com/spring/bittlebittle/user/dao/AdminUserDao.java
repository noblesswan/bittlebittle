package com.spring.bittlebittle.user.dao;


import java.util.List;

import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

public interface AdminUserDao {

	int updateUser(User user);

    int deleteUser(User user);
	
	//전체조회
    
    List<User> selectUsers();
    
    //선택조회
    
    User selectListbyNo(User user);
    
    //관리자권한 등록
    
    int adminRegister(User user);
    
    //이 아래는 JWT 영역
    
    UserJwt selectUserJwt(UserJwt userJwt);

    int insertJwtWithIdx(UserJwt userJwt);

    int updateUserJwt(UserJwt userJwt);

    UserJwt selectUserJwtBySubject(UserJwt userJwt);
    
    int deleteUserJwt(UserJwt userJwt);
    
    User selectLoginUser(User user);

}
