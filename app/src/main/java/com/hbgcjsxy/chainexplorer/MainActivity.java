package com.hbgcjsxy.chainexplorer;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@SuppressLint("NonConstantResourceId")
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.tv_type)
    private TextView tvType;
    @ViewInject(R.id.et_hash)
    private EditText etInput;
    @ViewInject(R.id.btn_search)
    private Button btnSearch;

    //BTC
    @ViewInject(R.id.btc_tv_rank)
    private TextView btc_tv_rank;

    @ViewInject(R.id.btc_tv_height)
    private TextView btc_tv_height;

    @ViewInject(R.id.btc_tv_lastBlockTime)
    private TextView btc_tv_lastBlockTime;

    //ETH
    @ViewInject(R.id.eth_tv_rank)
    private TextView eth_tv_rank;

    @ViewInject(R.id.eth_tv_height)
    private TextView eth_tv_height;

    @ViewInject(R.id.eth_tv_lastBlockTime)
    private TextView eth_tv_lastBlockTime;

    //OKC
    @ViewInject(R.id.okc_tv_rank)
    private TextView okc_tv_rank;

    @ViewInject(R.id.okc_tv_height)
    private TextView okc_tv_height;

    @ViewInject(R.id.okc_tv_lastBlockTime)
    private TextView okc_tv_lastBlockTime;

    //TRON
    @ViewInject(R.id.tron_tv_rank)
    private TextView tron_tv_rank;

    @ViewInject(R.id.tron_tv_height)
    private TextView tron_tv_height;

    @ViewInject(R.id.tron_tv_lastBlockTime)
    private TextView tron_tv_lastBlockTime;
    @ViewInject(R.id.iv_btc_block)
    private ImageView btc_block;
    //列表选项的window
    private ListPopupWindow popupWindow;
    private AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用xutils库初始化
        x.view().inject(this);
        initView();

    }

    private void initView() {
        //设置输入框前的 文字的点击事件
        tvType.setOnClickListener(view -> {
            //当用户点击输入框前面的文字/下三角时，程序会到此处执行
            showList();
        });
        //设置输入框后面的按钮的点击事件
        btnSearch.setOnClickListener(view -> {
            //当用户点击输入框后面的向右的箭头区域时，程序会到此处执行
            openTransactionPage();
        });

        //对要展示的popupwindow进行初始化
        popupWindow = new ListPopupWindow(this);
        //Android中默认实现了一个简单的Adapter,比如：ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_popupwindow, Constant.TYPE_ARRAY);
        popupWindow.setAdapter(adapter);

        //尺寸
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        //ListPopupWindowClickEvent
        popupWindow.setOnItemClickListener((adapterView, view, position, l) -> {
            String type = Constant.TYPE_ARRAY[position];
            tvType.setText(type);
            etInput.setText(Constant.test_btc_tx_hash[position]);//测试hash
            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();//关闭popupwindow窗口
            }
        });
        btc_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBlockTransactionsList();

            }
        });
        try {
            showInfo();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //listView展示数据
        //① 确定要展示的数据内容
        //BTC、ETH、LTC
        //② 确定展示的模板（布局样式）
        //布局样式：TextView
        //③ 把数据设置到模板上
    }
    private void openBlockTransactionsList() {
        // TODO: 2023/3/28 根据用户的选择和输入内容，查询区块链中的交易详情信息，并展示出来
//        ResponseSerializable responseSerializable = new ResponseSerializable();
        Intent intent = new Intent(this, BlockTransactionsList.class);
//        intent.putExtra("type", tvType.getText().toString().trim());
//        intent.putExtra("txid", etInput.getText().toString().trim());
        //携带数据进行跳转
        startActivity(intent);

    }

    /**
     * 该方法用于根据用户的选择和输入内容，查询区块链中的交易详情信息，并展示出来
     */
    private void openTransactionPage() {
        // TODO: 2023/3/28 根据用户的选择和输入内容，查询区块链中的交易详情信息，并展示出来
//        ResponseSerializable responseSerializable = new ResponseSerializable();
        Intent intent = new Intent(this, TransactionDetailActivity.class);
        intent.putExtra("type", tvType.getText().toString().trim());
        intent.putExtra("txid", etInput.getText().toString().trim());
        //携带数据进行跳转
        startActivity(intent);

    }

    /**
     * 该方法用于弹出一个供用户选择的列表选项
     */
    private void showList() {
        popupWindow.setAnchorView(tvType);
        popupWindow.setDropDownGravity(Gravity.START | Gravity.BOTTOM);
        popupWindow.show();//将创建好的popupwindow展示出来。

    }

    private void showInfo() throws InterruptedException {
        RequestParams params = new RequestParams(Constant.BASE_URL + Constant.INFO);
        params.addHeader("Ok-Access-Key", Constant.API_KEY);
        params.addParameter("chainShortName", Constant.TYPE_ARRAY[0]);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Data_info data_info = Uitls.info_praseRespone(result).getData().get(0);
                System.out.println(data_info.getChainFullName());
                btc_tv_rank.setText(String.format("No.%s", data_info.getRank()));
                btc_tv_height.setText(data_info.getLastHeight());
                btc_tv_lastBlockTime.setText(Uitls.TimestampToTime(data_info.getLastBlockTime()));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        Thread.sleep(1000);
        params.clearParams();
        params.addParameter("chainShortName", Constant.TYPE_ARRAY[1]);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Data_info data_info = Uitls.info_praseRespone(result).getData().get(0);
                System.out.println(data_info.getChainFullName());
                eth_tv_rank.setText(String.format("No.%s", data_info.getRank()));
                eth_tv_height.setText(data_info.getLastHeight());
                eth_tv_lastBlockTime.setText(Uitls.TimestampToTime(data_info.getLastBlockTime()));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        Thread.sleep(1000);
        params.clearParams();
        params.addParameter("chainShortName", Constant.TYPE_ARRAY[2]);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Data_info data_info = Uitls.info_praseRespone(result).getData().get(0);
                System.out.println(data_info.getChainFullName());
                okc_tv_rank.setText(String.format("No.%s", data_info.getRank()));
                okc_tv_height.setText(data_info.getLastHeight());
                okc_tv_lastBlockTime.setText(Uitls.TimestampToTime(data_info.getLastBlockTime()));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        Thread.sleep(1000);
        params.clearParams();
        params.addParameter("chainShortName", Constant.TYPE_ARRAY[3]);
        x.http().get(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Data_info data_info = Uitls.info_praseRespone(result).getData().get(0);
                System.out.println(data_info.getChainFullName());
                tron_tv_rank.setText(String.format("No.%s", data_info.getRank()));
                tron_tv_height.setText(data_info.getLastHeight());
                tron_tv_lastBlockTime.setText(Uitls.TimestampToTime(data_info.getLastBlockTime()));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


    private void back() {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.finish();
    }

    private void searchError() {
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("错误！")//标题
                .setMessage("网络出错")//内容
                .setPositiveButton("确定", (dialogInterface, i) -> back())
                .create();
        alertDialog.show();
    }


}