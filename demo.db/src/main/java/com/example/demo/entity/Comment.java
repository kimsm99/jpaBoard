package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String reply;

    @Column
    private String replyWriter;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Board board;

    public Comment(String reply, String replyWriter, Board board) {
        this.reply=reply;
        this.replyWriter=replyWriter;
        this.board=board;
    }
}
