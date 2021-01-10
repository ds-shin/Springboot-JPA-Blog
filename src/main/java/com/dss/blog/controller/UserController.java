package com.dss.blog.controller;


import com.dss.blog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하는 있는 /js/**, /css/**, /image/** 허용
@Controller
public class UserController {

  // 인증이 필요없는 곳은 모두 /auth추가
  @GetMapping("/auth/joinForm")
  public String joinForm(){
    System.out.println("joinForm()");
    return "user/joinForm";
  }

  @GetMapping("/auth/loginForm")
  public String loginFrom(){
    System.out.println("loginForm()");
    return "user/loginForm";
  }

/*  //일반적인 사용자 업데이트 처리
  @GetMapping("/user/updateForm")
  public String updateForm(){
    return "user/updateForm";
  }*/

  // SecurityContextHolder > SecurityContext > Authentication 객체의 User 값을 가져온다.
  @GetMapping("/user/updateForm")
  public String updateForm(@AuthenticationPrincipal PrincipalDetail principal){
    return "user/updateForm";
  }
}
