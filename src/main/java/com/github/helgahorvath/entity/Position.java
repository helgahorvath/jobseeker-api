package com.github.helgahorvath.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true, value = {"id"})
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 50, min = 3, message = "Please enter a job title between 3 and 50 characters.")
  private String jobTitle;

  @Size(max = 50, min = 1, message = "Please enter a location between 1 and 50 characters.")
  private String location;

  private String url;

  @PostPersist
  public void onSave() {
    url = url + id;
  }

}
