package com.dss.blog.test;

import com.dss.blog.model.RoleType;
import com.dss.blog.model.User;
import com.dss.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

  @Autowired // 의존성주입(DI)
  private UserRepository userRepository;

  // 회원가입
  // @RequestParam 를 생략하면 파라미터명과 변수명을 일치해서 작성하면된다.
  // @RequestParam("username") String u : 설정하면 변수명을 변경 할수도 있다.(동일하게 사용해도 된다.)
  @PostMapping("/dummy/join")
  public String join(String username,String password, String email){
    System.out.println("username : " + username);
    System.out.println("password : " + password);
    System.out.println("email : " + email);
    return "회원완료가입1";
  }
  @PostMapping("/dummy/join2")
  public String join(User user){
    System.out.println("id : " + user.getId());
    System.out.println("username1 : " + user.getUsername());
    System.out.println("password2 : " + user.getPassword());
    System.out.println("email2 : " + user.getEmail());
    System.out.println("role : " + user.getRole());

    user.setRole(RoleType.USER);
    // 회원저장
    userRepository.save(user);
    return "회원완료가입2";
  }
}
