package com.github.helgahorvath.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 50, min = 3, message = "Please enter a job title between 3 and 50 characters.")
  private String jobTitle;

  @Size(max = 50, min = 1, message = "Please enter a location between 1 and 50 characters.")
  private String location;

  private String url;
}
