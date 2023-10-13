package com.classroom.assignment.entity;

public class Lesson {
  private int id;
  private String name;
  private int day;
  private String dayName;
  private int period;
  private String teacher;

  public Lesson() {}

  public Lesson(int id, String name, int day, int period, String teacher) {
    super();
    this.id = id;
    this.name = name;
    this.day = day;
    this.period = period;
    this.teacher = teacher;
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


  public String getDayName() {
    return dayName;
  }

  public void setDayName(String dayName) {
    this.dayName = dayName;
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
