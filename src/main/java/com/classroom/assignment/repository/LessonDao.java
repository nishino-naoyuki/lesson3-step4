package com.classroom.assignment.repository;

import java.util.List;
import com.classroom.assignment.entity.Lesson;

public interface LessonDao {

  List<Lesson> selectAll();

  List<Lesson> selectByDay(int day);

  Lesson findById(int id);

  int insertLesson(Lesson Lesson);

  int updateLesson(Lesson lesson);

  int deleteLesson(int id);


}
