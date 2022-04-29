package com.jupiteropt.assessment.repository;

import com.jupiteropt.assessment.domain.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountEntityRepository extends CrudRepository<AccountEntity, Long> {

  AccountEntity findByAccountId(Long accountId);

  boolean existsByAccountId(Long accountId);
}