package com.hbgcjsxy.chainexplorer;
public class Blocklist {
    private String txid;

    private String blockHash;

    private String height;

    private String transactionTime;

    private String from;

    private String fromTag;

    private String to;

    private String toTag;

    private String amount;

    private String transactionSymbol;

    private String txfee;

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
    public void setFrom(String from){
        this.from = from;
    }
    public String getFrom(){
        return this.from;
    }
    public void setFromTag(String fromTag){
        this.fromTag = fromTag;
    }
    public String getFromTag(){
        return this.fromTag;
    }
    public void setTo(String to){
        this.to = to;
    }
    public String getTo(){
        return this.to;
    }
    public void setToTag(String toTag){
        this.toTag = toTag;
    }
    public String getToTag(){
        return this.toTag;
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
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
}