package com.classroom.assignment.service;

import com.classroom.assignment.entity.Archievement;
import java.util.List;

public interface ArchievementService {

  List<Archievement> selectAll();

  void insertArchievement(Archievement archievement);

}
