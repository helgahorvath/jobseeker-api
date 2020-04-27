package com.github.helgahorvath.service;

import com.github.helgahorvath.entity.Client;
import com.github.helgahorvath.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

  private ClientRepository clientRepository;

  @Autowired
  ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public void saveClient(Client client) {
    clientRepository.save(client);
  }

  public UUID generateApiKey() {
    String name = "name";
    return UUID.nameUUIDFromBytes(name.getBytes());
  }

  public boolean isApiKeyValid(String apiKey) {
    try {
      UUID uuid = UUID.fromString(apiKey);
    }catch (IllegalArgumentException e) {
      return false;
    }
    return true;
  }
}
