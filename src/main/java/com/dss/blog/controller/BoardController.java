package com.dss.blog.controller;

import com.dss.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

  /*
  // index에서도 보안 설정을 할 경우(인증이 필요한 경우)
  @GetMapping({"","/"})
  public String index(@AuthenticationPrincipal PrincipalDetail principal){ // 컨트롤로에서 세션을 어떻게 찾는지?
    System.out.println("로그인 사용자 아이디 : "+ principal.getUsername());
    // /WEB-INF/views/index.jsp
    return "index";
  }*/

  @GetMapping({"","/"})
  public String index(){
    System.out.println("index() call =====");
    return "index";
  }
  
  //User 권한이 필요 -- 글쓰기 창으로 이동
  @GetMapping("/board/saveForm")
  public String saveForm(){
    return "board/saveForm";
  }
}
