package com.github.helgahorvath.repository;

import com.github.helgahorvath.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
  List<Position> findPositionsByJobTitleContainsAndLocationContains(String jobTitle, String location);
}
