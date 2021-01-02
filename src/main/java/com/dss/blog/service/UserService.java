package com.dss.blog.service;

import com.dss.blog.model.User;
import com.dss.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  //회원가입
  @Transactional
  public Integer insert(User user){
    try{
      userRepository.save(user);
      return 1;
    }catch (Exception e){
      System.out.println("UserService save() :" + e.getMessage());
    }
    return -1;
  }
}
