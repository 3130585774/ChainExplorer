package com.hbgcjsxy.chainexplorer;

import java.util.ArrayList;
import java.util.List;

public class Data_Transaction {
    private String page;

    private String limit;

    private String totalPage;

    private String chainFullName;

    private String chainShortName;

    private List<TransactionList> transactionList;

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return this.page;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getLimit() {
        return this.limit;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getTotalPage() {
        return this.totalPage;
    }

    public void setChainFullName(String chainFullName) {
        this.chainFullName = chainFullName;
    }

    public String getChainFullName() {
        return this.chainFullName;
    }

    public void setChainShortName(String chainShortName) {
        this.chainShortName = chainShortName;
    }

    public String getChainShortName() {
        return this.chainShortName;
    }

    public void setTransactionList(List<TransactionList> transactionList) {
        this.transactionList = transactionList;
    }

    public List<TransactionList> getTransactionList() {
        return this.transactionList;
    }
}
