
package com.example.demo.post;


import com.example.demo.entity.Board;
import com.example.demo.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    //글쓰기 페이지 이동
    @RequestMapping("/insertBoardView")
    public String insertBoardView() {
        return "insertBoard";
    }



    //글 등록
    @PostMapping("/insertBoard")
    public String insertBoard(Board board) {
        boardService.saveBoard(board.getTitle(), board.getContent(), board.getWriter());
        return "redirect:/boardView";
    }



//    //페이징하기
//    //게시글 전체 보기
//    @GetMapping(value ="/boardView")
//    public String getBoardList(@RequestParam String searchWord, Model model) {
//
//        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
////        Page<Board> boardList = boardService.getBoardPage(page);
//        model.addAttribute("boardList", boardList);
//        return "board";
//
//
//    }


//            //페이징하기
//        //게시글 전체 보기
//        @GetMapping(value ="/boardView")
//        public String getBoardList(@RequestParam(defaultValue = "") String searchWord, Model model) {
//            List<Board> boardList;
//            if(searchWord.equals("")||searchWord.equals(null)){
//                boardList = boardRepository.findAllByOrderByModifiedAtDesc();
//            } else {
//                boardList = boardRepository.findAllSearch(searchWord);
//            }
//
////        Page<Board> boardList = boardService.getBoardPage(page);
//
//            model.addAttribute("boardList", boardList);
//            return "board";
//        }

                //페이징하기
        //게시글 전체 보기
        @GetMapping(value ="/boardView")
        public String getBoardList(@RequestParam(defaultValue = "") String searchWord, Model model, @RequestParam(defaultValue = "1") int page) {
            Pageable pageable = PageRequest.of(page-1, 10);
            Page<Board> boardList;
            if(searchWord.equals("")||searchWord.equals(null)){
                boardList = boardRepository.findAllByOrderByModifiedAtDesc(pageable);
            } else {
                boardList = boardRepository.findAllSearch(searchWord, pageable);
            }

            model.addAttribute("boardList", boardList);
            return "board";
        }



    //상세조회
    @GetMapping("/getDetail")
    public String getBoard(@RequestParam Long id, Model model) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글 존재 안함")
        );

        model.addAttribute("board", board);
        return "detail";
    }

    //삭제//왜 method delete 하면 안될까...
    @RequestMapping(value="/deleteBoard")
    public String deleteBoard(@RequestParam Long id) {
        boardRepository.deleteById(id);
        return "redirect:/boardView";
    }

    //수정 페이지 이동
    @RequestMapping(value="/updateBoardView")
    public ModelAndView updateBoardView(@RequestParam Long id) {
        System.out.println("id"+id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("insertBoard");
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글 존재 안함")
        );
        mv.addObject("board",board);
        System.out.println(board.getContent()+board.getTitle()+board.getId());

        return mv;
    }

    //수정
    @RequestMapping(value="/updateBoard")

    public String updateBoard(Board board) {
        boardService.updateBoard(board.getId(), board.getTitle(), board.getContent());
        return "redirect:/boardView";
    }

    //검색 기능



}
