package com.github.helgahorvath.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Service
public class RequestServiceImpl implements RequestService {

  public HashMap<String, String>[] fetchPositions(String job, String location) {
    String url = generateApiUrl(job, location);
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(url, HashMap[].class);
  }

  public String generateApiUrl(String job, String location) {
    return "https://jobs.github.com/positions.json?description=" + URLEncoder.encode(job, StandardCharsets.UTF_8) + "&location=" + URLEncoder.encode(location, StandardCharsets.UTF_8);
  }
}
