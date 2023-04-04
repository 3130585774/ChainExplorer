package com.hbgcjsxy.chainexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
    private List<TransactionList> transactionLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
        Context context = getApplicationContext();
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        transactions_list.setLongClickable(true);
        Intent intent = getIntent();
        Intent intentout = new Intent(this,TransactionDetailActivity.class);
        //TODO 详情
        GetBlockTransactionList(intent);
        transactions_list.setOnItemClickListener((adapterView, view, i, l) -> {
            intentout.putExtra("type", intent.getStringExtra("type"));
            intentout.putExtra("txid", transactionLists.get(i).getTxid());
            startActivity(intentout);
        });
        transactions_list.setOnItemLongClickListener((adapterView, view, i, l) -> {
            System.out.println("长按啦");
            ClipData clip = ClipData.newPlainText("simple text", transactionLists.get(i).getTxid().trim());
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(context, "已复制交易Hash", Toast.LENGTH_SHORT).show();
            return true;
        });


    }

    private void GetBlockTransactionList(Intent intent) {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                JSONObject jsonObject = (JSONObject) msg.obj;
                transactionLists = Uitls.transactionParseRespond(jsonObject).getData().get(0).getTransactionList();
                showBlockList(transactionLists);

            }
        };
        Map<String, String> parameters = new HashMap<>();
        parameters.put("chainShortName", intent.getStringExtra("type"));
        parameters.put("height", intent.getStringExtra("height"));
        parameters.put("limit","30");
        Uitls.HttpsGetX(handler, Constant.BASE_URL + Constant.TRANSACTIONLIST, parameters);
    }

    private void showBlockList(List<TransactionList> transactionLists) {
        TransactionsAdapter transactionsAdapter = new TransactionsAdapter(TransactionsListActivity.this,R.layout.transactions_item,transactionLists);
        transactions_list.setAdapter(transactionsAdapter);

    }

}