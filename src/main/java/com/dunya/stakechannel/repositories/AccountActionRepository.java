package com.dunya.stakechannel.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dunya.stakechannel.model.AccountAction;

public interface AccountActionRepository extends MongoRepository<AccountAction, Object>{

}
