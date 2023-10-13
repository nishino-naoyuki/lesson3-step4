package com.classroom.assignment.model.request;

import javax.validation.constraints.NotEmpty;

public class ArchievementForm {

  @NotEmpty(message = "入力してください")
  private String name;

  private String memo;

  public ArchievementForm() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }
}
