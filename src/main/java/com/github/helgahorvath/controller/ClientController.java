package com.github.helgahorvath.controller;

import com.github.helgahorvath.entity.Client;
import com.github.helgahorvath.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
public class ClientController {

  private ClientService clientService;

  @Autowired
  ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping("/clients")
  public UUID registerClient(@RequestBody Client client) {
    clientService.saveClient(client);
    return clientService.generateApiKey();
  }

}
