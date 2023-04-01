package com.hbgcjsxy.chainexplorer;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    public static Response transactionFillsParseRespond(JSONObject result) {
        return JSON.parseObject(result.toString(), Response.class);

    }

    public static ResponseInfo info_parseRespond(JSONObject result) {
        return JSON.parseObject(result.toString(), ResponseInfo.class);

    }


    /**
     * http get请求工具
     *
     * @param mHandler 回调
     * @param url      网址
     */
    public static void HttpsGetX(final Handler mHandler, String url, Map<String, String> parameters) {
        int mWhat = 0;
        int fail = 1;
        RequestParams params = new RequestParams(url);
        params.addHeader("Ok-Access-Key", Constant.API_KEY);
        for (Map.Entry<String, String> entry : parameters.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + value);
            params.addParameter(key.trim(), value.trim());
        }
//        params.addParameter("chainShortName", "BTC");
//        params.addParameter("height","783306" );
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                System.out.println("result"+result);
                //解析result
                Message message = new Message();
                message.obj = result;
                message.what = mWhat;
                mHandler.sendMessage(message);
            }

            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                mHandler.sendEmptyMessage(fail);
            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {
            }
        });
    }
}
