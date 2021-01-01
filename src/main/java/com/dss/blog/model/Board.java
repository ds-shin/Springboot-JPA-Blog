package com.dss.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

  //FetchType.EAGER : 조인해서 즉시 가져올게!! ,명시적으로 설정해줘야한다!
  //FetchType.LAZY : 필요하면 여러개 가져올게   : 기본전략이다.
  @ManyToOne(fetch = FetchType.EAGER) // userid 와 연관관계설정 : Many=Board , User=One : 한명의 user는 여러개의 게시글을 쓸수있다.
  @JoinColumn(name="userid") // 필드가 생성되고 User에 대해서 FK가 생성된다.
  private User user; // DB는 오브젝트를 저장 할수 없다.=> FK사용, 자바는 오브젝트를 저장 할 수 있다.
  // private int userId; // DB 관점(FK)

  // fetch=FetchType.LAZY : 기본값으로 명시적으로 안해도 된다. , 필요할때 가져올 경우
  // 여기서는 상제보기시 바로 보여줘야하기 때문에: FetchType.EAGER로 설정해야한다.
  // 만약 댓글을 펼치기 버튼을 이용할 경우에는 기본전략으로!!!
  // mappedBy 연관관계의 주인이 아니다.(난 FK가 아니예요) DB에 칼럼을 만들지마세요!!
  @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)  // Reply의  Board 객체를 설정해준다.
  //@JoinColumn(name = "replyId") // = FK이다. join 이 필요없다. 하나인 경우에만 설정하자.
  private List<Reply> reply;

  @CreationTimestamp
  private Timestamp createDate;
}
