package com.github.helgahorvath.service;

import java.util.HashMap;

public interface RequestService {
  HashMap<String, String>[] fetchPositions(String job, String location);
  String generateApiUrl(String job, String location);
}
