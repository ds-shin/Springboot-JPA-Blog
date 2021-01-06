package com.dss.blog.config;

import com.dss.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 시큐리티 필터가 등록이 된다.(Controller 전에 무조건 필터링 하자~)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하자!
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private PrincipalDetailService principalDetailService;

  //hash code로 반환
  @Bean // 호출할때마다 암호화해서 리턴해준다.
  public BCryptPasswordEncoder encodePWD(){
    //String encPassword = new BCryptPasswordEncoder().encode("1234");
    return new BCryptPasswordEncoder();
  }

  // 시큐리트가 대신 로그인해주는데 password를 가로채기를 하는데
  // 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
  // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교 할 수 있다.


  // principalDetailService 를 만들어서 넘겨줘야만 패스워드를 비교 할 수 있다..필수조건!!
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()// csrf 토큰 비활성화(테스트시 걸어주는게 좋음) , token이 없으면 기본으로 막힘(차후 token을 만들어서 넘겨주면서 disable()를 주석처리해준다.
            .authorizeRequests()  // 요청을 받음
            //.antMatchers("/auth/loginForm", "/auth/joinForm") //해당 url로 접근하면
            .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
            .permitAll()      // 허용하겠다.
            .anyRequest()     // 그외 요청이면
            .authenticated() // 인증을 거쳐야 해~
        .and()  // http://localhost:8000/ 하면 Forbidden 에러나 발생=>로그인 페이지로 보내자!
            .formLogin()
            .loginPage("/auth/loginForm")
            .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인을 해준다.: UserDetails를 상속한 User Object를 만들어줘야한다.
            .defaultSuccessUrl("/")  // 로그인후 지정해준 url로 보낸다.
            //.failureUrl("/fail") // 실패시 해당 url로 보낸다.
    ;
  }
}
