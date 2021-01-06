package com.dss.blog.controller;

import com.dss.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

  @GetMapping({"","/"})
  public String index(@AuthenticationPrincipal PrincipalDetail principal){ // 컨트롤로에서 세션을 어떻게 찾는지?
    System.out.println("로그인 사용자 아이디 : "+ principal.getUsername());
    // /WEB-INF/views/index.jsp
    return "index";
  }
}
