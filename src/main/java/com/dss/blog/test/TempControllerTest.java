package com.dss.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

  //http://localhost:8000/blog/temp/home :: 실행은 되지만 웹은 오류가 발생한다.(매핑이 안되었다.)=>static
  @GetMapping("/temp/home")
  public String tempHome(){
    System.out.println("tempHome()");

    //파일리턴 기본경로 : src/main/resources/static : static.html 로 리턴한다.
    //리턴명: /home.html  // 고로 리턴명을 *.html을 적어준다.
    //풀경로 : src/main/resources/static/home.html
    return "/home.html";  // 파일을 리턴
  }

  //http://localhost:8000/blog/temp/img
  @GetMapping("/temp/img")
  public String TempImg(){
    System.out.println("tempImg()");
    return "/a.png";
  }

  @GetMapping("/temp/jsp")
  public String tempjsp(){
    System.out.println("tempJsp()");

    // prefix : /WEB-INF/views/
    // suffix : .jsp
    // fullname : /WEB-INF/views/test.jsp
    return "test";
  }
}
