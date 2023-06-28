package com.spring.bittlebittle.board.service;

import com.spring.bittlebittle.board.dao.BoardReplyDaoImpl;
import com.spring.bittlebittle.board.vo.BoardReply;
import com.spring.bittlebittle.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminBoardReplyServiceImpl implements AdminBoardReplyService{

    @Autowired
    private BoardReplyDaoImpl dao;
    
    @Autowired
    private UserDao userDao;

    @Override
    public List<BoardReply> getReplyList(int replyNo) {
        return dao.getReplyList(replyNo);
    }

    @Override
    public void addReply(BoardReply reply, int userNo, String nickname) {
    }

//    @Override
//    public void addReply(BoardReply boardReply, int userNo, String nickname) {
//        User user = userDao.selectUsers().get(0);
//        boardReply.setUserNo(user.getUserNo());
//        dao.addReply(boardReply);
//
//    }

//    @Override
//    public void updateReply(BoardReply reply) {
//    	dao.updateReply(reply);
//    }

    @Override
    public void deleteReply(int replyNo) {
    	dao.deleteReply(replyNo);
    }

    @Override
    public boolean isAdmin(String adminYn) {
        return false;
    }

//  @Override
//  public boolean isAdmin(String adminYn) {
//      // Ư�� ����� �ۼ������� Ȯ���ϴ� ����
//      User user = new User();
//      		
//      if (user.getAdminYn() == "y") {
//          return true;
//      } else {
//          return false;
//      }
//  }
  
//  @Override
//  public boolean isAdmin(String adminYn) {
//      User user = userDao.selectUsers().get(0);
//      return user.getAdminYn().equals("y");
//  }
    
    
//    @Override
//    public boolean isAuthor(int replyNo, int userNo) {
//        // Ư�� ����� �ۼ������� Ȯ���ϴ� ����
//        BoardReply reply = new BoardReply();
//        		
//        if (reply != null && reply.getUserNo() == userNo) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//    @Override
//    public boolean isAuthor(int replyNo, int userNo) {
//        // Ư�� ����� �ۼ������� Ȯ���ϴ� ����
//        BoardReply reply = dao.getReplyList();
//        if (reply != null && reply.getUserNo() == userNo) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    


//    public List<BoardReply> getReplyList(int replyNo) {
//        return dao.getReplyList(replyNo);
//    }
//
//    public void addReply(BoardReply reply, int userNo, String nickname) {
//        dao.addReply(reply, userNo, nickname);
//        
//    }
//
//    public void updateReply(BoardReply reply) {
//        int replyNo = reply.getReplyNo();
//        		
//        int userNo = reply.getUserNo();
//        	
//        if (dao.isAuthor(replyNo, userNo)) {
//            dao.updateReply(reply);
//        }
//    }
//
//    public void deleteReply(int replyNo, int userNo){
//            if (dao.isAuthor(replyNo, userNo)) {
//                dao.deleteReply(replyNo);
//            }
//        }
//
//	@Override
//	public void deleteReply(int replyNo) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean isAuthor(int replyNo, int userNo) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
