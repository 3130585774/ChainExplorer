package com.hbgcjsxy.chainexplorer;

public class TransactionList
{
    private String txid;

    private String blockHash;

    private String height;

    private String transactionTime;

    private String input;

    private String output;

    private String amount;

    private String transactionSymbol;

    private String txfee;

    private String methodId;

    private String transactionType;

    private String state;

    public void setTxid(String txid){
        this.txid = txid;
    }
    public String getTxid(){
        return this.txid;
    }
    public void setBlockHash(String blockHash){
        this.blockHash = blockHash;
    }
    public String getBlockHash(){
        return this.blockHash;
    }
    public void setHeight(String height){
        this.height = height;
    }
    public String getHeight(){
        return this.height;
    }
    public void setTransactionTime(String transactionTime){
        this.transactionTime = transactionTime;
    }
    public String getTransactionTime(){
        return this.transactionTime;
    }
    public void setInput(String input){
        this.input = input;
    }
    public String getInput(){
        return this.input;
    }
    public void setOutput(String output){
        this.output = output;
    }
    public String getOutput(){
        return this.output;
    }
    public void setAmount(String amount){
        this.amount = amount;
    }
    public String getAmount(){
        return this.amount;
    }
    public void setTransactionSymbol(String transactionSymbol){
        this.transactionSymbol = transactionSymbol;
    }
    public String getTransactionSymbol(){
        return this.transactionSymbol;
    }
    public void setTxfee(String txfee){
        this.txfee = txfee;
    }
    public String getTxfee(){
        return this.txfee;
    }
    public void setMethodId(String methodId){
        this.methodId = methodId;
    }
    public String getMethodId(){
        return this.methodId;
    }
    public void setTransactionType(String transactionType){
        this.transactionType = transactionType;
    }
    public String getTransactionType(){
        return this.transactionType;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
}

