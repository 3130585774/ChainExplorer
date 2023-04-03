package com.hbgcjsxy.chainexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import java.util.List;
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
        Intent intent = getIntent();
        //TODO 详情
        GetBlockTransactionList(intent);
    }

    private void GetBlockTransactionList(Intent intent) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                JSONObject jsonObject = (JSONObject) msg.obj;
                System.out.println(msg.obj);
                showBlockList(Uitls.blockListParseRespond(jsonObject).getData().get(0).getBlockList());

            }
        };
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("chainShortName", intent.getStringExtra("type"));
        parameters.put("limit","50");
        Uitls.HttpsGetX(handler, Constant.BASE_URL + Constant.BLOCKLIST, parameters);
    }

    private void showBlockList(List<BlockList> blocklist) {
        System.out.println(blocklist.get(0).getBlockTime());
        BlockListAdapter blockListAdapter = new BlockListAdapter(BlockTransactionsList.this,R.layout.block_item,blocklist);
        block_list.setAdapter(blockListAdapter);

    }

}