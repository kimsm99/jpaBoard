package com.example.demo;


import com.example.demo.comment.CommentController;

import com.example.demo.comment.CommentService;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.post.BoardService;
import com.example.demo.repo.BoardRepository;
import com.example.demo.repo.CommentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentController commentController;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Order(1)
    @DisplayName("댓글 저장")
    void saveComment() {
        //give
        Board board = new Board("게시글 제목","게시글 내용","게시글 작성자");
        boardService.saveBoard(board.getTitle(), board.getTitle(), board.getWriter());
        Board savedBoard = boardRepository.findAll().get(0);
        System.out.println(savedBoard.getId());
        String reply = "댓글";
        String replyWriter = "작성자";

        //when
        commentService.saveComment(reply,replyWriter, savedBoard.getId());
        Comment comment = commentRepository.findAll().get(0);

//        //then
        assertEquals(comment.getReply(),"댓글");
        assertEquals(comment.getReplyWriter(),"작성자");
        assertEquals(comment.getBoard().getId(),savedBoard.getId());
    }

    @Nested
    @DisplayName("Bad Test")
    class wrong{
        @Test
        @Order(1)
        @DisplayName("저장- 제목 없음")
        void wrongSaveReply() {
            //give
            String reply = "";
            String replyWriter = "작성자";
            //when
            try{
                commentService.saveComment(reply,replyWriter, 1L);
            } catch (IllegalArgumentException e) {
                Assertions.assertEquals("댓글을 작성해주세요", e.getMessage());
            }

        }


    }

}
