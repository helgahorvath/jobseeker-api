package com.github.helgahorvath.controller;

import com.github.helgahorvath.entity.Position;
import com.github.helgahorvath.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class PositionController {

  private PositionService positionService;

  @Autowired
  PositionController(PositionService positionService) {
    this.positionService = positionService;
  }

  @PostMapping("/positions")
  public String createPosition(@RequestBody Position position, HttpServletRequest request) {
    //todo api kulcs ellenőrzése
    // todo hiba
    StringBuffer requestUrl = request.getRequestURL().append("/");
    position.setUrl(requestUrl.toString());
    positionService.savePosition(position);
    return position.getUrl();
  }


  @GetMapping("/positions")
  public List<Position> getPositions(@RequestParam String jobTitle, String location) {
    return positionService.getPositionList(jobTitle, location);
  }


}
