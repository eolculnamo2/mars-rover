package com.rover.rover.services;

import java.awt.image.RenderedImage;
import java.io.IOException;

public interface IImageService {
  String saveImages();
  void compressImageAndSave(RenderedImage image, String outputLocation) throws IOException;
}
