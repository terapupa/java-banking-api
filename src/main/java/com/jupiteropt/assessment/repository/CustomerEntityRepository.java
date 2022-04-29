package com.jupiteropt.assessment.repository;

import com.jupiteropt.assessment.domain.CustomerEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEntityRepository extends CrudRepository<CustomerEntity, Long> {

  Optional<CustomerEntity> findByFirstNameAndLastName(String firstName, String lastName);

}