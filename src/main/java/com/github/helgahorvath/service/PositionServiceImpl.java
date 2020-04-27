package com.github.helgahorvath.service;

import com.github.helgahorvath.entity.Position;
import com.github.helgahorvath.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {

  private PositionRepository positionRepository;
  @Autowired
  PositionServiceImpl(PositionRepository positionRepository) {
    this.positionRepository = positionRepository;
  }

  public void savePosition(Position position) {
    positionRepository.save(position);
  }
}
