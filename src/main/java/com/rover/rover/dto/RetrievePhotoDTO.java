package com.rover.rover.dto;

import java.io.Serializable;

public class RetrievePhotoDTO implements Serializable {
  private String index;
  private String sol;
  private String rover;
  private String cam;

  public String getSol() {
    return sol;
  }

  public void setSol(String sol) {
    this.sol = sol;
  }

  public String getIndex() {
    return index;
  }

  public void setIndex(String index) {
    this.index = index;
  }

  public String getRover() {
    return rover;
  }

  public void setRover(String rover) {
    this.rover = rover;
  }

  public String getCam() {
    return cam;
  }

  public void setCam(String cam) {
    this.cam = cam;
  }
}
