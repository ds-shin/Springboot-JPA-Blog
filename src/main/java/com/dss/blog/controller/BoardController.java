package com.dss.blog.controller;

import com.dss.blog.service.BoardService;
import com.sun.javafx.iio.common.SmoothMinifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

  @Autowired
  private BoardService boardService;

  @GetMapping({"","/"})
  public String index(Model model,
                      @PageableDefault(size=3, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){  // 데이터를 가져가서 보여주자!
    System.out.println("index() call =====");

    model.addAttribute("boards",boardService.list(pageable));

    return "index";  // viewResolver 작동!!
  }


  @GetMapping("/board/{id}")
  public String findById(@PathVariable int id,Model model){
    System.out.println("findById() call =====");
    model.addAttribute("board",boardService.view(id));
    return "board/detail";
  }

  @GetMapping("/board/{id}/updateForm")
  public String updateForm(@PathVariable int id, Model model){
    System.out.println("updateForm() call =====");

    model.addAttribute("board",boardService.view(id));
    return "board/updateForm";
  }

  //User 권한이 필요 -- 글쓰기 창으로 이동
  @GetMapping("/board/saveForm")
  public String saveForm(){
    System.out.println("saveForm() call =====");

    return "board/saveForm";
  }
}
