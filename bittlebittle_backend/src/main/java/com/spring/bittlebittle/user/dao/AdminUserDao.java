package com.spring.bittlebittle.user.dao;


import java.util.List;

import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

public interface AdminUserDao {

	int updateUser(User user);

    int deleteUser(User user);
	
	//��ü��ȸ
    
    List<User> selectUsers();
    
    //������ȸ
    
    User selectListbyNo(User user);
    
    //�����ڱ��� ���
    
    int adminRegister(User user);
    
    //�� �Ʒ��� JWT ����
    
    UserJwt selectUserJwt(UserJwt userJwt);

    int insertJwtWithIdx(UserJwt userJwt);

    int updateUserJwt(UserJwt userJwt);

    UserJwt selectUserJwtBySubject(UserJwt userJwt);
    
    int deleteUserJwt(UserJwt userJwt);
    
    User selectLoginUser(User user);

}
