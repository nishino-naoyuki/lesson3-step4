package com.classroom.assignment.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.classroom.assignment.entity.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherServiceImpl implements WeatherService {

  @Override
  public Weather findWeather() {
    RestTemplate rest = new RestTemplate();
    try {
      /**
       * level 1-2 天気取得都市の変更
       *
       * https://weather.tsukumijima.net/ を参考に自分の住んでいる都市のcity codeをに変更する
       */
      final String cityCode = "130010"; // 東京のCityCode
      final String endpoint = "https://weather.tsukumijima.net/api/forecast";

      final String url = endpoint + "?city=" + cityCode;

      ResponseEntity<String> response = rest.getForEntity(url, String.class);
      String json = response.getBody();

      ObjectMapper objectMapper = new ObjectMapper();

      JsonNode jsonNode = objectMapper.readTree(json);
      Weather weather = new Weather();
      weather.setCity(jsonNode.get("location").get("city").textValue());
      weather.setForecast(jsonNode.get("forecasts").elements().next().get("telop").textValue());
      weather
          .setImage(
              jsonNode.get("forecasts").elements().next().get("image").get("url").textValue());
      return weather;

    } catch (RestClientException e) {
      e.printStackTrace();
      return new Weather("", "天気が取得できませんでした。", "");
    } catch (JsonMappingException e) {
      e.printStackTrace();
      return new Weather("", "天気が取得できませんでした。", "");
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return new Weather("", "天気が取得できませんでした。", "");
    }
  }
}
