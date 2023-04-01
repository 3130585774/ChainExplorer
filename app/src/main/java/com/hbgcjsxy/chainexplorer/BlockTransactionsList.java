package com.hbgcjsxy.chainexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ListView;

import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

@SuppressLint("NonConstantResourceId")
@ContentView(R.layout.activity_blockchaindetails)
public class BlockTransactionsList extends AppCompatActivity {
    @ViewInject(R.id.block_list)
    private ListView block_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        //TODO 详情

    }

    private void GetBlockTransactionList() {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                JSONObject jsonObject = (JSONObject) msg.obj;

            }
        };
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("chainShortName", "BTC");
        parameters.put("height", "783306");
        Uitls.HttpsGetX(handler, Constant.BASE_URL + Constant.BLOCKTRANSACTIONLIST, parameters);
    }

    private void showBlockList(BlockList blocklist) {

    }

}