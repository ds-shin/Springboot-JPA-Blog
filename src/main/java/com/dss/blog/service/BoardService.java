package com.dss.blog.service;

import com.dss.blog.model.Board;
import com.dss.blog.model.User;
import com.dss.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

  @Autowired
  private BoardRepository boardRepository;

  //회원가입
  @Transactional
  public void write(Board board, User user)  {
    System.out.println("BoardService write() call === ");
    // exception 발생시 무조건 GlobalExceptionHandler 을 호출한다.
    // try 구문을 감싸주면 리턴되는 메세지 차이가 있다.
    try{

      board.setCount(0);
      board.setUser(user);

      boardRepository.save(board);
    }catch(Exception exception){
      System.out.println("exception error : "+ exception.getMessage());
    }
    /*
    "status": 500,
    "data": "Transaction silently rolled back because it has been marked as rollback-only"
     */
  }

  public List<Board> list(){
    return boardRepository.findAll(); // 글목록을 리턴
  }
}
