package com.fsse2603.project_backend.repository;

import com.fsse2603.project_backend.data.transaction.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {

}
