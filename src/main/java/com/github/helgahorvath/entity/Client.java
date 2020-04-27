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

  @Size(max = 50, min = 3, message = "Please enter a name between 0 and 50 characters.")
  private String name;

  @Size(max = 50, message = "Please enter an email between 0 and 50 characters.")
  @Email(message = "Please enter a valid email")
  @Column(unique = true)
  private String email;

}
