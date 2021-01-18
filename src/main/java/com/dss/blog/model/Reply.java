package com.dss.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, length = 200)
  private String content;

  @ManyToOne // 여러개의 답변은 하나의 게시글에 존재한다.
  @JoinColumn(name = "boardId")
  private Board board;

  @ManyToOne // 여러개의 답변은 하나의 유저만 쓸수있다.
  @JoinColumn(name = "userId")
  private User user;

  @CreationTimestamp
  private Timestamp createDate;
}
