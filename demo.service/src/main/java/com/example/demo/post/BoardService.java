package com.example.demo.post;

import com.example.demo.entity.Board;
import com.example.demo.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    @Transactional
    public void updateBoard(Long id,String title,String content) {
        if(title==""||content=="") throw new IllegalArgumentException("게시글을 모두 작성해주세요");

        Board board = boardRepository.findById(id).orElseThrow(
                ()->new NullPointerException("게시글 없음")
        );
        board.updateBoard(title, content);
    }
    @Transactional
    public void saveBoard(String title, String content, String writer) {
        if(title==""||content=="") throw new IllegalArgumentException("게시글을 모두 작성해주세요");

        Board board = new Board(title, content, writer);
        boardRepository.save(board);
    }



//    public Page<Board> getBoardPage(int page) {
//        Pageable pageable = PageRequest.of(page-1, 10);
//        return boardRepository.findAll(pageable);
//
//    }
}
