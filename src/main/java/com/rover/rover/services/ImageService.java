package com.rover.rover.services;

import com.rover.rover.client.RoverClient;
import com.rover.rover.dto.DownloadRoverDTO;
import com.rover.rover.dto.JsonRoverResponseDTO;
import com.rover.rover.dto.RetrievePhotoDTO;
import com.rover.rover.utils.Constants;
import com.rover.rover.utils.Helpers;
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
import java.util.ArrayList;
import java.util.Base64;

@Service
public class ImageService implements IImageService {

  public String saveImages(DownloadRoverDTO downloadRoverDTO) {
    ArrayList<JsonRoverResponseDTO> imgUrlList;
    ArrayList<RenderedImage> imgFiles;
    RoverClient roverClient = new RoverClient(downloadRoverDTO);
    // Gets list of image locations
    try {
      imgUrlList = roverClient.getJsonResponse();
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
      String folderName = Helpers.getPathFromRoverDetails(downloadRoverDTO.getCam(), downloadRoverDTO.getRover(), Integer.toString(downloadRoverDTO.getSol()));
      this.compressImageAndSave(imgFiles, folderName);
    } catch(Exception e) {
      System.out.println(e);
    }
    return "Success";
  }

  public byte[] readImages(RetrievePhotoDTO retrievePhotoDTO) throws IOException {
    String folderName = Helpers.getPathFromRoverDetails(retrievePhotoDTO.getCam(), retrievePhotoDTO.getRover(), retrievePhotoDTO.getSol());
    File image = new File(Constants.BASE_SAVE_LOCATION + "/" + folderName + "/image_" + retrievePhotoDTO.getIndex() + ".jpg");

    return Base64.getEncoder().encode(Files.readAllBytes(image.toPath()));
  }

  public void compressImageAndSave(ArrayList<RenderedImage> imgFiles, String folderName) throws IOException {
    ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
    ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

    imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    imageWriteParam.setCompressionQuality(Constants.COMPRESSION_QUALITY);

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

  private ArrayList<RenderedImage> getImagesFromUrls(ArrayList<JsonRoverResponseDTO> jsonRoverResponseDTOS) throws MalformedURLException, IOException {
    ArrayList<RenderedImage> images = new ArrayList<>();
    for(int i = 0; i < jsonRoverResponseDTOS.size(); i++) {
      // limit max images to 20
      if(i > 19) break;

      JsonRoverResponseDTO j = jsonRoverResponseDTOS.get(i);
      URL url = new URL(j.getImageUrl());

      // http protocol causing null images
      if(!url.getProtocol().equals("https")) {
        url = new URL("https://"+url.getHost()+url.getPath());
      }

      RenderedImage renderedImage = ImageIO.read(url);
      images.add(ImageIO.read(url));
    }
    return images;
  }
}
