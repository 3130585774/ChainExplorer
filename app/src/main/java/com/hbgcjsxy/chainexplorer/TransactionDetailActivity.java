package com.hbgcjsxy.chainexplorer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbgcjsxy.chainexplorer.databinding.ActivityMainBinding;
import com.hbgcjsxy.chainexplorer.databinding.ActivityTransactionDetailBinding;

import org.json.JSONObject;
import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import  org.xutils.x;
import java.util.Dictionary;
import java.util.Objects;



public class TransactionDetailActivity extends AppCompatActivity {
    private ActivityTransactionDetailBinding binding;


    private AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        binding = ActivityTransactionDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HttpManager manager = x.http();

        RequestParams params = new RequestParams(Constant.BASE_URL + Constant.TRANSACTION);
        params.addHeader("Ok-Access-Key", Constant.API_KEY);

        Intent intent = getIntent();

        params.addParameter("chainShortName", intent.getStringExtra("type"));
        params.addParameter("txid", intent.getStringExtra("txid"));

        System.out.println(params);
        manager.get(params, new Callback.CommonCallback<org.json.JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                //网络请求成功时会调用该方法
                Response response = Uitls.transactionFillsParseRespond(result);
                if (!Objects.equals(response.getCode(), "0")) searchError();
                show_transaction_fills(response);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //网络请求遇到错误时会调用该方法
                System.out.println(ex);
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
        System.out.println(chainShortName);
        if (Objects.equals(chainShortName, "BTC")) binding.ivLogo.setImageResource(R.drawable.btc_logo);
        if (Objects.equals(chainShortName, "ETH")) binding.ivLogo.setImageResource(R.drawable.eth_logo);
        if (Objects.equals(chainShortName, "OKTC")) binding.ivLogo.setImageResource(R.drawable.okc_logo);
        if (Objects.equals(chainShortName, "TRON")) binding.ivLogo.setImageResource(R.drawable.tron_logo);

        binding.tvLogoTitle.setText(data.getChainFullName());
        binding.tvBlockHeight.setText(data.getHeight());
        binding.tvConfirm.setText(data.getConfirm());
        binding.tvTransactionTime.setText(Uitls.TimestampToTime(data.getTransactionTime()));
        binding.tvTotalTransactionSize.setText(Objects.equals(data.getTotalTransactionSize(), "") ? "-" : String.format("%s Byte", data.getTotalTransactionSize()));
        binding.tvVirtualSize.setText(Objects.equals(data.getVirtualSize(), "") ? "-" : String.format("%s Byte", data.getVirtualSize()));
        binding.tvWeight.setText(Objects.equals(data.getWeight(), "") ?"-":data.getWeight());
        if (Objects.equals(chainShortName, "BTC")) {
            Dictionary<String, Double> De = Uitls.CalculateTransaction(data.getInputDetails(), data.getOutputDetails());
            binding.tvInput.setText(String.format("%.8f %s", De.get("input"), data.getTransactionSymbol()));
            binding.tvOutput.setText(String.format("%.8f %s", De.get("output"), data.getTransactionSymbol()));
        } else {
            binding.tvInput.setText("-");
            binding.tvOutput.setText("-");
        }
        binding.tvTxfee.setText(data.getTxfee());


    }

    private void searchError() {
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("错误！")//标题
                .setMessage("查询失败\n请检查地址是否正确")//内容
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
