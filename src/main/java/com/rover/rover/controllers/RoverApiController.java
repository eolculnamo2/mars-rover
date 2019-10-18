package com.rover.rover.controllers;

import com.rover.rover.client.RoverClient;
import com.rover.rover.services.ImageService;
import com.rover.rover.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/rovers")
public class RoverApiController {
  private ImageService imageService;
  private RoverClient roverClient;

  public RoverApiController(ImageService imageService, RoverClient roverClient) {
    this.imageService = imageService;
    this.roverClient = roverClient;
  }

  @GetMapping("/rovers")
  public String getRovers() {
    File imgFile = new File("/Users/rbertram/Desktop/family.jpeg");

    try {
      this.imageService.compressImage(imgFile, Constants.BASE_SAVE_LOCATION);
    } catch(IOException e) {
      System.out.println(e);
    }
    return "testing api";
  }
}