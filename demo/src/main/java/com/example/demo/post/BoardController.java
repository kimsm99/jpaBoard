
package com.example.demo.post;


        import lombok.RequiredArgsConstructor;
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
    @RequestMapping("/insertBoard")
    public String insertBoard(@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("writer") String writer) {
        Board board = new Board(title, content, writer);
        boardRepository.save(board);
        return "redirect:/boardView";
    }

    //게시글 전체 보기
    @RequestMapping("/boardView")
    public String getBoardList(Model model) {
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        model.addAttribute("boardList", boardList);
        return "board";

    }

    //상세조회
    @RequestMapping("/getDetail")
    public String getBoard(@RequestParam Long id, Model model) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글 존재 안함")
        );

        model.addAttribute("board", board);
        return "detail";
    }

    //삭제//왜 method delete 하면 안될까...
    @RequestMapping(value="/deleteBoard")
    public String deleteBoard(@RequestParam Long id){
        boardRepository.deleteById(id);
        return "redirect:/boardView";
    }

    //수정 페이지 이동
    @RequestMapping(value="/updateBoardView")
    public ModelAndView updateBoardView(@RequestParam Long id){
        System.out.println("id"+id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("insertBoard");
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시글 존재 안함")
        );
        mv.addObject("board",board);
        System.out.println(board.getContent()+board.getTitle()+board.getId());

        return mv;}

    //수정
    @RequestMapping(value="/updateBoard")
    public String updateBoard(@RequestParam Long id, @RequestParam("title") String title, @RequestParam("content") String content){
        boardService.updateBoard(id, title, content);
        return "redirect:/boardView";
    }



}
