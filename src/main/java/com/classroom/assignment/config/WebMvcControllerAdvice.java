package com.classroom.assignment.config;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.classroom.assignment.exception.NotDeleteException;
import com.classroom.assignment.exception.NotInsertException;
import com.classroom.assignment.exception.NotUpdateException;

@ControllerAdvice
public class WebMvcControllerAdvice {

  @ExceptionHandler(NotInsertException.class)
  public String handleException(NotInsertException e, Model model) {
    model.addAttribute("message", e);
    return "error/customError";
  }

  @ExceptionHandler(NotUpdateException.class)
  public String handleException(NotUpdateException e, Model model) {
    model.addAttribute("message", e);
    return "error/customError";
  }

  @ExceptionHandler(NotDeleteException.class)
  public String handleException(NotDeleteException e, Model model) {
    model.addAttribute("message", e);
    return "error/customError";
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public String handleException(EmptyResultDataAccessException e, Model model) {
    model.addAttribute("message", e);
    return "error/customError";
  }

}
