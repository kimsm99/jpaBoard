package com.example.demo.post;

import com.example.demo.comment.Comment;
import com.example.demo.timestamped.Timestamped;
import com.example.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String writer;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    public Board(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer=writer;
    }

    public void updateBoard(String title, String content){
        this.title=title;
        this.content=content;
    }

}
