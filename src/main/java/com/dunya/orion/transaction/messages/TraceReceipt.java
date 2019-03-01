package com.dunya.orion.transaction.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TraceReceipt
{
    private String status;

    private int netUsageWords;

    private int cpuUsageUs;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
    
    public int getNetUsageWords ()
    {
        return netUsageWords;
    }

    @JsonProperty("net_usage_words")
    public void setNetUsageWords (int netUsageWords)
    {
        this.netUsageWords = netUsageWords;
    }

    public int getCpuUsageUs ()
    {
        return cpuUsageUs;
    }

    @JsonProperty("cpu_usage_us")
    public void setCpuUsageUs (int cpuUsageUs)
    {
        this.cpuUsageUs = cpuUsageUs;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+", net_usage_words = "+netUsageWords+", cpu_usage_us = "+cpuUsageUs+"]";
    }
}
