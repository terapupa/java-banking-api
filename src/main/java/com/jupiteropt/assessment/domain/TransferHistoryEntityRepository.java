package com.jupiteropt.assessment.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TransferHistoryEntityRepository extends CrudRepository<TransferHistoryEntity, Long> {
  List<TransferHistoryEntity> findByAccountFromIsAndAccountToIs(long accountFrom, long accountTo);
}