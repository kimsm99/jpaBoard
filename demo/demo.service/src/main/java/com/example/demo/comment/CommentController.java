package com.example.demo.comment;

import com.example.demo.entity.Comment;
import com.example.demo.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    @PostMapping("/insertComment")
    public String insertComment(Comment comment,
                                @RequestParam("boardId") Long boardId,
                                RedirectAttributes redirectAttributes) {
        commentService.saveComment(comment.getReply(), comment.getReplyWriter(), boardId);

        redirectAttributes.addAttribute("id", boardId);
        return "redirect:/getDetail";
    }


    @RequestMapping(value="/deleteComment")
    public String deleteComment(@RequestParam Long boardId,
                                @RequestParam Long id,
                                RedirectAttributes redirectAttributes) {
        commentRepository.deleteById(id);
        redirectAttributes.addAttribute("id", boardId);
        return "redirect:/getDetail";
    }
}
