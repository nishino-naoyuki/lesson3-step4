package com.classroom.assignment.repository;

import java.util.List;
import com.classroom.assignment.entity.Archievement;

public interface ArchievementDao {

  List<Archievement> selectAll();

  int insertArchievement(Archievement archievement);

}
