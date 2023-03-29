package com.hbgcjsxy.chainexplorer;
import java.util.ArrayList;
import java.util.List;
public class Data
{
    private String chainFullName;

    private String chainShortName;

    private String txid;

    private String height;

    private String transactionTime;

    private String amount;

    private String transactionSymbol;

    private String txfee;

    private String index;

    private String confirm;

    private List<InputDetails> inputDetails;

    private List<OutputDetails> outputDetails;

    private String state;

    private String gasLimit;

    private String gasUsed;

    private String gasPrice;

    private String totalTransactionSize;

    private String virtualSize;

    private String weight;

    private String nonce;

    private String transactionType;

    private String methodId;

    private List<String> tokenTransferDetails;

    private List<String> contractDetails;

    public void setChainFullName(String chainFullName){
        this.chainFullName = chainFullName;
    }
    public String getChainFullName(){
        return this.chainFullName;
    }
    public void setChainShortName(String chainShortName){
        this.chainShortName = chainShortName;
    }
    public String getChainShortName(){
        return this.chainShortName;
    }
    public void setTxid(String txid){
        this.txid = txid;
    }
    public String getTxid(){
        return this.txid;
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
    public void setIndex(String index){
        this.index = index;
    }
    public String getIndex(){
        return this.index;
    }
    public void setConfirm(String confirm){
        this.confirm = confirm;
    }
    public String getConfirm(){
        return this.confirm;
    }
    public void setInputDetails(List<InputDetails> inputDetails){
        this.inputDetails = inputDetails;
    }
    public List<InputDetails> getInputDetails(){
        return this.inputDetails;
    }
    public void setOutputDetails(List<OutputDetails> outputDetails){
        this.outputDetails = outputDetails;
    }
    public List<OutputDetails> getOutputDetails(){
        return this.outputDetails;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    public void setGasLimit(String gasLimit){
        this.gasLimit = gasLimit;
    }
    public String getGasLimit(){
        return this.gasLimit;
    }
    public void setGasUsed(String gasUsed){
        this.gasUsed = gasUsed;
    }
    public String getGasUsed(){
        return this.gasUsed;
    }
    public void setGasPrice(String gasPrice){
        this.gasPrice = gasPrice;
    }
    public String getGasPrice(){
        return this.gasPrice;
    }
    public void setTotalTransactionSize(String totalTransactionSize){
        this.totalTransactionSize = totalTransactionSize;
    }
    public String getTotalTransactionSize(){
        return this.totalTransactionSize;
    }
    public void setVirtualSize(String virtualSize){
        this.virtualSize = virtualSize;
    }
    public String getVirtualSize(){
        return this.virtualSize;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }
    public String getWeight(){
        return this.weight;
    }
    public void setNonce(String nonce){
        this.nonce = nonce;
    }
    public String getNonce(){
        return this.nonce;
    }
    public void setTransactionType(String transactionType){
        this.transactionType = transactionType;
    }
    public String getTransactionType(){
        return this.transactionType;
    }
    public void setMethodId(String methodId){
        this.methodId = methodId;
    }
    public String getMethodId(){
        return this.methodId;
    }
    public void setTokenTransferDetails(List<String> tokenTransferDetails){
        this.tokenTransferDetails = tokenTransferDetails;
    }
    public List<String> getTokenTransferDetails(){
        return this.tokenTransferDetails;
    }
    public void setContractDetails(List<String> contractDetails){
        this.contractDetails = contractDetails;
    }
    public List<String> getContractDetails(){
        return this.contractDetails;
    }
}
