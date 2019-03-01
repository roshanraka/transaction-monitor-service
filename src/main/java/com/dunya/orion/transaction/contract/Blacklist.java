package com.dunya.orion.transaction.contract;

public class Blacklist {
	private int blacklist;

	public int getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(int blacklist) {
		this.blacklist = blacklist;
	}

	@Override
	public String toString() {
		return "Blacklist [blacklist=" + blacklist + "]";
	}
}