package com.rover.rover.dto;

import java.io.Serializable;


public class DownloadRoverDTO implements Serializable {
  private int sol;
  private String rover;
  private String cam;

  public DownloadRoverDTO() {}

  public int getSol() {
    return sol;
  }

  public void setSol(int sol) {
    this.sol = sol;
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
