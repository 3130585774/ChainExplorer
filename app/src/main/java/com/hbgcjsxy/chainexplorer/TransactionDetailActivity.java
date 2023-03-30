package com.hbgcjsxy.chainexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;


@SuppressLint("NonConstantResourceId")
@ContentView(R.layout.activity_transaction_detail)
public class TransactionDetailActivity extends AppCompatActivity {
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.iv_logo)
    private ImageView iv_logo;
    @ViewInject(R.id.tv_logo_title)
    private TextView tv_logo_title;
    @ViewInject(R.id.tv_block_height)
    private TextView tv_block_height;
    @ViewInject(R.id.tv_confirm)
    private TextView tv_confirm;
    @ViewInject(R.id.tv_transactionTime)
    private TextView tv_transactionTime;
    @ViewInject(R.id.tv_totalTransactionSize)
    private TextView tv_totalTransactionSize;
    @ViewInject(R.id.tv_virtualSize)
    private TextView tv_virtualSize;
    @ViewInject(R.id.tv_weight)
    private TextView tv_weight;
    @ViewInject(R.id.tv_input)
    private TextView tv_input;
    @ViewInject(R.id.tv_output)
    private TextView tv_output;
    @ViewInject(R.id.tv_txfee)
    private TextView tv_txfee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        //1、根据用户的选择和输入的内容 通过网络查询交易数据
        Intent intent = getIntent();
        ResponseSerializable responseSerializable=(ResponseSerializable) intent.getSerializableExtra("re");
        Response response = responseSerializable.getResponse();
        iv_back.setOnClickListener(view -> {
            Iv_back();
        });
        System.out.println(response.getCode());
        show_transaction_fills(response);




        //2、接收网络查询的结果

        //3、根据结果，将数据显示到界面上

    }
    private void Iv_back(){
        this.finish();
    }

    private void show_transaction_fills(Response transaction_fills){
        Data data = transaction_fills.getData().get(0);
        tv_logo_title.setText(data.getChainFullName());
        tv_block_height.setText(data.getHeight());
        tv_confirm.setText(data.getConfirm());
        tv_transactionTime.setText(Uitls.TimestampToTime(data.getTransactionTime()));
        tv_totalTransactionSize.setText(String.format("%s Byte", data.getTotalTransactionSize()));
        tv_virtualSize.setText(String.format("%s Byte", data.getVirtualSize()));
        tv_weight.setText(data.getWeight());
//        tv_input.setText(data);\
        Dictionary<String , Double> De = Uitls.CalculateTransaction(data.getInputDetails(),data.getOutputDetails());
        tv_input.setText(String.format("%.8f %s",De.get("input"),data.getTransactionSymbol()));
        tv_output.setText(String.format("%.8f %s",De.get("output"),data.getTransactionSymbol()));
        tv_txfee.setText(data.getTxfee());
        String chainShortName = data.getChainShortName();
        System.out.println(chainShortName);
        System.out.println(chainShortName=="BTC");
        if (Objects.equals(chainShortName, "BTC")) iv_logo.setImageResource(R.drawable.btc_logo);
        if (Objects.equals(chainShortName, "ETH")) iv_logo.setImageResource(R.drawable.eth_logo);
        if (Objects.equals(chainShortName, "OCK")) iv_logo.setImageResource(R.drawable.okc_logo);
        if (Objects.equals(chainShortName, "TRON")) iv_logo.setImageResource(R.drawable.tron_logo);
    }

}
