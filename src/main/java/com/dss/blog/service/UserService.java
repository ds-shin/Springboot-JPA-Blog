package com.dss.blog.service;

import com.dss.blog.model.RoleType;
import com.dss.blog.model.User;
import com.dss.blog.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  //회원가입
  @Transactional
  public void insert(User user)  {
    // exception 발생시 무조건 GlobalExceptionHandler 을 호출한다.
    //userRepository.save(user);
    /*
    "status": 500,
    "data": "could not execute statement; SQL [n/a]; constraint [UK_jreodf78a7pl5qidfh43axdfb]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"
    */

    // exception 발생시 무조건 GlobalExceptionHandler 을 호출한다.
    // try 구문을 감싸주면 리턴되는 메세지 차이가 있다.
    try{
      String rawPassword = user.getPassword();  // 원문 암호
      String encPassword = encoder.encode(rawPassword); // 해쉬 암호화

      user.setPassword(encPassword);
      user.setRole(RoleType.USER);

      userRepository.save(user);
    }catch(Exception exception){
      System.out.println("exception error : "+ exception.getMessage());
    }
    /*
    "status": 500,
    "data": "Transaction silently rolled back because it has been marked as rollback-only"
     */
  }

  @Transactional
  public void update(User user){
    // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고 영속화된 User 오브젝트를 수정
    // select를 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서
    // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌
    User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
             return new IllegalArgumentException("회원찾기 실패");
            });
    String rawPassword = user.getPassword();
    String encPassword = encoder.encode(rawPassword);
    persistance.setPassword(encPassword);
    persistance.setEmail(user.getEmail());

    // 회원수정함수 종료시 = 서비스 종료= 트랜잭션종료=commit 이 자동으로 됨
    // 영속화된 persistence 객체의 변화가 감지되면 더티체키이 되어 update문을 날려줌.
  }

  /*
  //security를 적용하므로 해당 메서드는 사용하지 않음!
  @Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
  public User login(User user){
    // 호출되는 지점에서 select 쿼리가 여러번 사용되도라도 정합성을 보장해준다.(readOnly=true 인경우)
    return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
  }*/

}
