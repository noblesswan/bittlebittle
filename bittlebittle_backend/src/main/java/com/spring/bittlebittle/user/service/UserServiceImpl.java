package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.board.vo.BoardReply;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.tag.vo.UserTagInfo;
import com.spring.bittlebittle.user.dao.UserDao;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    private Logger log = LogManager.getLogger("case3");
    @Autowired
    private UserDao dao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${secretKey}")
    private String key;

    @Override
    public List<User> getUsers(int userNo) {
        return dao.selectUsers(userNo);
    }

    @Override
    public User getUser(User user) {
        return dao.selectUser(user);
    }


    @Override
    public User loginUser(User user) {
        // login 시 사용하는 id 만 가지고 일단 db 를 불러온 뒤
        User loginUser = dao.selectLoginUser(user);


        // 만약 유저 아이디가 일치 하지 않으면 db 에 조회가 안될 것이고,
        if (loginUser == null) {
            log.debug("해당 아이디의 유저가 존재하지 않습니다.");
            return null;
        }

        // 만약 비밀번호가 일치하지 않는다면
        if (!passwordEncoder.matches(user.getUserPwd(), loginUser.getUserPwd())) {
            log.debug("유저가 로그인 창에 입력한 비밀번호를 인코딩한 값 : " + passwordEncoder.encode(user.getUserPwd()));
            log.debug("유저가 로그인 창에 입력한 비밀번호 원본 값 : " + user.getUserPwd());
            log.debug("실제 db에 암호화되서 저장된 비밀번호 값 : " + loginUser.getUserPwd());
            log.debug("비밀번호가 일치하지 않습니다.");
            return null;
        }
        log.debug("로그인에 성공했습니다.");
        return loginUser;
    }


    @Override
    @Transactional
    public User registerUser(User user) {
        user.setUserPwd(passwordEncoder.encode(user.getUserPwd()));
        dao.insertUser(user);
        return dao.selectLoginUser(user);
    }

    @Override
    @Transactional
    public User editUser(User user) {
        user.setUserPwd(passwordEncoder.encode(user.getUserPwd()));
        dao.updateUser(user);
        return dao.selectUser(user);
    }

    @Override
    public int removeUser(User user) {
        return dao.deleteUser(user);
    }


    @Override
    @Transactional
    public UserJwt createUserJwt(UserJwt userJwt) {
        userJwt.setUserJwtIdx(passwordEncoder.encode(userJwt.getSubject() + key));
        dao.insertJwtWithIdx(userJwt);
        return dao.selectUserJwt(userJwt);
    }

    @Override
    public UserJwt getUserJwt(UserJwt userJwt) {
        return dao.selectUserJwt(userJwt);
    }

    @Override
    public UserJwt getUserJwtBySubject(UserJwt userJwt) {
        return dao.selectUserJwtBySubject(userJwt);
    }

    @Override
    @Transactional
    public UserJwt editUserJwt(UserJwt userJwt) {
        dao.updateUserJwt(userJwt);
        return dao.selectUserJwt(userJwt);
    }

    @Override
    public int removeUserJwt(UserJwt userJwt) {
        return dao.deleteUserJwt(userJwt);
    }

//
//	@Override
//    public boolean checkUserExists(String username) {
//        User user = dao.getUserByUsername(username);
//        return user != null;
//    }
//
//    @Override
//    public User getUserById(String userId) {
//        return dao.getUserById(userId);
//    }
//
//    @Override
//    public User getUserByUsername(String username) {
//        return dao.getUserByUsername(username);
//    }
//
//    @Override
//    public List<User> getAllUsers(User user) {
//        return dao.getAllUsers(user);
//    }

//	@Override
//	public User selectUser(User user) {
//		return dao.selectUser(user);
//	}
//
    @Override
    public Map<String, Object> getUserTags(UserTagInfo userTagInfo) {
        Map<String,Object> map = new HashMap<>();
        map.put("tagList", dao.getUserTags(userTagInfo));
        map.put("tagTypeList", dao.getUserTagTypes(userTagInfo));
        return map;
    }

	@Override
    @Transactional
	public int addUserTags(int userNo, List<Integer> tagNoList) throws Exception {
        log.debug("정상적으로 들어오니");
        List<UserTagInfo> list = dao.getUserTags(UserTagInfo.builder().userNo(userNo).build());
        log.debug(list.toString());
		if(!list.isEmpty()) {
            int re1 = dao.deleteUserTags(userNo);
            log.debug(re1);
        }
        int result = dao.addUserTags(userNo, tagNoList);
        log.debug("결과 반환");
        return result;
    }

//    @Override
//    public void editUserTags(int userNo, List<Integer> tagNoList) throws Exception {
//        dao.updateUserTags(userNo, tagNoList);
//    }

    @Override
    @Transactional
	public int deleteUserTags(int userNo, List<Integer> tagNoList) throws Exception {
		return dao.deleteUserTags(userNo);
    }

	//아이디 중복확인, 이메일인증
	@Override
	public boolean isUsernameDuplicate(String userId) {
		User user = dao.findByUserId(userId);
        // 있으면 유저가 나올거고, 앖으면 null.
        return user != null;
	}

    @Override
    public boolean isNicknameuplicate(String nickname) {
        User user = dao.findByNickname(nickname);
        // 있으면 유저가 나올거고, 앖으면 null.
        if(user != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean sendEmailAuth(String email) {
        return false;
    }

	@Override
	public List<Review> getUserReviews(int userNo) {
		return dao.getUserReviews(userNo);
	}

	@Override
	public List<BoardReply> getUserComments(int userNo) {
		return dao.getUserComments(userNo);
	}

	@Override
	public void withdrawUser(int userNo) {
		dao.updateStatusToWithdraw(userNo);
		
	}

//	@Override
//	public List<User> findAllUsers(int userNo) {
//		return dao.findAllUsers(userNo);
//	}

	@Override
	public List<User> searchUsers(String searchCriteria, String searchKeyword) {
		return dao.searchUsers(searchCriteria, searchKeyword);
	}

	@Override
	public int updateStatusToN(List<Long> userNos) {
		return dao.updateStatusToN(userNos);
	}

	@Override
	public int updateUsermodal(User user) {
		return dao.updateUsermodal(user);
	}

//	@Override
//	public boolean sendEmailAuth(String email) {
//		SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("이메일 인증 코드");
////        message.setText("인증 코드: 123456"); // 실제로는 무작위로 생성한 인증 코드를 사용해야 합니다.
//
//        Random random = new Random();
//        int authCode = random.nextInt(900000) + 100000; // 100000부터 999999까지의 랜덤한 6자리 수 생성
//        message.setText("인증 코드: " + authCode);
//
//        try {
//            javaMailSender.send(message);
//            return true;
//        } catch (MailException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
	

}
