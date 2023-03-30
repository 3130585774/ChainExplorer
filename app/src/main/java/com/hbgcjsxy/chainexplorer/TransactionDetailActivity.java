package com.hbgcjsxy.chainexplorer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        //1、根据用户的选择和输入的内容 通过网络查询交易数据
        Intent intent = getIntent();
//        ResponseSerializable responseSerializable=(ResponseSerializable) intent.getSerializableExtra("re");
//        Response response = responseSerializable.getResponse();
        iv_back.setOnClickListener(view -> {
            Iv_back();
        });
        HttpManager manager = x.http();
        RequestParams params = new RequestParams(Constant.BASE_URL + Constant.TRANSACTION);
        params.addHeader("Ok-Access-Key", Constant.API_KEY);
        params.addParameter("chainShortName", intent.getStringExtra("type"));
        params.addParameter("txid", intent.getStringExtra("txid"));
        System.out.println(params);
        manager.get(params, new Callback.CommonCallback<org.json.JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                //网络请求成功时会调用该方法
                Response response = Uitls.transaction_fills_praseRespone(result);
                if (!Objects.equals(response.getCode(), "0")) searchError();
                show_transaction_fills(response);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //网络请求遇到错误时会调用该方法
//                System.out.println(ex);
                System.out.println("error");
                searchError();

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

    private void Iv_back() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.finish();
    }

    private void show_transaction_fills(Response transaction_fills) {
        Data data = transaction_fills.getData().get(0);
        String chainShortName = data.getChainShortName();
        if (Objects.equals(chainShortName, "BTC")) iv_logo.setImageResource(R.drawable.btc_logo);
        if (Objects.equals(chainShortName, "ETH")) iv_logo.setImageResource(R.drawable.eth_logo);
        if (Objects.equals(chainShortName, "OCK")) iv_logo.setImageResource(R.drawable.okc_logo);
        if (Objects.equals(chainShortName, "TRON")) iv_logo.setImageResource(R.drawable.tron_logo);

        tv_logo_title.setText(data.getChainFullName());
        tv_block_height.setText(data.getHeight());
        tv_confirm.setText(data.getConfirm());
        tv_transactionTime.setText(Uitls.TimestampToTime(data.getTransactionTime()));
        tv_totalTransactionSize.setText(Objects.equals(data.getTotalTransactionSize(), "") ? "-" : String.format("%s Byte", data.getTotalTransactionSize()));
        tv_virtualSize.setText(Objects.equals(data.getVirtualSize(), "") ? "-" : String.format("%s Byte", data.getVirtualSize()));
        tv_weight.setText(Objects.equals(data.getWeight(), "") ?"-":data.getWeight());
        if (Objects.equals(chainShortName, "BTC")) {
            Dictionary<String, Double> De = Uitls.CalculateTransaction(data.getInputDetails(), data.getOutputDetails());
            tv_input.setText(String.format("%.8f %s", De.get("input"), data.getTransactionSymbol()));
            tv_output.setText(String.format("%.8f %s", De.get("output"), data.getTransactionSymbol()));
        } else {
            tv_input.setText("-");
            tv_output.setText("-");
        }
        tv_txfee.setText(data.getTxfee());


    }

    private void searchError() {
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("错误！")//标题
                .setMessage("查询失败")//内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Iv_back();
                    }
                })
                .create();
        alertDialog.show();
    }

}
