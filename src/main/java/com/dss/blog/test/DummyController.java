package com.dss.blog.test;

import com.dss.blog.model.RoleType;
import com.dss.blog.model.User;
import com.dss.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//html파일이 아니라 data를 리턴해주는 controller = @RestController
@RestController
public class DummyController {

  @Autowired // 의존성주입(DI)
  private UserRepository userRepository;


  // 여러건 조회
  // http://localhost:8000/blog/dummy/user
  @GetMapping("/dummy/users")
  public List<User> list() {
    return userRepository.findAll();
  }

  //case1 : 한페이지당 2건에 데이터를 리턴 : Page 객체의 모든 정보를 리턴할때
  //http://localhost:8000/blog/dummy/user
  //http://localhost:8000/blog/dummy/user?page=0
  @GetMapping("/dummy/user")
  public Page<User> pageList(@PageableDefault(size=2,sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
    Page<User> users = userRepository.findAll(pageable);
    return users;
  }

  //case2 : 한페이지당 2건에 데이터를 리턴 : Page 객체의 페이지 정보(Contents)만 리턴할때
  //http://localhost:8000/blog/dummy/user2
  //http://localhost:8000/blog/dummy/user2?page=0
  @GetMapping("/dummy/user2")
  public List<User> pageList2(@PageableDefault(size=2,sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
    List<User> users = userRepository.findAll(pageable).getContent();
    return users;
  }

  //case3 : 한페이지당 2건에 데이터를 리턴 : Page 객체의 페이지 정보(Contents)만 리턴할때
  //http://localhost:8000/blog/dummy/user3
  //http://localhost:8000/blog/dummy/user32?page=0
  @GetMapping("/dummy/user3")
  public List<User> pageList3(@PageableDefault(size=2,sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
    Page<User> pagingUsers = userRepository.findAll(pageable);

    if(pagingUsers.isLast()){
      //
    }
    List<User> users = pagingUsers.getContent();

    return users;
  }

  // 단건조회
  // {id}주소로 파라미터를 전달받을수 있다.
  // http://localhost:8000/blog/dummy/user/3
  @GetMapping("/dummy/user/{id}")
  public User detail(@PathVariable int id){

    // 조회하는 대상이 널인경우 Optinal로 체크하여 처리
    // get()을 : 무조건 데이터가 있어
    // orElseGet() : 없으면 객체하나 만들어서 넣어줘~
    /*User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
      @Override
      public User get() {
        // null이여서 객체를 만들어서 넣어줄께!!
        System.out.println("USER: 데이터가 없어서 빈객체를 넣어줫어!!");
        return new User(); // return {"id":0,"username":null,"password":null,"email":null,"role":null,"createDate":null}
      }
    });*/

    /*User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
      @Override
      public IllegalArgumentException get() {
        return new IllegalArgumentException("해당 유저는 없습니다. id :" +id);
      }
    });*/

    //람다식으로 처리시
    User user = userRepository.findById(id).orElseThrow(()->{
      return new IllegalArgumentException("해당 유저는 없습니다. id :" +id);
    });

    // 요청 :웹브라워
    // user 객체 : 자바오브젝트
    // 반환 (웹브라우저가 이해할수 있는 데이터) -> json(Gson 라이브러리)
    // 만약에 자바 오프젝트를 리턴하게되면 MessageConverter가 Jackson라이브러를 호출해서
    // user 오브젝트를 json으로 반환해서 브라우저에게 던져준다. (who ? 스프링부트가)
    return user;
  }

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
