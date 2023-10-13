package com.classroom.assignment.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

  @Override
  public String greeting(LocalDateTime time) {
    String greeting = "";
    int hour = time.getHour();

    /**
     * level 1-2 挨拶の表示
     */
    if (hour >= 5 && hour < 12) {
      greeting = "Good Morning";
    } else if (hour >= 12 && hour < 18) {
      greeting = "Good Afternoon";
    } else if (hour >= 18 || hour < 5) {
      greeting = "Good Evening";
    }

    return greeting;
  }
}
