package com.github.helgahorvath.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 100, min = 3, message = "Please enter a name between 3 and 100 characters.")
  private String name;

  @Email(message = "Please enter a valid email")
  @Column(unique = true)
  private String email;

}
