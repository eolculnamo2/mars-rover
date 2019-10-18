package com.rover.rover.services;

import java.io.File;
import java.io.IOException;

public interface IImageService {
  void saveImages();
  void compressImage(File image, String outputLocation) throws IOException;
}
