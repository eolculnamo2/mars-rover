package com.rover.rover.client;

import org.springframework.stereotype.Service;

import javax.xml.ws.WebServiceClient;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

@WebServiceClient
@Service
public class RoverClient {

  public DataOutputStream readImage(String urlRoute) throws ProtocolException, IOException {

    URL url = new URL(urlRoute);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setDoOutput(true);
    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    out.flush();
    out.close();
    return out;
  }
}
