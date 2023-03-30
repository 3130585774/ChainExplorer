package com.hbgcjsxy.chainexplorer;

import android.annotation.SuppressLint;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;
import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Uitls {
    public static String TimestampToTime(String timeStamp) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
    }

    public static Dictionary<String, Double> CalculateTransaction(List<InputDetails> inputDetails, List<OutputDetails> outputDetails) {
        //TODO 计算交易输入输出
        double inputDetailsSum = 0.0;
        double outputDetailsSum = 0.0;
        for (InputDetails i : inputDetails) {
            inputDetailsSum += Double.parseDouble(i.getAmount());
        }
        for (OutputDetails o : outputDetails) {
            outputDetailsSum += Double.parseDouble(o.getAmount());
        }
        Dictionary<String, Double> re = new Hashtable<>();
        re.put("input", inputDetailsSum);
        re.put("output", outputDetailsSum);
        return re;
    }

    public static Response GetResponseFills(String type, String txid) throws InterruptedException {
        HttpManager manager = x.http();
        RequestParams params = new RequestParams(Constant.BASE_URL + Constant.TRANSACTION);
        params.addHeader("Ok-Access-Key", Constant.API_KEY);
        params.addParameter("chainShortName", type);
        params.addParameter("txid", txid);
        final Response[] response = {new Response()};
        manager.get(params, new Callback.CommonCallback<org.json.JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                //网络请求成功时会调用该方法
                response[0] = transaction_fills_praseRespone(result);
//                System.out.println(response[0].getCode());

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //网络请求遇到错误时会调用该方法
//                System.out.println(ex);
                System.out.println("error");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //当用户取消网络请求时，会调用该方法
                System.out.println("cancelled");

            }

            @Override
            public void onFinished() {
                //网络请求结束时会调用该方法
                System.out.println("over");
            }
        }).wait();
        return response[0];
    }

    private static Response transaction_fills_praseRespone(JSONObject result) {
        return JSON.parseObject(result.toString(), Response.class);

    }
}
