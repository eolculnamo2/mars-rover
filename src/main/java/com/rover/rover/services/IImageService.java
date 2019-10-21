package com.rover.rover.services;

import com.rover.rover.dto.DownloadRoverDTO;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.ArrayList;

public interface IImageService {
  String saveImages(DownloadRoverDTO downloadRoverDTO);
  void compressImageAndSave(ArrayList<RenderedImage> imgList, String folderName) throws IOException;
}
