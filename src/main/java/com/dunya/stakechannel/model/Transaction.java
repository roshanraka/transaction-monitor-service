package com.dunya.stakechannel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
	
	private Trace trace;

    private long blockTime;

    private String blockNumber;

    public Trace getTrace ()
    {
        return trace;
    }

    public void setTrace (Trace trace)
    {
        this.trace = trace;
    }

    public long getBlockTime ()
    {
        return blockTime;
    }

    @JsonProperty("block_time")
    public void setBlockTime (long blockTime)
    {
        this.blockTime = blockTime;
    }

    public String getBlockNumber ()
    {
        return blockNumber;
    }

    @JsonProperty("block_number")
    public void setBlockNumber (String blockNumber)
    {
        this.blockNumber = blockNumber;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [trace = "+trace+", block_time = "+blockTime+", block_number = "+blockNumber+"]";
    }
}
