package com.objectzilla.persistence.repository;

import com.objectzilla.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
