package com.example.demo.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    @Transactional
    public void updateBoard(Long id,String title,String content){
        Board board = boardRepository.findById(id).orElseThrow(
                ()->new NullPointerException("게시글 없음")
        );
        board.updateBoard(title, content);
    }

}
