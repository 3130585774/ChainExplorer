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
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("NonConstantResourceId")
@ContentView(R.layout.activity_blockchaindetails)
public class BlockTransactionsListActivity extends AppCompatActivity {
    @ViewInject(R.id.block_list)
    private ListView block_list;
    private  List<BlockList> blockList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        Intent intentout = new Intent(this, TransactionsListActivity.class);
        //TODO 详情
        GetBlockTransactionList(intent);
        block_list.setOnItemClickListener((adapterView, view, i, l) -> {
            intentout.putExtra("type", intent.getStringExtra("type"));
            intentout.putExtra("height", blockList.get(i).getHeight());
            startActivity(intentout);
        });
    }

    private void GetBlockTransactionList(Intent intent) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                JSONObject jsonObject = (JSONObject) msg.obj;
                blockList = Uitls.blockListParseRespond(jsonObject).getData().get(0).getBlockList();
                showBlockList(blockList);

            }

        };
        Map<String, String> parameters = new HashMap<>();
        parameters.put("chainShortName", intent.getStringExtra("type"));
        parameters.put("limit", "50");
        Uitls.HttpsGetX(handler, Constant.BASE_URL + Constant.BLOCKLIST, parameters);
    }

    private void showBlockList(List<BlockList> blocklist) {
        System.out.println(blocklist.get(0).getBlockTime());
        BlockListAdapter blockListAdapter = new BlockListAdapter(BlockTransactionsListActivity.this, R.layout.block_item, blocklist);
        block_list.setAdapter(blockListAdapter);

    }

}