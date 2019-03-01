package com.dunya.orion.transaction.dao;

import java.util.List;

import com.dunya.orion.transaction.model.AccountAction;

public interface AccountActionsRepositoryCustom {
	List<AccountAction> saveAccountActions(List<AccountAction> accountActionsList);
}
