package com.dss.blog.controller.api;

import com.dss.blog.dto.ResponseDto;
import com.dss.blog.model.RoleType;
import com.dss.blog.model.User;
import com.dss.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

  @Autowired
  private UserService userService;

  @PostMapping("/api/user")
  public ResponseDto<Integer> insert(@RequestBody User user){
    System.out.println("UserApiController insert 호출됨 :" + user);

    user.setRole(RoleType.USER);
    userService.insert(user);

    // 자바오브젝트를 JSON으로 변환해서 리턴(Jackson 처리)
    return new ResponseDto<Integer>(HttpStatus.OK.value(),1 ); // 200 , 1
  }
}
