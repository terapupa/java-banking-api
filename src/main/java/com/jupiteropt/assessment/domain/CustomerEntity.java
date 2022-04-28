package com.jupiteropt.assessment.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The data persistence entity.
 * The class represents the data model used by JPA and Hibernate ORM to represent the MySQL schema.
 */

@Entity
@Table(name="customer_entity")
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @OneToMany(mappedBy = "customerEntity", orphanRemoval = true)
  private List<AccountEntity> accountEntities = new ArrayList<>();

  public List<AccountEntity> getAccountEntities() {
    return accountEntities;
  }

  public void setAccountEntities(List<AccountEntity> accountEntities) {
    this.accountEntities = accountEntities;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" +
        "id = " + id + ", " +
        "firstName = " + firstName + ", " +
        "lastName = " + lastName + ")";
  }
}
