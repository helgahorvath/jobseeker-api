package com.github.helgahorvath.service;

import com.github.helgahorvath.entity.Client;
import java.util.UUID;


public interface ClientService {
  public void saveClient(Client client);
  public UUID generateApiKey();
  public boolean isApiKeyValid(String apiKey);
}
