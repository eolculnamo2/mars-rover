package com.rover.rover.controllers;

import com.rover.rover.client.RoverClient;
import com.rover.rover.services.ImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rovers")
public class RoverApiController {
  private ImageService imageService;

  public RoverApiController(ImageService imageService, RoverClient roverClient) {
    this.imageService = imageService;
  }

  @GetMapping(
      value="/rovers",
      produces = MediaType.IMAGE_JPEG_VALUE
  )
  public byte[] getRovers() {
    byte[] response;

    try {
      response = this.imageService.readImages();

    } catch(Exception e) {
      System.out.println(e);
      return null;
    }

    return response;
  }

  //TODO make post request
  @GetMapping("/save-rovers")
  public String saveRovers() {
    this.imageService.saveImages();
    return "test route";
  }
}