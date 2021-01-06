package com.dss.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
  @Test
  public void test(){
    String  endpwd = new BCryptPasswordEncoder().encode("1234");
    System.out.println("hash value : " + endpwd);
  }
}
