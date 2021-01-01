package com.dss.blog.repository;

import com.dss.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//=DAO
//@Repository : 생략이 가능한다. 자동으로 빈등록해준다.
//상속만으로도 기본 CRUD를 처리 할 수 있다.!!!
public interface UserRepository extends JpaRepository<User,Integer> {

}
