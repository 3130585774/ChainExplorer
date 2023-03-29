package com.hbgcjsxy.chainexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;
import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;


@ContentView(R.layout.activity_transaction_detail)
public class TransactionDetailActivity extends AppCompatActivity {
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        //1、根据用户的选择和输入的内容 通过网络查询交易数据
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        String hash = intent.getStringExtra("hash");
        System.out.println(type);
        System.out.println(hash);
        iv_back.setOnClickListener(view -> {
            Iv_back();
        });

        HttpManager manager = x.http();
        RequestParams params = new RequestParams(Constant.BASE_URL + Constant.TRANSACTION);
        params.addHeader("Ok-Access-Key", Constant.API_KEY);
        params.addParameter("chainShortName", type);
        params.addParameter("txid", hash);

        manager.get(params, new Callback.CommonCallback<org.json.JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                //网络请求成功时会调用该方法
//                show_transaction_fills(transaction_fills_praseRespone(result));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //网络请求遇到错误时会调用该方法
                System.out.println(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //当用户取消网络请求时，会调用该方法
                System.out.println("cancelled");

            }

            @Override
            public void onFinished() {
                //网络请求结束时会调用该方法
                System.out.println("over");
            }
        });


        //2、接收网络查询的结果

        //3、根据结果，将数据显示到界面上

    }
    private void Iv_back(){
        this.finish();
    }
//    private Response transaction_fills_praseRespone(JSONObject result){
//        return JSON.parseObject(result.toString(),Response.class);
//
//    }
//    private void show_transaction_fills(Response transaction_fills){
//        Data data = transaction_fills.getData().get(0);
////        System.out.println(data.getChainFullName());
//        tv_fullName.setText(data.getChainFullName());
//        tv_height.setText("区块高度："+data.getHeight());
//    }
}
