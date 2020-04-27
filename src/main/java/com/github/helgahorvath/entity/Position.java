package com.github.helgahorvath.entity;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
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
