package com.dss.blog.controller.api;

import com.dss.blog.dto.ResponseDto;
import com.dss.blog.model.User;
import com.dss.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiController {

  @Autowired
  private UserService userService;

  @Autowired
  public AuthenticationManager authenticationManager;
  /* 주입해서 사용해되도고 메서드 인자로 받아서 바로 사용해도 된다.
  @Autowired
  HttpSession session;
  */

  @PostMapping("/auth/joinProc") // join을 수행하는곳
  public ResponseDto<Integer> insert(@RequestBody User user){ // username,password,email
    System.out.println("UserApiController insert 호출됨 :" + user);

    userService.insert(user);

    // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson 처리)
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1 ); // 200 , 1
  }

  // 스프링 시큐리티(spring-boot-starter-security)를 설정하면
  // 로그인시 스프링 시큐리티가 가로채서 로그인 화면으로 무조건 보낸다
/*  @PostMapping("/api/user/login")  // 전통적인 로그인 방식은 사용하지 않음(보안이슈)
  public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
    System.out.println("UserApiController login 호출됨 :" + user);

    User principal = userService.login(user);  // principal(접근주체)

    //HttpSession session : @Autowired 로 주입해서 사용해도 된다.
    if(null!=principal){
      session.setAttribute("principal",principal);
    }

    return new ResponseDto<Integer>(HttpStatus.OK.value(),1 );
  }*/

  @PutMapping("/user")
  public ResponseDto<Integer> update(@RequestBody User user
//                                     ,@AuthenticationPrincipal PrincipalDetail principal,
//                                     HttpSession session
  ) {
    System.out.println("user update : "+ user);
    userService.update(user);

    //여기서는 트랜잭션이 종료되기 때문에 db에 값은 변경이 됐음.
    //하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경 해 줘야한다.
    //강제로 세션정보를 변경한다.
    //다음과 같이 추가해도 세션정보가 변경되지 않음 => SecurityConfig authenticationManagerBean 구현!!
//    Authentication authentication =
//            new UsernamePasswordAuthenticationToken(principal,null, principal.getAuthorities()); // arg1,arg2,arg3 => arg1:사용자정보, arg2:옵션, arg3:권한정보
//    SecurityContext securityContext = SecurityContextHolder.getContext();
//    securityContext.setAuthentication(authentication);
//    session.setAttribute("SPRING_SECURITY_CONTEXT",securityContext);

    // 계층 : SecurityContextHolder > SecurityContext > Authentication
    // 디비 변경 완료후에 변경된 정보로 세션처리!!!!!
    // 변경된 세션 등록
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return new ResponseDto<Integer>(HttpStatus.OK.value(),1 ); // 200 , 1
  }

}
