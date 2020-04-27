package com.github.helgahorvath.controller;

import com.github.helgahorvath.entity.Position;
import com.github.helgahorvath.service.ClientService;
import com.github.helgahorvath.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class PositionController {

  private PositionService positionService;
  private ClientService clientService;

  @Autowired
  PositionController(PositionService positionService, ClientService clientService) {
    this.positionService = positionService;
    this.clientService = clientService;
  }

  @PostMapping("/positions")
  public String createPosition(@RequestBody Position position, HttpServletRequest request) {
    throwExceptionIfUnauthorized(request);
    StringBuffer requestUrl = request.getRequestURL().append("/");
    position.setUrl(requestUrl.toString());
    positionService.savePosition(position);
    return position.getUrl();
  }

  @GetMapping("/positions")
  public List<Position> getPositions(@RequestParam String jobTitle, String location, HttpServletRequest request) {
    throwExceptionIfUnauthorized(request);
    return positionService.getPositionList(jobTitle, location);
  }

  private void throwExceptionIfUnauthorized (HttpServletRequest request) {
    String apiKey = request.getHeader("Authorization").split("ApiKey ")[1];
    boolean authorized = clientService.isApiKeyValid(apiKey);
    if (!authorized) {
      throw new AccessDeniedException("API key is invalid");
    }
  }

}
