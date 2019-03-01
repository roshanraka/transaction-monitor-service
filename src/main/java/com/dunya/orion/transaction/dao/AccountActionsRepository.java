package com.dunya.orion.transaction.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dunya.orion.transaction.model.AccountAction;

@Repository
public interface AccountActionsRepository extends MongoRepository<AccountAction, String> {

    List<AccountAction> findByAccountName(String accountName);
}
