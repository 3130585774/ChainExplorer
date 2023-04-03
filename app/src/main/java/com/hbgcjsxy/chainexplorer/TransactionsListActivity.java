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
@ContentView(R.layout.activity_transactions_list)
public class TransactionsListActivity extends AppCompatActivity {
    @ViewInject(R.id.transaction_list)
    private ListView transactions_list;
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
                showBlockList(Uitls.transactionParseRespond(jsonObject).getData().get(0).getTransactionList());

            }
        };
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("chainShortName", intent.getStringExtra("type"));
        parameters.put("height", intent.getStringExtra("height"));
        parameters.put("limit","50");
        Uitls.HttpsGetX(handler, Constant.BASE_URL + Constant.TRANSACTIONLIST, parameters);
    }

    private void showBlockList(List<TransactionList> transactionLists) {
        TransactionsAdapter transactionsAdapter = new TransactionsAdapter(TransactionsListActivity.this,R.layout.transactions_item,transactionLists);
        transactions_list.setAdapter(transactionsAdapter);

    }

}