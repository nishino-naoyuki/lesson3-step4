package com.classroom.assignment.service;

import java.util.List;
import com.classroom.assignment.repository.LessonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.classroom.assignment.entity.Lesson;
import com.classroom.assignment.exception.NotDeleteException;
import com.classroom.assignment.exception.NotInsertException;
import com.classroom.assignment.exception.NotUpdateException;

@Service
public class LessonServiceImpl implements LessonService {

  private final LessonDao dao;

  @Autowired
  public LessonServiceImpl(LessonDao dao) {
    this.dao = dao;
  }

  @Override
  public List<Lesson> selectAll() {
    List<Lesson> list = dao.selectAll();
    return list;
  }

  @Override
  public List<Lesson> selectByDay(int day) {
    List<Lesson> list = dao.selectByDay(day);
    return list;
  }

  @Override
  public Lesson findLesson(int id) {
    return dao.findById(id);
  }

  @Override
  public void insertLesson(Lesson lesson) {
    int result = dao.insertLesson(lesson);
    if (result == 0) {
      throw new NotInsertException();
    }
  }

  @Override
  public void updateLesson(Lesson lesson) {
    int result = dao.updateLesson(lesson);
    if (result == 0) {
      throw new NotUpdateException();
    }
  }

  @Override
  public void delete(int id) {
    int result = dao.deleteLesson(id);
    if (result == 0) {
      throw new NotDeleteException();
    }
  }
}
