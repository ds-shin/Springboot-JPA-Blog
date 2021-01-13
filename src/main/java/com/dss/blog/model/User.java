package com.dss.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity // User 클래스가 Mysql에 테이블이 생성이 된다.
//@DynamicInsert // 저장시 지정한 컬럼(명시적으로 설정한 컬럼)에 대해서만 INSERT 처리 기능을 한다.(즉,INSRET시 NULL인 필드를 제외시켜준다)
public class User {

  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.(application.yml=>use-new-id-generator-mappings: false)
  private int id; // 시퀀스, auto_increment ( mysql,oracle db에 따라 적용된다.)

  @Column(nullable = false, length = 100, unique = true) // 중복허용방지
  private String username; // 아이디

  @Column(nullable = false, length = 100) // 12345 => 추후 해쉬이용(비밀번호 암호화)
  private String password;

  @Column(nullable = false, length = 50)
  private String email;

  //@ColumnDefault("'user'") // 'user' :문자를 의미함.컬럼의 기본값을 설정
  // ==> 기본컬럼값을 지정하지 않으면 INSERT시 컬럼이 추가되지 않는다.
  //private String role;  // Enum을 쓰는게 좋다.// admin, user, manager (enum사용시 오타방지)

  //@ColumnDefault("'user'") // 'user' :문자를 의미함.컬럼의 기본값을 설정
  // enum으로 설정을 하면 DB는 인지하지 못한다.(DB는 RoleType이 없다.)
  @Enumerated(EnumType.STRING)  // 스트링이라고 알려줘야한다!!
  private RoleType role;

  private String oauth; // kakao,google

  @CreationTimestamp  // 시간이 자동입력
  private Timestamp createDate;
}

// https://getinthere.tistory.com/20 참조
