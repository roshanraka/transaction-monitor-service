package com.dunya.stakechannel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dunya.stakechannel.model.AccountAction;

@Repository
public interface AccountActionRepository extends MongoRepository<AccountAction, Object>{

}
