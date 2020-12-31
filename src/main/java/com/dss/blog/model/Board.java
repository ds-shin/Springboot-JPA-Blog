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
@Builder
@Entity
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
  private int id;

  @Column(nullable = false, length = 100)
  private String title;

  @Lob // 대용량 데이터
  private String content ; // (tool)섬머노트 라이브러리 <html>태그가 썩여서 디자인이 됨

  @ColumnDefault("0")
  private int count; //조회수

  @ManyToOne // userid 와 연관관계설정 : Many=Board , User=One : 한명의 user는 여러개의 게시글을 쓸수있다.
  @JoinColumn(name="userid") // 필드가 생성되고 User에 대해서 FK가 생성된다.
  private User user; // DB는 오브젝트를 저장 할수 없다.=> FK사용, 자바는 오브젝트를 저장 할 수 있다.
  // private int userId; // DB 관점(FK)

  @CreationTimestamp
  private Timestamp createDate;
}
