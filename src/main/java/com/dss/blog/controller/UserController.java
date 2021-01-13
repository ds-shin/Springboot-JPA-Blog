package com.dss.blog.controller;

import com.dss.blog.config.auth.PrincipalDetail;
import com.dss.blog.model.KakaoProfile;
import com.dss.blog.model.OAuthToken;
import com.dss.blog.model.User;
import com.dss.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하는 있는 /js/**, /css/**, /image/** 허용
@Controller
public class UserController {

  //실제 운영시에는 applicaion.yml 에서 노출되어지면 안됨!
  @Value("${cos.key}")
  private String coskey;

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

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
  //public @ResponseBody String kakaoCallback(String code){ // Data를 턴해주는 컨트롤러 함수
  public String kakaoCallback(String code){ // Data를 턴해주는 컨트롤러 함수
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

    //System.out.println("카카오 토큰 요청 완료(response) : "+ response);
    System.out.println("카카오 토큰 요청 완료(response) : "+ response.getBody());


    // 라이브러리 :  Gson, JsonSimple, ObjectMapper(내장)
    ObjectMapper objectMapper = new ObjectMapper();

    OAuthToken oAuthToken = null;
    try {
       oAuthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
    } catch (JsonMappingException e) {
      //e.printStackTrace();
      System.out.println("objectMapper JsonMappingException : " + e.getMessage());
    } catch (JsonProcessingException e) {
      //e.printStackTrace();
      System.out.println("objectMapper JsonProcessingException : " + e.getMessage());
    }

    System.out.println("카카오엑세스토큰(oAuthToken) : " + oAuthToken.getAccess_token());

    System.out.println("카카오 토큰 요청 완료(response) : "+ response.getBody());
    System.out.println("===============================================");
    //return response.getBody();


    // 사용자 정보 요청하기
    RestTemplate restTemplate2 = new RestTemplate();

    // Access Token 사용
    HttpHeaders httpHeaders2 = new HttpHeaders();
    httpHeaders2.add("Authorization","Bearer "+oAuthToken.getAccess_token()); // Bearer {ACCESS_TOKEN}
    httpHeaders2.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

    // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
    HttpEntity<MultiValueMap<String,String>> kakaoProfileReqeust2 = new HttpEntity<>(httpHeaders2);

    // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응닫 받기
    ResponseEntity<String> response2 = restTemplate2.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.POST,
            kakaoProfileReqeust2,
            String.class  // 응답을 스트링으로~
    );

    //System.out.println("카카오 토큰 요청 완료(response2) : "+ response2);
    System.out.println("카카오 토큰 요청 완료(response2) : "+ response2.getBody());

    ObjectMapper objectMapper2 = new ObjectMapper();
    KakaoProfile kakaoProfile = null;
    try {
      kakaoProfile = objectMapper2.readValue(response2.getBody(),KakaoProfile.class);
    } catch (JsonMappingException e) {
      //e.printStackTrace();
      System.out.println("objectMapper2 JsonMappingException : " + e.getMessage());
    } catch (JsonProcessingException e) {
      //e.printStackTrace();
      System.out.println("objectMapper2 JsonProcessingException : " + e.getMessage());
    }

    // User Object  : username, password, email
    System.out.println("카카오 아이디(번호) :" + kakaoProfile.getId());
    System.out.println("카카오 아이디(이메일1) :" + kakaoProfile.getKakao_account().getEmail());
    System.out.println("카카오 아이디(이메일2) :" + kakaoProfile.kakao_account.getEmail());
    System.out.println("카카오 아이디(nickname) :" + kakaoProfile.getKakao_account().getProfile().getNickname());
    System.out.println("카카오 아이디(Profile) :" + kakaoProfile.kakao_account.getProfile());

    System.out.println("블로그서버 username :" + kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
    System.out.println("블로그서버 email :" + kakaoProfile.getKakao_account().getEmail());

    // UUID garbagepassword = UUID.randomUUID(); // random garbage key
    //System.out.println("블로그서버 password :" + garbagepassword);
    // UUID 란? 중복되지 않는 어떤 특정값을 만들어내는 알고리즘.
    System.out.println("블로그서버 password :" + coskey);

    User kakaoUser = User.builder()
            .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
            //.password(garbagepassword.toString())
            .password(coskey)
            .email(kakaoProfile.getKakao_account().getEmail())
            .oauth("kakao")
            .build();

    // 가입자 혹은 비가입자 체크 해서 처리!!
    //User originUser = userService.findUser(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());  // 회원찾기
    User originUser = userService.findUser(kakaoUser.getUsername());  // 회원찾기
    
    //비가입자면 회원가입처리
    if(originUser==null) {
   //if(originUser.getUsername()==null) {
      System.out.println("카카오 신규 회원 가입===============");
      userService.insert(kakaoUser);
    }

    System.out.println("자동 로그인을 진행합니다. ===============");

    // Bad credentials 발생하는 원인( WARN 10844 --- [io-8000-exec-10] .m.m.a.ExceptionHandlerExceptionResolver : Resolved [org.springframework.security.authentication.BadCredentialsException: Bad credentials])
    // => 비번을 UUID로 하면 기존 회원의 비번이 수시로 변하기 때문에  Bad credentials 오류가 발생함.
    // 기존 가입자는 기 등록된 암호로 자동로그인 처리되어야 함.
    // kakaoUser.getPassword()가 아닌 coskey 를 설정한다. : coskey 가 노출되면 안됨
    //로그인처리
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),coskey));
    SecurityContextHolder.getContext().setAuthentication(authentication);



    System.out.println("카카오 신규회원 로그인 완료 ===============");

    //public @ResponseBody String kakaoCallback(String code){ // Data를 턴해주는 컨트롤러 함수
    //return response2.getBody(); // 리턴시 @ResponseBody 설정해주고
    return "redirect:/"; // 리턴시 @ResponseBody 를 제거해야한다.
  }
}
