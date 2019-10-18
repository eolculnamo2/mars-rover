package com.rover.rover.services;

import com.rover.rover.client.RoverClient;
import com.rover.rover.utils.Constants;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@Service
public class ImageService implements IImageService {
  private RoverClient roverClient;
  public ImageService(RoverClient roverClient) {
    this.roverClient = roverClient;
  }

  public void saveImages() {
    List<File> images = new ArrayList<>();

    for(File image : images) {
      try {
        compressImage(image, Constants.BASE_SAVE_LOCATION);
      } catch(IOException e) {
        System.out.println(e);
      }
    }
  }

  public void compressImage(File image, String outputLocation) throws IOException {
    byte[] buffer = new byte[1024];
    FileInputStream inputStream = new FileInputStream(image);
    FileOutputStream outputStream = new FileOutputStream(outputLocation);
    GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
    int read;

    while((read = inputStream.read(buffer)) != -1) {
      gzipOutputStream.write(buffer, 0, read);
    }
    gzipOutputStream.finish();
    gzipOutputStream.close();
    outputStream.close();
    inputStream.close();
  }
}
