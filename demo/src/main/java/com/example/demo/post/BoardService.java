package com.example.demo.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    @Transactional
    public void updateBoard(Long id,String title,String content){
        if(title==""&&content=="") throw new NullPointerException("빈칸 있음");

        Board board = boardRepository.findById(id).orElseThrow(
                ()->new NullPointerException("게시글 없음")
        );
        board.updateBoard(title, content);
    }
    @Transactional
    public void saveBoard(String title, String content, String writer){
        if(title==""&&content=="") throw new NullPointerException("빈칸 있음");

        Board board = new Board(title, content, writer);
        boardRepository.save(board);
    }




}
