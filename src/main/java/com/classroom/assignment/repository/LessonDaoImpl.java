package com.classroom.assignment.repository;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.classroom.assignment.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LessonDaoImpl implements LessonDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public LessonDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Lesson> selectAll() {
    String sql = "SELECT id, name, day, period, teacher FROM LESSON";
    List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
    List<Lesson> list = new ArrayList<Lesson>();
    for (Map<String, Object> result : resultList) {
      Lesson lesson = new Lesson();
      lesson.setId((int) result.get("id"));
      lesson.setName((String) result.get("name"));
      lesson.setPeriod((int) result.get("period"));
      lesson.setTeacher(((String) result.get("teacher")));

      int day = (int) result.get("day");
      lesson.setDay(day);
      // 曜日番号 → 曜日名 変換
      lesson.setDayName(DayOfWeek.of(day).getDisplayName(TextStyle.SHORT, Locale.JAPAN));

      list.add(lesson);
    }

    return list;
  }

  @Override
  public List<Lesson> selectByDay(int selectDay) {
    /**
     * level 2-3 本日の授業表示
     *
     * SQL文の修正
     */
    String sql = "SELECT id, name, day, period, teacher FROM lesson WHERE day = ?";
    List<Map<String, Object>> resultList = jdbcTemplate
        .queryForList(sql, selectDay);
    List<Lesson> list = new ArrayList<Lesson>();
    for (Map<String, Object> result : resultList) {
      Lesson lesson = new Lesson();
      lesson.setId((int) result.get("id"));
      lesson.setName((String) result.get("name"));
      lesson.setPeriod((int) result.get("period"));
      lesson.setTeacher(((String) result.get("teacher")));

      int day = (int) result.get("day");
      lesson.setDay(day);
      // 曜日番号 → 曜日名 変換
      lesson.setDayName(DayOfWeek.of(day).getDisplayName(TextStyle.SHORT, Locale.JAPAN));

      list.add(lesson);
    }

    return list;
  }

  @Override
  public Lesson findById(int id) {
    String sql = "SELECT id, name, day, period, teacher FROM LESSON WHERE id = ?";
    Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
    Lesson lesson = new Lesson();
    lesson.setId((int) result.get("id"));
    lesson.setName((String) result.get("name"));
    lesson.setDay((int) result.get("day"));
    lesson.setPeriod((int) result.get("period"));
    lesson.setTeacher((String) result.get("teacher"));
    return lesson;
  }

  @Override
  public int insertLesson(Lesson lesson) {
    String sql = "INSERT INTO LESSON (name, day, period, teacher) VALUES(?, ?, ?, ?)";
    return jdbcTemplate.update(sql,
        lesson.getName(), lesson.getDay(), lesson.getPeriod(), lesson.getTeacher());
  }

  @Override
  public int updateLesson(Lesson lesson) {
    String sql = "UPDATE LESSON SET name = ?, day = ?, period = ?, teacher = ? WHERE id = ?";
    return jdbcTemplate.update(sql,
        lesson.getName(), lesson.getDay(), lesson.getPeriod(), lesson.getTeacher(), lesson.getId());
  }

  @Override
  public int deleteLesson(int id) {
    return jdbcTemplate.update("DELETE FROM LESSON WHERE id = ?", id);
  }
}
