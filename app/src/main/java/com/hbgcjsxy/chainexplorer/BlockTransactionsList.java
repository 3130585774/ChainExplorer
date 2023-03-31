package com.hbgcjsxy.chainexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

@SuppressLint("NonConstantResourceId")
@ContentView(R.layout.activity_blockchaindetails)
public class BlockTransactionsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        //TODO 详情
        System.out.println(GetBlockTransactionList());
    }
    private JSONObject GetBlockTransactionList() {
        final JSONObject[] jsonObject = {null};
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                while (msg.what!=0) {
                    System.out.println();
                    jsonObject[0] = (JSONObject) msg.obj;
                }
            }
        };

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("chainShortName", "BTC");
        parameters.put("height","783306");
        Uitls.HttpsGetX(handler, Constant.BASE_URL + Constant.BLOCKTRANSACTIONLIST,parameters);
        return jsonObject[0];
    }

}