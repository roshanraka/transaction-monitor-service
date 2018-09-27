package com.dunya.stakechannel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TraceReceipt
{
    private String status;

    private String netUsageWords;

    private String cpuUsageUs;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
    
    public String getNetUsageWords ()
    {
        return netUsageWords;
    }

    @JsonProperty("net_usage_words")
    public void setNetUsageWords (String netUsageWords)
    {
        this.netUsageWords = netUsageWords;
    }

    public String getCpu_usage_us ()
    {
        return cpuUsageUs;
    }

    @JsonProperty("cpu_usage_us")
    public void setCpuUsageUs (String cpuUsageUs)
    {
        this.cpuUsageUs = cpuUsageUs;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+", net_usage_words = "+netUsageWords+", cpu_usage_us = "+cpuUsageUs+"]";
    }
}
