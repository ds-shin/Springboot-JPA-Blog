package com.dss.blog.controller.api;

import com.dss.blog.config.auth.PrincipalDetail;
import com.dss.blog.dto.ReplySaveRequstDto;
import com.dss.blog.dto.ResponseDto;
import com.dss.blog.model.Board;
import com.dss.blog.model.Reply;
import com.dss.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

  @Autowired
  private BoardService boardService;

  @PostMapping("/api/board")
  public ResponseDto<Integer> insert(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
    System.out.println("BoardApiController insert 호출됨 :" + board);

    System.out.println("USER = :" + principal);

    boardService.write(board,principal.getUser());

    // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson 처리)
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1 ); // 200 , 1
  }

  @DeleteMapping("/api/board/{id}")
  public ResponseDto<Integer> deleteById(@PathVariable int id){
    boardService.delete(id);
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1 ); // 200 , 1
  }

  @PutMapping("/api/board/{id}")
  public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
    boardService.update(id,board);
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1 ); // 200 , 1
  }


/*  // 데이터 받을때 컨트롤러에서 dto를 만들어서 받는게 좋다.
  @PostMapping("/api/board/{boardid}/reply")
  public ResponseDto<Integer> replySave(@PathVariable int boardid, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal){
    System.out.println("BoardApiController replySave 호출됨 :" + reply);

    System.out.println("USER = :" + principal);

    boardService.reply_write(principal.getUser(),boardid,reply);

    // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson 처리)
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1 ); // 200 , 1
  }*/
  // 데이터 받을때 컨트롤러에서 dto를 사용해서 구현하기
  @PostMapping("/api/board/{boardid}/reply")
  public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequstDto replySaveRequstDto){
    System.out.println("BoardApiController replySave 호출됨 :" + replySaveRequstDto);

    System.out.println("USER = :" + replySaveRequstDto);

    boardService.reply_write(replySaveRequstDto);

    // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson 처리)
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1 ); // 200 , 1
  }
}
