package com.rover.rover.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rover.rover.dto.DownloadRoverDTO;
import com.rover.rover.dto.RetrievePhotoDTO;
import com.rover.rover.services.ImageService;
import com.rover.rover.utils.Helpers;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
  public byte[] getRovers(RetrievePhotoDTO retrievePhotoDTO) {
    byte[] response;
    try {
      response = this.imageService.readImages(retrievePhotoDTO);

    } catch(Exception e) {
      System.out.println(e);
      return null;
    }

    return response;
  }

  @PostMapping("/save-rovers")
  public String saveRovers(HttpServletRequest request) {
    try {
      String payloadString = Helpers.convertJson(request.getInputStream());
      DownloadRoverDTO downloadRoverDTO = objectMapper.readValue(payloadString, DownloadRoverDTO.class);
      this.imageService.saveImages(downloadRoverDTO);
    } catch( Exception e ) {
      System.out.println(e);
    }
    return "test route";
  }
}