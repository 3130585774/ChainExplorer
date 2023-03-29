package com.hbgcjsxy.chainexplorer;

/**
 * 为程序App创建的全局常量类，把一些配置的参数，固定不变的内容存放在该类当中
 * 也可以理解为配置类
 */
public class Constant {
    //访问网路的oklink接口服务的钥匙
    public static final String API_KEY = "59906407-0356-47a8-b80c-308654cf9e40";

    public static final String BASE_URL = "https://www.oklink.com/";
    /**
     * 链上交易详情接口名
     */
    //https://www.oklink.com/api/v5/explorer/transaction/transaction-fills
    public static final String TRANSACTION = "api/v5/explorer/transaction/transaction-fills";
    public static final boolean isDebug = true;

    public static final String[] TYPE_ARRAY = {"BTC", "ETH", "LTC"};

    public static final String test_btc_tx_hash = "0a37339bfe54474095e96b83ded45aa1f745beee4faf55039f25087858d5c2cf";


}
