package com.example.demo.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    //pk, 이름,비밀번호, id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String userName;

    @Column
    private String nickName;//유저id

    @Column
    private String password;

    public User(String username, String nickName, String password) {
        this.userName=username;
        this.nickName=nickName;
        this.password=password;
    }
}
