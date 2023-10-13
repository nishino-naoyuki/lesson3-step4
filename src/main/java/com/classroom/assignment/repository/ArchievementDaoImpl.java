package com.classroom.assignment.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.classroom.assignment.entity.Archievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArchievementDaoImpl implements ArchievementDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ArchievementDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Archievement> selectAll() {
    String sql = "SELECT id, name, memo FROM ARCHIEVEMENT";
    List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
    List<Archievement> list = new ArrayList<Archievement>();
    for (Map<String, Object> result : resultList) {
      Archievement archievement = new Archievement();
      archievement.setId((int) result.get("id"));
      archievement.setName((String) result.get("name"));
      archievement.setMemo((String) result.get("memo"));

      list.add(archievement);
    }

    return list;
  }

  @Override
  public int insertArchievement(Archievement archievement) {
    String sql = "INSERT INTO ARCHIEVEMENT (name, memo) VALUES(?, ?)";
    return jdbcTemplate.update(sql,
        archievement.getName(), archievement.getMemo());
  }

}
