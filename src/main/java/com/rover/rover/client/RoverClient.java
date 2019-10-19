package com.rover.rover.client;

import com.rover.rover.dto.JsonRoverResponseDto;
import com.rover.rover.utils.Constants;
import com.rover.rover.utils.Helpers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

@Service
public class RoverClient {

  public ArrayList<JsonRoverResponseDto> getJsonResponse() throws IOException, JSONException {
    HttpURLConnection connection = this.getConnection(Constants.API_URL);
    connection.setRequestMethod("GET");
    connection.connect();

    String jsonString = Helpers.convertJson(connection.getInputStream());
    JSONObject jsonResponse = new JSONObject(jsonString);
    JSONArray jsonResponseArr = jsonResponse.getJSONArray("photos");


    ArrayList<JsonRoverResponseDto> roverImgUrls = new ArrayList<>();
    for(int i = 0; i < jsonResponseArr.length(); i++) {
      JsonRoverResponseDto resObject = new JsonRoverResponseDto();
      if(jsonResponseArr.getJSONObject(i) != null) {
        resObject.setImageUrl(jsonResponseArr.getJSONObject(i).getString("img_src"));
      }
      roverImgUrls.add(resObject);
    }

    return roverImgUrls;
  }

  public DataOutputStream readImage(String urlRoute) throws ProtocolException, IOException {
    HttpURLConnection connection = this.getConnection(urlRoute);
    connection.setRequestMethod("GET");
    connection.setDoOutput(true);

    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    out.flush();
    out.close();

    return out;
  }

  private HttpURLConnection getConnection(String urlRoute) throws IOException {
    URL url = new URL(urlRoute);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    return connection;
  }

}
