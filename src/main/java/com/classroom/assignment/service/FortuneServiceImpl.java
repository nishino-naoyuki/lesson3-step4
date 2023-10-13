package com.classroom.assignment.service;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class FortuneServiceImpl implements FortuneService {

  @Override
  public String omikuji() {
    /**
     * level 2-1 おみくじの表示
     */
    Random rand = new Random();
    int no = rand.nextInt(5);
    String omikuji = "";

    switch (no) {
      case 0:
        omikuji = "大吉";
        break;
      case 1:
        omikuji = "中吉";
        break;
      case 2:
        omikuji = "小吉";
        break;
      case 3:
        omikuji = "末吉";
        break;
      case 4:
        omikuji = "凶";
    }
    return omikuji;
  }

  @Override
  public String omikujiIcon(String omikuji) {
    String omikujiIcon = "";

    switch (omikuji) {
      case "大吉":
        omikujiIcon = "emoji-laughing";
        break;
      case "中吉":
        omikujiIcon = "emoji-smile";
        break;
      case "小吉":
        omikujiIcon = "emoji-neutral";
        break;
      case "末吉":
        omikujiIcon = "emoji-expressionless";
        break;
      case "凶":
        omikujiIcon = "emoji-dizzy";
    }
    return omikujiIcon;
  }
}
