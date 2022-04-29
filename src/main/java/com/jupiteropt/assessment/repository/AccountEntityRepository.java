package com.jupiteropt.assessment.repository;

import com.jupiteropt.assessment.domain.AccountEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountEntityRepository extends CrudRepository<AccountEntity, Long> {

  Optional<AccountEntity> findByAccountId(Long accountId);
}