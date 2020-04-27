package com.github.helgahorvath.service;

import com.github.helgahorvath.entity.Position;
import com.github.helgahorvath.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

  private PositionRepository positionRepository;
  private RequestService requestService;

  @Autowired
  public PositionServiceImpl(PositionRepository positionRepository, RequestService requestService) {
    this.positionRepository = positionRepository;
    this.requestService = requestService;
  }

  public void savePosition(Position position) {
    positionRepository.save(position);
  }

  private List<Position> getSavedPositions (String job, String location) {
    return positionRepository.findPositionsByJobTitleContainsAndLocationContains(job, location);
  }

  public List<Position> getPositionList(String job, String location) {
    var positions = getSavedPositions(job, location);
    HashMap<String, String>[] remotePositions = requestService.fetchPositions(job, location);
    for ( HashMap<String, String> remotePosition : remotePositions ) {
      var position = new Position();
        position.setJobTitle(remotePosition.get("title"));
        position.setLocation(remotePosition.get("location"));
        position.setUrl(remotePosition.get("url"));
        positions.add(position);
    }
    return positions;
  }
}
