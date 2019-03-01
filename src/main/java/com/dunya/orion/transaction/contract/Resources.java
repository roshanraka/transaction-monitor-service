package com.dunya.orion.transaction.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Resources {

	@JsonProperty("cpu_usage")
	private int cpuUsage;
	
	@JsonProperty("net_usage")
	private int netUsage;

	public int getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(int cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public int getNetUsage() {
		return netUsage;
	}

	public void setNetUsage(int netUsage) {
		this.netUsage = netUsage;
	}

	@Override
	public String toString() {
		return "Resources [cpuUsage=" + cpuUsage + ", netUsage=" + netUsage + "]";
	}
}
