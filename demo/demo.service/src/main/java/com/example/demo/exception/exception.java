package com.example.demo.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class exception {
    @ExceptionHandler(IllegalArgumentException.class)
    public String exeptionIllegalArgument(IllegalArgumentException ex, Model model) {
        model.addAttribute("exception",ex.getMessage());
        return "error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String exeptionNullPointerException(NullPointerException ex, Model model) {
        model.addAttribute("exception",ex.getMessage());
        return "error";
    }
}
