package com.dss.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity // User 클래스가 Mysql에 테이블이 생성이 된다.
public class User {

  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.(application.yml=>use-new-id-generator-mappings: false)
  private int id; // 시퀀스, auto_increment ( mysql,oracle db에 따라 적용된다.)

  @Column(nullable = false, length = 30)
  private String username; // 아이디

  @Column(nullable = false, length = 100) // 12345 => 추후 해쉬이용(비밀번호 암호화)
  private String password;

  @Column(nullable = false, length = 50)
  private String email;

  @ColumnDefault("'user'") // 'user' :문자를 의미함.컬럼의 기본값을 설정
  private String role;  // Enum을 쓰는게 좋다.// admin, user, manager (enum사용시 오타방지)

  @CreationTimestamp  // 시간이 자동입력
  private Timestamp createDate;
}

// https://getinthere.tistory.com/20 참조
