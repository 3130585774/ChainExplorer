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
import java.util.Locale;

public class Uitls {
    public static String TimestampToTime(String timeStampStr) {
        Long timeStamp = Long.parseLong(timeStampStr);
        @SuppressLint("SimpleDateFormat")
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(new Date(timeStamp));
        System.out.println(date);
        System.out.println(timeStamp);
        return date;
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

    public static Response transaction_fills_praseRespone(JSONObject result) {
        return JSON.parseObject(result.toString(), Response.class);

    }

    public static ResponseInfo info_praseRespone(JSONObject result) {
        return JSON.parseObject(result.toString(), ResponseInfo.class);

    }
}
