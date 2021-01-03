package com.dss.blog.handler;

import com.dss.blog.dto.ResponseDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice  // 어디에서든 발생하는 모든 에러가 이쪽으로 오도록 설정(모든 exception)
@RestController
public class GlobalExceptionHandler {

  @ExceptionHandler(value=Exception.class)
  public ResponseDto<String> handleArugumentException(Exception e){

    System.out.println("GlobalExceptionHandler call : " + e.getMessage());

    return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
  }

/*
  @ExceptionHandler(value=IllegalArgumentException.class)
  public String handleArugumentException(IllegalArgumentException e){
    return "<h1>" + e.getMessage() + "</h1>";
  }

  @ExceptionHandler(value= EmptyResultDataAccessException.class)
  public String handleArugumentException(EmptyResultDataAccessException e){
    return "<h1>" + e.getMessage() + "</h1>";
  }

  @ExceptionHandler(value= Exception.class)
  public String handleArugumentException(Exception e){
    return "<h1>" + e.getMessage() + "</h1>";
  }
  */
}
