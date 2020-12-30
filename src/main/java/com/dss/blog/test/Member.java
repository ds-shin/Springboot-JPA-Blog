package com.dss.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // = @Getter , @Setter 를 합친것과 같다.
//@AllArgsConstructor // 모든 필드에 대해서 생성자를 만들어준다.
@NoArgsConstructor  // 빈 생성자
//@RequiredArgsConstructor  // 필드에 final이 붙은 대상만 생성자 파라미터로 만들어준다.
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;


    // Member.builder().username("dss").password("1234").email("dss@nate.com").build();
    // 다중 인자의 오버로딩 생성자가 필요없다.
    @Builder  // 생성자 파라미터를 순서에 상관없이 필요한 필드만 .getter명('dss') 형태로 파라미터를 지정하여 설정할수 있다. 미설정한 대상은 null처리됨
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
/*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
*/

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
