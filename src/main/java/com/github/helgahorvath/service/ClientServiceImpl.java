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
    // todo hiba
    clientRepository.save(client);
  }

  public UUID generateApiKey() {
    // todo
    return null;
  }
}
