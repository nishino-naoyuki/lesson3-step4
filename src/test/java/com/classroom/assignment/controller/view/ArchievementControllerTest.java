package com.classroom.assignment.controller.view;

import com.classroom.assignment.controller.ArchievementController;
import com.classroom.assignment.entity.Archievement;
import com.classroom.assignment.service.ArchievementService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ArchievementControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ArchievementController controller;
  @Autowired
  private ArchievementService service;

  @Test
  void contextLoads() {
    // given
    // when
    // then
    assertThat(controller).isNotNull();
  }

  @Test
  public void shouldUpdateArchievements() throws Exception {
    // given
    Archievement before = service.findArchievement(1);
    // when
    // then
    this.mockMvc
        .perform(
            post("/archievement/update").param("id", "1").param("name", "変更").param("memo", "変更"))
        .andDo(print())
        .andExpect(status().isFound());

    Archievement after = service.findArchievement(1);

    assertNotEquals(before, after);
  }
}
