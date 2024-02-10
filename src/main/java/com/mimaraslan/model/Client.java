package com.mimaraslan.model;

import jakarta.persistence.*;

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

  public Client() {

  }

  public Client(String firstName, String lastName, boolean confirmed) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.confirmed = confirmed;
  }

  public long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public boolean isConfirmed() {
    return confirmed;
  }

  public void setConfirmed(boolean isConfirmed) {
    this.confirmed = isConfirmed;
  }

  @Override
  public String toString() {
    return "Client [id=" + id + ", firstName=" + firstName + ", desc=" + lastName + ", confirmed=" + confirmed + "]";
  }

}
