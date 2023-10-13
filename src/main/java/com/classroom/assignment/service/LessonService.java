package com.classroom.assignment.service;

import com.classroom.assignment.entity.Lesson;
import java.util.List;

public interface LessonService {

  List<Lesson> selectAll();

  List<Lesson> selectByDay(int day);

  Lesson findLesson(int id);

  void insertLesson(Lesson Lesson);

  void updateLesson(Lesson Lesson);

  void delete(int deleteId);
}
