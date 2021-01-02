package com.dss.blog.handler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice  // 어디에서든 발생하는 모든 에러가 이쪽으로 오도록 설정(모든 exception)
@RestController
public class GlobalExceptionHandler {

  @ExceptionHandler(value=IllegalArgumentException.class)
  public String handleArugumentException(IllegalArgumentException e){
    return "<h1>" + e.getMessage() + "</h1>";
  }

  @ExceptionHandler(value= EmptyResultDataAccessException.class)
  public String handleArugumentException(EmptyResultDataAccessException e){
    return "<h1>" + e.getMessage() + "</h1>";
  }
}
