package com.classroom.assignment.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.classroom.assignment.entity.Lesson;
import com.classroom.assignment.model.request.LessonForm;
import com.classroom.assignment.service.LessonService;

@Controller
@RequestMapping("/lesson")
public class LessonController {

  private final LessonService lessonService;

  @Autowired
  public LessonController(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @GetMapping
  public String index(Model model) {
    List<Lesson> list = lessonService.selectAll();

    model.addAttribute("lessonList", list);
    return "lesson/index";
  }

  @GetMapping("/new")
  public String regist(LessonForm lessonForm, Model model) {
    model.addAttribute("lessonForm", new LessonForm());
    return "lesson/new";
  }

  @PostMapping("/insert")
  public String insert(@Validated @ModelAttribute("lessonForm") LessonForm lessonForm,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("lessonForm", lessonForm);
      return "lesson/new";
    }
    Lesson lesson = new Lesson();
    lesson.setName(lessonForm.getName());
    lesson.setDay(lessonForm.getDay());
    lesson.setPeriod(lessonForm.getPeriod());
    lesson.setTeacher(lessonForm.getTeacher());

    lessonService.insertLesson(lesson);
    return "redirect:/lesson";
  }

  @GetMapping("/detail/{id}")
  public String detail(@PathVariable("id") int id, Model model) {
    Lesson lesson = lessonService.findLesson(id);
    LessonForm lessonForm = new LessonForm();
    lessonForm.setId(id);
    lessonForm.setName(lesson.getName());
    lessonForm.setDay(lesson.getDay());
    lessonForm.setPeriod(lesson.getPeriod());
    lessonForm.setTeacher(lesson.getTeacher());
    model.addAttribute("lessonForm", lessonForm);
    return "lesson/update";
  }

  @PostMapping("/update")
  public String update(@Validated @ModelAttribute("lessonForm") LessonForm lessonForm,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("lessonForm", lessonForm);
      return "lesson/update";
    }
    Lesson lesson = new Lesson();
    lesson.setId(lessonForm.getId());
    lesson.setName(lessonForm.getName());
    lesson.setDay(lessonForm.getDay());
    lesson.setPeriod(lessonForm.getPeriod());
    lesson.setTeacher(lessonForm.getTeacher());

    lessonService.updateLesson(lesson);
    return "redirect:/lesson";
  }

  @GetMapping("/delete")
  public String delete(@RequestParam("id") int id, Model model) {
    lessonService.delete(id);

    return "redirect:/lesson";
  }
}
