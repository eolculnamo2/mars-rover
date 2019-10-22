package com.rover.rover.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rover.rover.dto.DownloadRoverDTO;
import com.rover.rover.dto.RetrievePhotoDTO;
import com.rover.rover.services.ImageService;
import com.rover.rover.utils.Helpers;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rovers")
public class RoverApiController {
  private ImageService imageService;
  private ObjectMapper objectMapper;

  public RoverApiController(ImageService imageService, ObjectMapper objectMapper) {
    this.imageService = imageService;
    this.objectMapper = objectMapper;
  }

  @GetMapping(
      value="/get-rover",
      produces = MediaType.IMAGE_JPEG_VALUE
  )
  public byte[] getRovers(HttpServletResponse response, RetrievePhotoDTO retrievePhotoDTO) {
    byte[] image;
    try {
      image = this.imageService.readImages(retrievePhotoDTO);

    } catch(Exception e) {
      System.out.println(e);
      return null;
    }

    if(image == null) {
      response.setStatus(500);
    }

    return image;
  }

  @PostMapping("/save-rovers")
  public String saveRovers(HttpServletRequest request, HttpServletResponse response) {
    try {
      String payloadString = Helpers.convertJson(request.getInputStream());
      DownloadRoverDTO downloadRoverDTO = objectMapper.readValue(payloadString, DownloadRoverDTO.class);
      this.imageService.saveImages(downloadRoverDTO);
    } catch( Exception e ) {
      System.out.println(e);
      response.setStatus(500);
    }

    return "success";
  }
}