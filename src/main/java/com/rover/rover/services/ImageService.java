package com.rover.rover.services;

import com.rover.rover.client.RoverClient;
import com.rover.rover.dto.JsonRoverResponseDto;
import com.rover.rover.utils.Constants;
import org.springframework.stereotype.Service;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@Service
public class ImageService implements IImageService {
  private RoverClient roverClient;
  public ImageService(RoverClient roverClient) {
    this.roverClient = roverClient;
  }

  public String saveImages() {
    ArrayList<JsonRoverResponseDto> imgUrlList;
    ArrayList<RenderedImage> imgFiles;

    // Gets list of image locations
    try {
      imgUrlList = this.roverClient.getJsonResponse();
    } catch(Exception e) {
      System.out.println(e);
      return null;
    }

    // Convert to files
    try {
      imgFiles = this.getImagesFromUrls(imgUrlList);
    } catch(Exception e) {
      System.out.println(e);
      return null;
    }

    // compress and save files
    for(RenderedImage image : imgFiles) {
      try {
        compressImageAndSave(image, Constants.BASE_SAVE_LOCATION+"/"+Math.random());
      } catch(IOException e) {
        System.out.println(e);
      }
    }
    return "Test";
  }

  public void compressImageAndSave(RenderedImage image, String outputLocation) throws IOException {
    ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
    ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

    imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    imageWriteParam.setCompressionQuality(Constants.COMPRESSION_QUALITY);

    ImageOutputStream outputStream = ImageIO.createImageOutputStream(new File(outputLocation));
    imageWriter.setOutput(outputStream);

    IIOImage iioImage = new IIOImage(image, null, null);
    imageWriter.write(null, iioImage, imageWriteParam);

    imageWriter.dispose();
//    byte[] buffer = new byte[1024];
//    FileInputStream inputStream = new FileInputStream(image);
//    FileOutputStream outputStream = new FileOutputStream(outputLocation);
//    GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
//    int read;
//
//    while((read = inputStream.read(buffer)) != -1) {
//      gzipOutputStream.write(buffer, 0, read);
//    }
//    gzipOutputStream.finish();
//    gzipOutputStream.close();
//    outputStream.close();
//    inputStream.close();
  }

  private ArrayList<RenderedImage> getImagesFromUrls(ArrayList<JsonRoverResponseDto> jsonRoverResponseDtos) throws MalformedURLException, IOException {
    ArrayList<RenderedImage> images = new ArrayList<>();
    for(JsonRoverResponseDto j : jsonRoverResponseDtos) {
      URL url = new URL(j.getImageUrl());
      images.add(ImageIO.read(url));
    }
    return images;
  }
}
