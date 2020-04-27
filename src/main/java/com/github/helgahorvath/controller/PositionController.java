package com.github.helgahorvath.controller;

import com.github.helgahorvath.entity.Position;
import com.github.helgahorvath.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PositionController {

  private PositionService positionService;

  @Autowired
  PositionController(PositionService positionService) {
    this.positionService = positionService;
  }

  @PostMapping("/positions")
  public void createPosition(@RequestBody Position position) {
    //todo api kulcs ellenőrzése
    // todo return url
    positionService.savePosition(position);
  }

}
