package com.dss.blog.controller.api;

import com.dss.blog.config.auth.PrincipalDetail;
import com.dss.blog.dto.ResponseDto;
import com.dss.blog.model.Board;
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

}
