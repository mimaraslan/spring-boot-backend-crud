package com.mimaraslan.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "confirmed")
  private boolean confirmed;

  public Client(String firstName, String lastName, boolean confirmed) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.confirmed = confirmed;
  }

}
