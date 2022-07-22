package com.example.demo.comment;

import com.example.demo.post.Board;
import com.example.demo.post.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    @RequestMapping("/insertComment")
    public String insertComment(@RequestParam("reply") String reply,
                                @RequestParam("replyWriter") String replyWriter,
                                @RequestParam("boardId") Long boardId,
                                RedirectAttributes redirectAttributes) {
        commentService.saveComment(reply, replyWriter, boardId);

        redirectAttributes.addAttribute("id", boardId);
        return "redirect:/getDetail";
    }


    @RequestMapping(value="/deleteComment")
    public String deleteComment(@RequestParam Long boardId, @RequestParam Long id, RedirectAttributes redirectAttributes){
        commentRepository.deleteById(id);
        redirectAttributes.addAttribute("id", boardId);
        return "redirect:/getDetail";
    }
}
