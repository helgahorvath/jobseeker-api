package com.github.helgahorvath.controller;

import com.github.helgahorvath.entity.Position;
import com.github.helgahorvath.service.ClientService;
import com.github.helgahorvath.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("positions")
@Validated
public class PositionController {

  private PositionService positionService;
  private ClientService clientService;

  @Autowired
  PositionController(PositionService positionService, ClientService clientService) {
    this.positionService = positionService;
    this.clientService = clientService;
  }

  @PostMapping
  public String createPosition(@RequestBody Position position, HttpServletRequest request) {
    throwExceptionIfUnauthorized(request);
    StringBuffer requestUrl = request.getRequestURL().append("/");
    position.setUrl(requestUrl.toString());
    positionService.savePosition(position);
    return position.getUrl();
  }

  @GetMapping
  public List<Position> getPositions(@RequestParam
                                     @Size(min = 3, max = 50, message = "Please enter a keyword between 3 and 50 characters")
                                     String keyword,
                                     @Size(min = 3, max = 50, message = "Please enter a location between 3 and 50 characters")
                                     String location,
                                     HttpServletRequest request) {
    throwExceptionIfUnauthorized(request);
    return positionService.getPositionList(keyword, location);
  }

  private void throwExceptionIfUnauthorized (HttpServletRequest request) {
    String apiKey = request.getHeader("Authorization").split("ApiKey ")[1];
    boolean authorized = clientService.isApiKeyValid(apiKey);
    if (!authorized) {
      throw new AccessDeniedException("API key is invalid");
    }
  }

}
