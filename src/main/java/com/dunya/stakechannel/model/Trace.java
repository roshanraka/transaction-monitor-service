package com.dunya.stakechannel.model;
                
public class Trace
{
    private String elapsed;

    private String id;

    private ActionTrace[] action_traces;

    private String scheduled;

    private Object failed_dtrx_trace;

    private String block_time;

    private String block_num;

    private String net_usage;

    private TraceReceipt receipt;

    public String getElapsed ()
    {
        return elapsed;
    }

    public void setElapsed (String elapsed)
    {
        this.elapsed = elapsed;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public ActionTrace[] getAction_traces ()
    {
        return action_traces;
    }

    public void setAction_traces (ActionTrace[] action_traces)
    {
        this.action_traces = action_traces;
    }

    public String getScheduled ()
    {
        return scheduled;
    }

    public void setScheduled (String scheduled)
    {
        this.scheduled = scheduled;
    }

    public Object getFailed_dtrx_trace ()
    {
        return failed_dtrx_trace;
    }

    public void setFailed_dtrx_trace (Object failed_dtrx_trace)
    {
        this.failed_dtrx_trace = failed_dtrx_trace;
    }

    public String getBlock_time ()
    {
        return block_time;
    }

    public void setBlock_time (String block_time)
    {
        this.block_time = block_time;
    }

    public String getBlock_num ()
    {
        return block_num;
    }

    public void setBlock_num (String block_num)
    {
        this.block_num = block_num;
    }

    public String getNet_usage ()
    {
        return net_usage;
    }

    public void setNet_usage (String net_usage)
    {
        this.net_usage = net_usage;
    }

    public TraceReceipt getReceipt ()
    {
        return receipt;
    }

    public void setReceipt (TraceReceipt receipt)
    {
        this.receipt = receipt;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [elapsed = "+elapsed+", id = "+id+", action_traces = "+action_traces+", scheduled = "+scheduled+", failed_dtrx_trace = "+failed_dtrx_trace+", block_time = "+block_time+", block_num = "+block_num+", net_usage = "+net_usage+", receipt = "+receipt+"]";
    }
}