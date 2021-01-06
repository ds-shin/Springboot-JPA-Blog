package com.dss.blog.repository;

import com.dss.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//=DAO
//@Repository : 생략이 가능한다. 자동으로 빈등록해준다.
//상속만으로도 기본 CRUD를 처리 할 수 있다.!!!
public interface UserRepository extends JpaRepository<User,Integer> {

  // 네이밍 쿼리!!
  // SELECT * FROM user WHERE username = ?1;
  Optional<User> findByUsername(String username); // 규칙:findBy변수명
}
/*
// security 적용하면서 사용하지 않음!!
public interface UserRepository extends JpaRepository<User,Integer> {
  // JPA Naming 쿼리 : jpa 에 없는 메서드인데 다음과 같이 메서드를 정의하면 아래와 같이 쿼리가 자동으로 만들어진다.
  // findBy 이후 문자열을 네이밍전략으로 쿼리를 만들어준다.
  // SELECT * FROM user WHERE username=?1 AND password=?2;
  // case 1
  User findByUsernameAndPassword(String username, String password);

  // case 2 : @Query 를이용해서 만들수도 있다.
  //@Query(value="SELECT * FROM user WHERE username=?1 AND password=?2",nativeQuery = true)
  //User login(String username, String password);
}
*/

