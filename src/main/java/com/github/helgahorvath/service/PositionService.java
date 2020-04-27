package com.github.helgahorvath.service;

import com.github.helgahorvath.entity.Position;
import java.util.List;

public interface PositionService {
  void savePosition(Position position);
  List<Position> getPositionList(String job, String location);
}
