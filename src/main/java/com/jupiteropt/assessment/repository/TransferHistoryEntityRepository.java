package com.jupiteropt.assessment.repository;

import com.jupiteropt.assessment.domain.TransferHistoryEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferHistoryEntityRepository extends CrudRepository<TransferHistoryEntity, Long> {
  List<TransferHistoryEntity> findByAccountFromIsAndAccountToIs(long accountFrom, long accountTo);
}