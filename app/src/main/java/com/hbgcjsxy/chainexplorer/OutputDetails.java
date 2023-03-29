package com.hbgcjsxy.chainexplorer;
public class OutputDetails
{
    private String outputHash;

    private String tag;

    private String amount;

    private boolean contract;

    public void setOutputHash(String outputHash){
        this.outputHash = outputHash;
    }
    public String getOutputHash(){
        return this.outputHash;
    }
    public void setTag(String tag){
        this.tag = tag;
    }
    public String getTag(){
        return this.tag;
    }
    public void setAmount(String amount){
        this.amount = amount;
    }
    public String getAmount(){
        return this.amount;
    }
    public void setContract(boolean contract){
        this.contract = contract;
    }
    public boolean getContract(){
        return this.contract;
    }
}