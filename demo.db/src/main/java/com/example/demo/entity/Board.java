package com.example.demo.entity;

import com.example.demo.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
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
        this.writer = writer;
    }

    public void updateBoard(String title, String content){
        this.title = title;
        this.content = content;
    }

}
