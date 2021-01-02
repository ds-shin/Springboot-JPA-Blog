package com.dss.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @GetMapping("/user/joinForm")
  public String joinForm(){
    System.out.println("joinForm()");
    return "user/joinForm";
  }

  @GetMapping("/user/loginForm")
  public String loginFrom(){
    System.out.println("loginForm()");
    return "user/loginForm";
  }
}
