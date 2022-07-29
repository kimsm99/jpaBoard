package com.example.demo.comment;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.repo.BoardRepository;
import com.example.demo.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
@RequiredArgsConstructor
@Service
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    @Transactional
    public void saveComment(String reply,
                            String replyWriter,
                            Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new NullPointerException("게시글이 없습니다.")
        );

        if(reply=="") throw new IllegalArgumentException("댓글을 작성해주세요");

        Comment comment = new Comment(reply,replyWriter,board);
        commentRepository.save(comment);
    }
}
