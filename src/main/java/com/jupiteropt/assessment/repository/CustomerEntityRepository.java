package com.jupiteropt.assessment.repository;

import com.jupiteropt.assessment.domain.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEntityRepository extends CrudRepository<CustomerEntity, Long> {
  boolean existsByFirstNameAndLastName(String firstName, String lastName);
}