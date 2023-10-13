package com.classroom.assignment.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LessonForm {

  private int id;

  @NotEmpty(message = "入力してください")
  @Size(min = 1, max = 20, message = "20文字以内で入力してください")
  private String name;

  @Min(value = 1, message = "選択してください")
  private int day;

  @Min(value = 1, message = "選択してください")
  private int period;

  @NotEmpty(message = "入力してください")
  private String teacher;

  public LessonForm() {}

  public LessonForm(String name, int day, int period, String teacher) {
    this.setName(name);
    this.setDay(day);
    this.setPeriod(period);
    this.setTeacher(teacher);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getPeriod() {
    return period;
  }

  public void setPeriod(int period) {
    this.period = period;
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }

}
