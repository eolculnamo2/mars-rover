package com.rover.rover.services;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.ArrayList;

public interface IImageService {
  String saveImages();
  void compressImageAndSave(ArrayList<RenderedImage> imgList) throws IOException;
}
