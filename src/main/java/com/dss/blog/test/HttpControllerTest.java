package com.dss.blog.test;

import org.springframework.web.bind.annotation.*;

// postman 으로 테스트하기
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest: ";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        //Member member = new Member(1, "dss", "1234", "dss@nate.com");
        //builder() : 순서에 상관없이 생성자인자를 설정해서 전달 할 수 있다.
        Member member = Member.builder().username("dss").password("1234").email("dss@nate.com").build();
        System.out.println(TAG + "lombokTest:" + member.getId());
        member.setId(5000);
        System.out.println(TAG + "lombokTest:" + member.getId());
        System.out.println(TAG + "lombokTest:" + member.toString());

        return "lombok test 완료";
    }

    // http://localhost:8080/http/get?id=1&username=dss&password=1234&email=sossds@nate.com
    @GetMapping("/http/get") // select
    //public String getTest(@RequestParam int id, @RequestParam String username, @RequestParam String password, @RequestParam String email){
    //여러개의 파라미터를 클래스를 이용해서 받아서 처리하면 된다!
    public String getTest(Member member){
        return "get request : " + member;
    }

    // http://localhost:8080/http/post   : x-www-form
    @PostMapping("/http/post") // insert
    public String postTest(Member member) {  // MessageConvertor(스프링부트)가 알아서 매핑해서 자동으로 처리해줌
        System.out.println("post : " + member);
        return "post request : " + member;
    }

    // http://localhost:8080/http/postbody  : raw(=text/plain)
    //1> body : 안녕
    //2> body : {"id":1,"username":"dss","password":"1234","email":"dss@nate.com"} : raw:Text(text/plain)
        // => postbody(@RequestBody String text)
    //3> body : {"id":1,"username":"dss","password":"1234","email":"dss@nate.com"} : raw:JSON(application/json)
        // => postbody(@RequestBody Member member) ; json 타입으로 넘기면 Member 객체에 그대로 할당해서 넘겨준다.즉, String post(Member member) 와 같게된다.
    // MessageConvertor(스프링부트)가 알아서 마임타입을 매핑해서 처리해줌
    @PostMapping("/http/postbody") // insert
    public String postbodyTest(@RequestBody Member member){
        System.out.println("postbody : " + member);
        return "postbody request : " + member;
    }

    //body : raw : text => {"id":1,"username":"dss","password":"1234","email":"dss@nate.com"}
    @PostMapping("/http/postbodytext") // insert
    public String postbodytxtTest(@RequestBody String text){
        System.out.println("postbodytext : " + text);
        return "postbodytext request : " + text;
    }

    //localhost:8080/http/put
    @PutMapping("/http/put") // update
    public String putTest(@RequestBody Member member){
        System.out.println("putTest : " + member);
        return "put request : " + member;
    }

    //localhost:8080/http/delete
    @DeleteMapping("/http/delete") // delete
    public String deleteTest(@RequestBody Member member){
        System.out.println("deleteTest : " + member);
        return "delete request : " + member;
    }
}
