package com.rover.rover.dto;

import java.io.Serializable;

public class JsonRoverResponseDto implements Serializable {
  private String imageUrl;

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
