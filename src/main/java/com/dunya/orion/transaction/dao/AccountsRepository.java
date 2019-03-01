package com.dunya.orion.transaction.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dunya.orion.transaction.model.Account;

@Repository
public interface AccountsRepository extends MongoRepository<Account, String> {
    Optional<Account> findOneByAccountName(String accountName);
}
