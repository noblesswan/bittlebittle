package com.spring.bittlebittle.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

@Repository
public class AdminUserDaoImpl implements AdminUserDao{

	 private Logger log = LogManager.getLogger("case3");
	 
	    @Autowired
	    private SqlSession sqlSession;
	    
	    
	    
		@Override
		public int updateUser(User user) {
			return sqlSession.update("userMapper.updateUser", user);
		}
		@Override
		public int deleteUser(User user) {
			return sqlSession.update("userMapper.deleteUser", user);
		}
		@Override //전체조회
		public List<User> selectUsers() {
			 return sqlSession.selectList("userMapper.selectList");
		}
		@Override //선택조회
		public User selectListbyNo(User user) {
			 return sqlSession.selectOne("userMapper.selectListbyNo", user);
		}
		
		@Override //관리자 권한등록
		public int adminRegister(User user) {
			return sqlSession.insert("userMapper.adminRegister", user);
		}
		
		// 이 아래는 JWT 영역
		
		 @Override
		    public UserJwt selectUserJwt(UserJwt userJwt) {
		        return sqlSession.selectOne("userMapper.selectUserJwt", userJwt);
		    }

		    @Override
		    public UserJwt selectUserJwtBySubject(UserJwt userJwt) {
		        return sqlSession.selectOne("userMapper.selectUserJwtBySubject", userJwt);
		    }

		    public int insertJwtWithIdx(UserJwt userJwt) {
		        return sqlSession.insert("userMapper.insertJwtWithIdx", userJwt);
		    }

		    @Override
		    public int updateUserJwt(UserJwt userJwt) {
		        return sqlSession.update("userMapper.updateUserJwtWithIdx", userJwt);
		    }

		    @Override
		    public int deleteUserJwt(UserJwt userJwt) {
		        return sqlSession.delete("userMapper.deleteUserJwtWithUserJwtIdx", userJwt);
		    }
			@Override
			public User selectLoginUser(User user) {
				// TODO Auto-generated method stub
				return null;
			}
		
		
		
	
}
