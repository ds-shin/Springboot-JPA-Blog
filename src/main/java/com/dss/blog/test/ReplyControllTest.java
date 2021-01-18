package com.dss.blog.test;

import com.dss.blog.model.Board;
import com.dss.blog.model.Reply;
import com.dss.blog.repository.BoardRepository;
import com.dss.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReplyControllTest {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private ReplyRepository replyRepository;

  @GetMapping("/test/board/{id}")
  public Board getBoard(@PathVariable int id){

    // board에서 replys의 board정보를 제외[@JsonIgnoreProperties({"board"})]하고 리턴한다.
    // jakson 라이브러리 (오브젝트를 json으로 리턴) => 모델의 getter를 호출
    return boardRepository.findById(id).get();
  }

  @GetMapping("/test/reply")
  public List<Reply> getReply(){
    // direct로 호출 할 경우에는 board,user 객체를 모두 리턴한다.
    return replyRepository.findAll();
  }
}

