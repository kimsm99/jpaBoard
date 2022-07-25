package com.example.demo;

import com.example.demo.comment.CommentController;
import com.example.demo.comment.CommentRepository;
import com.example.demo.comment.CommentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentController commentController;

    @Test
    @Order(1)
    @DisplayName("댓글 저장")
    void saveComment() {
        //give
        String reply = "댓글";
        String replyWriter = "작성자";
        Long boardId = 1L;
        //when
        commentService.saveComment(reply,replyWriter,boardId);
//        Comment commment = commentRepository.findById()
//        //then
//        assertEquals()
    }

}
