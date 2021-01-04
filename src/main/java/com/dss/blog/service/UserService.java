package com.dss.blog.service;

import com.dss.blog.model.User;
import com.dss.blog.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

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
      userRepository.save(user);
    }catch(Exception exception){
      System.out.println("exception error : "+ exception.getMessage());
    }
    /*
    "status": 500,
    "data": "Transaction silently rolled back because it has been marked as rollback-only"
     */
  }

  @Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
  public User login(User user){
    // 호출되는 지점에서 select 쿼리가 여러번 사용되도라도 정합성을 보장해준다.(readOnly=true 인경우)
    return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
  }
}
