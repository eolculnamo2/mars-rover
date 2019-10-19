package com.rover.rover.controllers;

import com.rover.rover.client.RoverClient;
import com.rover.rover.services.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    File imgFile = new File("/Users/rbertram/Desktop/family.jpeg");
//
//    try {
//      this.imageService.compressImage(imgFile, Constants.BASE_SAVE_LOCATION);
//    } catch(IOException e) {
//      System.out.println(e);
//    }
    return "testing api";
  }

  //TODO make post request
  @GetMapping("/save-rovers")
  public String saveRovers() {
    this.imageService.saveImages();
    return "test route";
  }
}