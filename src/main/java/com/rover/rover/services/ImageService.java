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
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    try {
      this.compressImageAndSave(imgFiles);
    } catch(Exception e) {
      System.out.println(e);
    }
    return "Test";
  }

  public byte[] readImages() throws IOException {
    //https://stackoverflow.com/questions/20987392/how-to-pass-images-into-arrayliststring-in-android/20987471#20987471
    File image = new File(Constants.BASE_SAVE_LOCATION+"/2019-10-19/image_0.jpg");
    return Files.readAllBytes(image.toPath());
  }

  public void compressImageAndSave(ArrayList<RenderedImage> imgFiles) throws IOException {
    ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
    ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

    imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    imageWriteParam.setCompressionQuality(Constants.COMPRESSION_QUALITY);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String folderName = simpleDateFormat.format(new Date());
    new File(Constants.BASE_SAVE_LOCATION+"/"+folderName).mkdirs();
    for(int i = 0; i < imgFiles.size(); i++) {
      RenderedImage image = imgFiles.get(i);
      ImageOutputStream outputStream = ImageIO.createImageOutputStream(new File(Constants.BASE_SAVE_LOCATION+"/"+ folderName + "/image_" + i+".jpg"));
      imageWriter.setOutput(outputStream);

      IIOImage iioImage = new IIOImage(image, null, null);
      imageWriter.write(null, iioImage, imageWriteParam);
    }

    imageWriter.dispose();
  }

  private ArrayList<RenderedImage> getImagesFromUrls(ArrayList<JsonRoverResponseDto> jsonRoverResponseDtos) throws MalformedURLException, IOException {
    ArrayList<RenderedImage> images = new ArrayList<>();
    for(int i = 0; i < jsonRoverResponseDtos.size(); i++) {
      // limit max images to 20
      if(i > 19) break;

      JsonRoverResponseDto j = jsonRoverResponseDtos.get(i);
      URL url = new URL(j.getImageUrl());
      images.add(ImageIO.read(url));
    }
    return images;
  }
}
