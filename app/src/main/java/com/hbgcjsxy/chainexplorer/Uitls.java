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

    public static Response transaction_fills_praseRespone(JSONObject result) {
        return JSON.parseObject(result.toString(), Response.class);

    }
}
