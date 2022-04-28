package com.jupiteropt.assessment.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AccountEntityRepository extends CrudRepository<AccountEntity, Long> {
  AccountEntity findByAccountId(Long accountId);

  List<AccountEntity> findByCustomerEntity_Name(String name);
}