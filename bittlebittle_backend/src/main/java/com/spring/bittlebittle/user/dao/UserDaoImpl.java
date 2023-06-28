package com.spring.bittlebittle.user.dao;

import com.spring.bittlebittle.board.vo.BoardReply;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.tag.vo.UserTagInfo;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class UserDaoImpl implements UserDao {

    private Logger log = LogManager.getLogger("case3");
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<User> selectUsers(int userNo) {
        return sqlSession.selectList("userMapper.selectList", userNo);
    }

    @Override
    public List<User> selectUsersByKeyword(User user) {
        return null;
    }

    @Override
    public User selectUser(User user) {
        return sqlSession.selectOne("userMapper.selectOne", user);
    }


	public User selectLoginUser(User user) {
		return sqlSession.selectOne("userMapper.selectLoginUser", user);
	}


    @Override
    public int insertUser(User user) {
		return sqlSession.insert("userMapper.registerUser", user);
    }

	@Override
	public int updateUser(User user) {
		return sqlSession.update("userMapper.updateUser", user);
	}

	@Override
	public int deleteUser(User user) {
		return sqlSession.update("userMapper.deleteUser", user);
	}

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
	public List<UserTagInfo> getUserTags(UserTagInfo userTagInfo) {
		return sqlSession.selectList("tagMapper.selectTagByUser", userTagInfo);
	}

	@Override
	public List<UserTagInfo> getUserTagTypes(UserTagInfo userTagInfo) {
		return sqlSession.selectList("tagMapper.selectTagTypeByUser", userTagInfo);
	}
	@Override
	public int addUserTags(int userNo, List<Integer> tagNoList) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNo", userNo);
		paramMap.put("tagNoList", tagNoList);
		log.debug(tagNoList.toString());
		return sqlSession.insert("tagMapper.insertUserTags", paramMap);
	}

//	@Override
//	public int updateUserTags(int userNo, List<Integer> tagNoList) throws Exception {
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("userNo", userNo);
//		paramMap.put("tagNoList", tagNoList);
//		return sqlSession.update("tagMapper.updateUserTags", paramMap);
//	}

	@Override
	public int deleteUserTags(int userNo) throws Exception {
		return sqlSession.delete("tagMapper.deleteUserTags", userNo);
	}


	@Override
	public User findByUserId(String userId) {
		return sqlSession.selectOne("userMapper.findByUserId", userId);
	}

	@Override
	public User findByNickname(String nickname) {
		return sqlSession.selectOne("userMapper.findByNickname", nickname);
	}

	public List<Review> getUserReviews(int userNo) {
		return sqlSession.selectList("userMapper.selectReview",userNo);
	}

	public List<BoardReply> getUserComments(int userNo) {
		return sqlSession.selectList("userMapper.selectReply", userNo);
	}

	@Override
	public void updateStatusToWithdraw(int userNo) {
		sqlSession.update("userMapper.updateWithdraw", userNo);
		
	}

//	@Override
//	public List<User> findAllUsers(int userNo) {
//		return sqlSession.selectList("userMapper.findAllUsers", userNo);
//	}

	@Override
	public List<User> searchUsers(String searchCriteria, String searchKeyword) {
		Map<String, Object> params = new HashMap<>();
        params.put("searchCriteria", searchCriteria);
        params.put("searchKeyword", searchKeyword);

        return sqlSession.selectList("userMapper.searchUsers", params);
	}

	@Override // 회원관리창에서 회원정보 선택삭제를 하는 부분
	public int updateStatusToN(List<Long> userNos) {
		
		return sqlSession.update("userMapper.updateStatusToN", userNos);
		
	}

	@Override
	public int updateUsermodal(User user) {
		return sqlSession.update("userMapper.updateUsermodal", user);
	}


}
