package com.dss.blog.controller;


import com.dss.blog.config.auth.PrincipalDetail;
import com.dss.blog.model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


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

  @GetMapping("/auth/kakao/callback")
  public @ResponseBody String kakaoCallback(String code){ // Data를 턴해주는 컨트롤러 함수
    System.out.println("카카오인증완료 : kakaoCallback() code : " + code);


    //POST방식으로 key=value 데이터를 요청(카카오쪽으로)
    //HttpsURLConnection , Retrofit2(안드로이드) , OkHttp ,RestTemplate 사용가능
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

    //변수로 넘겨받아서 처리하는 방식으로 ~
    MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
    params.add("grant_type","authorization_code");
    params.add("client_id","bf591b4518919bd509251f6d16ee63a4");
    params.add("redirect_uri","http://localhost:8000/auth/kakao/callback");
    params.add("code",code);

    // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
    HttpEntity<MultiValueMap<String,String>> kakaoTokenReqeust = new HttpEntity<>(params,httpHeaders);

    // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응닫 받기
    ResponseEntity<String> response = restTemplate.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenReqeust,
            String.class  // 응답을 스트링으로~
    );

    //System.out.println("카카오 토큰 요청 완료 : "+ response);
    System.out.println("카카오 토큰 요청 완료 : "+ response.getBody());


    // 라이브러리 :  Gson, JsonSimple, ObjectMapper(내장)
    ObjectMapper objectMapper = new ObjectMapper();

    OAuthToken oAuthToken = null;
    try {
       oAuthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
    } catch (JsonMappingException e) {
      //e.printStackTrace();
      System.out.println("JsonMappingException : " + e.getMessage());
    } catch (JsonProcessingException e) {
      //e.printStackTrace();
      System.out.println("JsonProcessingException : " + e.getMessage());
    }

    System.out.println("카카오엑세스토큰 : " + oAuthToken.getAccess_token());

    return response.getBody();
  }
}
