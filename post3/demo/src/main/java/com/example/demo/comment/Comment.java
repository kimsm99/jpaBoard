package com.example.demo.comment;

import com.example.demo.post.Board;
import com.example.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
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
