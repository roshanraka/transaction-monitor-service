package com.dunya.stakechannel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dunya.stakechannel.config.Constants;

@Service
public class ResourceManagerService {

	@Autowired
	RestTemplate restTemplate;
	
	public String stakeUnstake(String accountName) {
		return this.restTemplate.postForObject(Constants.EOS_JS_HOST_URL, accountName, String.class);
	}
}
