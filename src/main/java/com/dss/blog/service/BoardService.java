package com.dss.blog.service;

import com.dss.blog.dto.ReplySaveRequstDto;
import com.dss.blog.model.Board;
import com.dss.blog.model.Reply;
import com.dss.blog.model.User;
import com.dss.blog.repository.BoardRepository;
import com.dss.blog.repository.ReplyRepository;
import com.dss.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private ReplyRepository replyRepository;

  @Autowired
  private UserRepository userRepository;

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

  @Transactional(readOnly = true)
  public Page<Board> list(Pageable pageable){
    return boardRepository.findAll(pageable); // 글목록을 리턴
  }

  @Transactional(readOnly = true)
  public Board view(int id) {
    //reply를 fetch = FetchType.EAGER 로 설정해서 jpa에서 알아서 갖고있다. view에서 그냥 사용하면 된다!!!
    return boardRepository.findById(id)
            .orElseThrow(()->{
              return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수없습니다.");
            });
  }

  @Transactional
  public void delete(int id) {
    boardRepository.deleteById(id);
  }

  @Transactional
  public void update(int id, Board reqeustBoard) {
    //영속화 설정
    Board board = boardRepository.findById(id)
            .orElseThrow(()->{
              return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수없습니다.");
            }); // 영속화완료
    board.setTitle(reqeustBoard.getTitle());
    board.setContent(reqeustBoard.getContent());
    // 해당 함수로 종료시(Service가 종료될때) 트랜잭션이 종료됨. 이때 더티체킹 - 자동 업데이트가 됨. db flush
  }

  @Transactional 
  public void reply_write(ReplySaveRequstDto replySaveRequstDto) {

    User user = userRepository.findById(replySaveRequstDto.getUserid()).orElseThrow(()->{
      return new IllegalArgumentException("댓글 쓰기 실퍠 : 사용자 id를 찾을 수 없습니다.");
    }); // 영속성완료

    Board board =boardRepository.findById(replySaveRequstDto.getBoardid()).orElseThrow(()->{
      return new IllegalArgumentException("댓글 쓰기 실퍠 : 게시글 id를 찾을 수 없습니다.");
    }); // 영속성완료

    Reply reply = Reply.builder()
            .user(user)
            .board(board)
            .content(replySaveRequstDto.getContent())
            .build();
//    Reply reply = new Reply();
//    reply.update(user,board,replySaveRequstDto.getContent());

    replyRepository.save(reply);
  }

/*  @Transactional
  public void reply_write(User user, int boardid, Reply requestReply) {

    Board board =boardRepository.findById(boardid).orElseThrow(()->{
      return new IllegalArgumentException("댓글 쓰기 실퍠 : 게시글 id를 찾을 수 없습니다.");
    }); // 영속성완료

    requestReply.setUser(user);
    requestReply.setBoard(board);

    replyRepository.save(requestReply);
  }*/
}
