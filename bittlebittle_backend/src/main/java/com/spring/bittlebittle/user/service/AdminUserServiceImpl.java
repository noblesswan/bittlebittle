package com.spring.bittlebittle.user.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.user.dao.AdminUserDao;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	private Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private AdminUserDao adminDao;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
//
    @Value("${secretKey}")
    private String key;
	
	
	@Override
	public int updateUser(User user) {
		 return adminDao.updateUser(user);
	}

	@Override
	public int deleteUser(User user) {
		return adminDao.deleteUser(user);
	}

	@Override
	public List<User> selectUsers() {
		 return adminDao.selectUsers();
	}

	@Override
	public User selectListbyNo(User user) {
		return adminDao.selectListbyNo(user);
	}

	@Override //관리자등록
	public int adminUser(User user) {
		 user.setUserPwd(passwordEncoder.encode(user.getUserPwd()));
	        return adminDao.adminRegister(user);
	}
	
	//아래는 JWT영역

	@Override
	public UserJwt getUserJwt(UserJwt userJwt) {
		 return adminDao.selectUserJwt(userJwt);
	}

	@Override
	 @Transactional
	public UserJwt createUserJwt(UserJwt userJwt) {
		 userJwt.setUserJwtIdx(passwordEncoder.encode(userJwt.getSubject() + key));
		 adminDao.insertJwtWithIdx(userJwt);
	        return adminDao.selectUserJwt(userJwt);
	}

	@Override
	 @Transactional
	public UserJwt editUserJwt(UserJwt userJwt) {
		adminDao.updateUserJwt(userJwt);
        return adminDao.selectUserJwt(userJwt);
	}

	@Override
	public UserJwt getUserJwtBySubject(UserJwt userJwt) {
		 return adminDao.selectUserJwtBySubject(userJwt);
	}

	@Override
	public int removeUserJwt(UserJwt userJwt) {
		return adminDao.deleteUserJwt(userJwt);
	}

	@Override
	public Boolean loginUser(User user) {
		// login �떆 �궗�슜�븯�뒗 id 留� 媛�吏�怨� �씪�떒 db 瑜� 遺덈윭�삩 �뮘
        User loginUser = adminDao.selectLoginUser(user);

        // 留뚯빟 �쑀�� �븘�씠�뵒媛� �씪移� �븯吏� �븡�쑝硫� db �뿉 議고쉶媛� �븞�맆 寃껋씠怨�,
        if (loginUser == null) {
            log.debug("�빐�떦 �븘�씠�뵒�쓽 �쑀��媛� 議댁옱�븯吏� �븡�뒿�땲�떎.");
            return false;
        }

        // 留뚯빟 鍮꾨�踰덊샇媛� �씪移섑븯吏� �븡�뒗�떎硫�
        if (!passwordEncoder.matches(user.getUserPwd(), loginUser.getUserPwd())) {
            log.debug(passwordEncoder.encode(user.getUserPwd()));
            log.debug(user.getUserPwd());
            log.debug(loginUser.getUserPwd());
            log.debug("鍮꾨�踰덊샇媛� �씪移섑븯吏� �븞�뒿�땲�떎.");
            return false;
        }
        log.debug("濡쒓렇�씤�뿉 �꽦怨듯뻽�뒿�땲�떎.");
        return true;
	}

//	@Override
//	public User getUser(User user) {
//		return adminDao.selectUser(user);
//	}
	
	
	
	

}
