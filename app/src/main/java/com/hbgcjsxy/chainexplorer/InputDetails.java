package com.hbgcjsxy.chainexplorer;
public class InputDetails
{
    private String inputHash;

    private String tag;

    private String amount;

    private boolean contract;

    public void setInputHash(String inputHash){
        this.inputHash = inputHash;
    }
    public String getInputHash(){
        return this.inputHash;
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
