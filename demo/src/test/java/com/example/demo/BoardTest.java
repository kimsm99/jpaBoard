package com.example.demo;

import com.example.demo.comment.CommentController;
import com.example.demo.comment.CommentRepository;
import com.example.demo.comment.CommentService;
import com.example.demo.post.Board;
import com.example.demo.post.BoardController;
import com.example.demo.post.BoardRepository;
import com.example.demo.post.BoardService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardController boardController;

    @Test
    @Order(1)
    @DisplayName("게시글 저장")
    void saveBoard() {
        //give
        String title = "제목";
        String content = "내용";
        String writer = "작성자";
        //when
        boardService.saveBoard(title,content,writer);
        //then
        Board board = boardRepository.findByWriter(writer).get(0);
        assertEquals(board.getTitle(),"제목");
        assertEquals(board.getContent(),"내용");
        assertEquals(board.getWriter(),"작성자");

    }

    @Test
    @Order(2)
    @DisplayName("게시글 저장")
    void updateBoard() {
        //give
        boardService.saveBoard("수정전제목","수정전내용","수정전작성자");
        String title = "제목";
        String content = "내용";
        Long id = 1L;
        //when
        boardService.updateBoard(id, title, content);
        //then
        Board board = boardRepository.findById(1L).orElseThrow(
                ()->new NullPointerException("게시글 없음")
        );
        assertEquals(board.getTitle(),"제목");
        assertEquals(board.getContent(),"내용");
        assertEquals(board.getWriter(),"작성자");

    }


}
