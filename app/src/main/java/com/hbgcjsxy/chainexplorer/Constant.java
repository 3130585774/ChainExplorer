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
    public static final String INFO = "api/v5/explorer/blockchain/info";
    public static final boolean isDebug = true;

    public static final String[] TYPE_ARRAY = {"BTC", "ETH", "OKC", "TRON"};

    public static final String[] test_btc_tx_hash = {"eb344000d48350b8e8655b307a61666304575f89a4b0900f8220989d4ab3bfd0", "0xf87524573d14ff8ffe1f63828b75f413b1a4078528793b4d0b3f728644f8b52b","0xc75be5a6cc3d4ad6a964c348481d270f3d4061c81dca54c4dbfda62b860e7e47","8be2d992ca4a803d6cb8c195c72a44a011ce2fa6d998eb154cffb02758be6faa"};


}
