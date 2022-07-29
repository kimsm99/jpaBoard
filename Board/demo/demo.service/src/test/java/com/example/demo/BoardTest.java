package com.example.demo;


import com.example.demo.entity.Board;
import com.example.demo.post.BoardController;

import com.example.demo.post.BoardService;
import com.example.demo.repo.BoardRepository;
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
    @DisplayName("게시글 수정")
    void updateBoard() {
        //give
        String title = "수정 후 제목";
        String content = "수정 후 내용";
        Long id = 1L;
        //when
        boardService.updateBoard(id, title, content);
        //then
        Board board = boardRepository.findById(1L).orElseThrow(
                ()->new NullPointerException("게시글 없음")
        );
        assertEquals(board.getTitle(),"수정 후 제목");
        assertEquals(board.getContent(),"수정 후 내용");
        assertEquals(board.getWriter(),"작성자");

    }

    @Nested
    @DisplayName("Bad Test")
    class wrong{
        @Test
        @Order(1)
        @DisplayName("저장- 제목 없음")
        void wrongSaveTitle() {
            //give
            String title = "";
            String content = "내용2";
            String writer = "작성자";
            //when
            try{
                boardService.saveBoard(title,content,writer);
            } catch (IllegalArgumentException e) {
                Assertions.assertEquals("게시글을 모두 작성해주세요", e.getMessage());
            }

        }

        @Test
        @Order(2)
        @DisplayName("저장- 내용 없음")
        void wrongSaveContent() {
            //give
            String title = "제목2";
            String content = "";
            String writer = "작성자";
            //when
            try{
                boardService.saveBoard(title,content,writer);
            } catch (IllegalArgumentException e) {
                Assertions.assertEquals("게시글을 모두 작성해주세요", e.getMessage());
            }

        }

        @Test
        @Order(3)
        @DisplayName("수정- 제목 없음")
        void wrongUpdateTitle() {
            //give
            String title = "수정 후 제목ㅠㅠ";
            String content = "";
            Long id = 1L;

            //when
            try{
                boardService.updateBoard(id, title, content);
            } catch (IllegalArgumentException e) {
                Assertions.assertEquals("게시글을 모두 작성해주세요", e.getMessage());
            }

        }

        @Test
        @Order(4)
        @DisplayName("수정- 내용 없음")
        void wrongUpdateContent() {
            //give
            String title = "";
            String content = "수정 후 내용 ㅠㅠ";
            Long id = 1L;
            //when


            //when
            try{
                boardService.updateBoard(id, title, content);
            } catch (IllegalArgumentException e) {
                Assertions.assertEquals("게시글을 모두 작성해주세요", e.getMessage());
            }

        }



    }
}





