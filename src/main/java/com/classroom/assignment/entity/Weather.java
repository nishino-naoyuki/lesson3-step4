package com.classroom.assignment.entity;

public class Weather {
  private String city;

  private String forecast;

  private String image;

  public Weather() {}

  public Weather(String city, String forecast, String image) {
    this.city = city;
    this.forecast = forecast;
    this.image = image;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getForecast() {
    return forecast;
  }

  public void setForecast(String forecast) {
    this.forecast = forecast;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
