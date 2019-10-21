package com.rover.rover.client;

import com.rover.rover.dto.DownloadRoverDTO;
import com.rover.rover.dto.JsonRoverResponseDTO;
import com.rover.rover.utils.Constants;
import com.rover.rover.utils.Helpers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Service
public class RoverClient {
  private HttpURLConnection connection;

  public RoverClient() {}
  public RoverClient(DownloadRoverDTO downloadRoverDTO) {
    try {
      String url = Constants.API_URL + downloadRoverDTO.getRover() + "/photos?sol=" + downloadRoverDTO.getSol();

      if(downloadRoverDTO.getCam() != null && !downloadRoverDTO.getCam().equals("all")) {
        url += "&camera=" + downloadRoverDTO.getCam();
      }

      url += "&api_key=" + Constants.API_KEY;

      this.connection = (HttpURLConnection) new URL(url).openConnection();
    } catch(Exception e) {
      System.out.println(e);
    }
  }

  public ArrayList<JsonRoverResponseDTO> getJsonResponse() throws IOException, JSONException {
    connection.setRequestMethod("GET");
    connection.connect();

    String jsonString = Helpers.convertJson(connection.getInputStream());
    JSONObject jsonResponse = new JSONObject(jsonString);
    JSONArray jsonResponseArr = jsonResponse.getJSONArray("photos");


    ArrayList<JsonRoverResponseDTO> roverImgUrls = new ArrayList<>();
    for(int i = 0; i < jsonResponseArr.length(); i++) {
      JsonRoverResponseDTO resObject = new JsonRoverResponseDTO();
      if(jsonResponseArr.getJSONObject(i) != null) {
        resObject.setImageUrl(jsonResponseArr.getJSONObject(i).getString("img_src"));
      }
      roverImgUrls.add(resObject);
    }

    return roverImgUrls;
  }

}
