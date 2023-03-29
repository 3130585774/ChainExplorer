package com.hbgcjsxy.chainexplorer;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Uitls {
    public static String TimestampToTime(String timeStamp) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
    }
    public static double CalculateTransaction(InputDetails inputDetails,OutputDetails outputDetails){
        //TODO 计算交易输入输出

        double re = 0;
        return re;
    }
}
