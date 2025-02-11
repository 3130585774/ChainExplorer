package com.hbgcjsxy.chainexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hbgcjsxy.chainexplorer.databinding.ActivityMainBinding;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ListPopupWindow popupWindow;
    private final Map<String, Handler> handlerMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        setupCryptoBlocks();
    }

    private void initView() {
        // 选择框
        binding.tvType.setOnClickListener(v -> showList());

        // 搜索按钮
        binding.btnSearch.setOnClickListener(v -> {
            String txid = binding.etHash.getText().toString().trim();
            if (!txid.isEmpty()) {
                openTransactionPage(txid);
            } else {
                showAlertDialog();
            }
        });
        SwipeRefreshLayout swipeRefresh = binding.swipeRefresh;

        swipeRefresh.setOnRefreshListener(() -> {
            // 调用刷新数据的方法
            fetchBlockchainInfo();
            // 2秒后停止刷新动画
            swipeRefresh.postDelayed(() -> {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
            }, 2000);

        });


        // 初始化选择框
        setupPopupWindow();

        // 绑定点击事件
        for (String type : Constant.TYPE_ARRAY) {
            getBlockLayout(type).setOnClickListener(v -> openBlockTransactionsList(type));
        }

        fetchBlockchainInfo();
    }

    private void setupPopupWindow() {
        popupWindow = new ListPopupWindow(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_popupwindow, Constant.TYPE_ARRAY);
        popupWindow.setAdapter(adapter);
        popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        popupWindow.setOnItemClickListener((adapterView, view, position, id) -> {
            binding.tvType.setText(Constant.TYPE_ARRAY[position]);
            popupWindow.dismiss();
        });
    }

    private void showList() {
        popupWindow.setAnchorView(binding.tvType);
        popupWindow.setDropDownGravity(Gravity.START | Gravity.BOTTOM);
        popupWindow.show();
    }

    private void openTransactionPage(String txid) {
        Intent intent = new Intent(this, TransactionDetailActivity.class);
        intent.putExtra("type", binding.tvType.getText().toString().trim());
        intent.putExtra("txid", txid);
        startActivity(intent);
    }

    private void openBlockTransactionsList(String type) {
        Intent intent = new Intent(this, BlockTransactionsListActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    private void fetchBlockchainInfo() {
        for (String type : Constant.TYPE_ARRAY) {
            Map<String, String> params = new HashMap<>();
            params.put("chainShortName", type);
            Uitls.HttpsGetX(getHandler(type), Constant.BASE_URL + Constant.INFO, params);

        }
    }

    private Handler getHandler(String type) {
        if (!handlerMap.containsKey(type)) {
            handlerMap.put(type, new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    JSONObject jsonObject = (JSONObject) msg.obj;
                    Data_info data = Uitls.info_parseRespond(jsonObject).getData().get(0);

                    getRankTextView(type).setText(String.format("No.%s", data.getRank()));
                    getHeightTextView(type).setText(data.getLastHeight());
                    getLastBlockTimeTextView(type).setText(Uitls.TimestampToTime(data.getLastBlockTime()));
                }
            });
        }
        return handlerMap.get(type);
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("错误！")
                .setMessage("请输入交易地址")
                .setPositiveButton("确定", null)
                .create()
                .show();
    }

    private void setupCryptoBlocks() {
        setupBlock(binding.btcBlock.getRoot(), "BTC", "No.1", "783152", "2023年03月30 16:28");
        setupBlock(binding.ethBlock.getRoot(), "ETH", "No.2", "16938883", "2023年03月30 16:30");
        setupBlock(binding.okcBlock.getRoot(), "OKC", "No.16", "18396367", "2023年03月30 16:28");
        setupBlock(binding.tronBlock.getRoot(), "TRON", "No.16", "49850841", "2023年03月30 16:30");
        getBlockLogoImageView("BTC").setImageResource(R.drawable.btc_logo);
        getBlockLogoImageView("ETH").setImageResource(R.drawable.eth_logo);
        getBlockLogoImageView("OKC").setImageResource(R.drawable.okc_logo);
        getBlockLogoImageView("TRON").setImageResource(R.drawable.tron_logo);
    }

    private void setupBlock(LinearLayout blockView, String name, String rank, String height, String time) {
        TextView tvRank = blockView.findViewById(R.id.tv_block_rank);
        TextView tvHeight = blockView.findViewById(R.id.tv_block_height);
        TextView tvTime = blockView.findViewById(R.id.tv_block_last_time);
        getBlockNameTextView(name).setText(name);


        tvRank.setText(rank);
        tvHeight.setText(height);
        tvTime.setText(time);
    }

    private LinearLayout getBlockLayout(String type) {
        switch (type) {
            case "BTC":
                return binding.btcBlock.getRoot();
            case "ETH":
                return binding.ethBlock.getRoot();
            case "OKC":
                return binding.okcBlock.getRoot();
            case "TRON":
                return binding.tronBlock.getRoot();
            default:
                throw new IllegalArgumentException("Unsupported block type: " + type);
        }
    }

    private TextView getRankTextView(String type) {
        View blockView = getBlockView(type);
        if (blockView == null) {
            throw new IllegalArgumentException("Unsupported block type: " + type);
        }
        return blockView.findViewById(R.id.tv_block_rank);
    }


    private TextView getHeightTextView(String type) {
        View blockView = getBlockView(type);
        if (blockView == null) {
            throw new IllegalArgumentException("Unsupported block type: " + type);
        }
        return blockView.findViewById(R.id.tv_block_height);
    }


    private TextView getLastBlockTimeTextView(String type) {
        View blockView = getBlockView(type);
        if (blockView == null) {
            throw new IllegalArgumentException("Unsupported block type: " + type);
        }
        return blockView.findViewById(R.id.tv_block_last_time);
    }
    private View getBlockView(String type) {
        switch (type) {
            case "BTC":
                return binding.getRoot().findViewById(R.id.btc_block);
            case "ETH":
                return binding.getRoot().findViewById(R.id.eth_block);
            case "OKC":
                return binding.getRoot().findViewById(R.id.okc_block);
            case "TRON":
                return binding.getRoot().findViewById(R.id.tron_block);
            default:
                return null;
        }
    }
    private ImageView getBlockLogoImageView(String type) {
        View blockView = getBlockView(type);
        if (blockView == null) {
            throw new IllegalArgumentException("Unsupported block type: " + type);
        }
        return blockView.findViewById(R.id.iv_block_logo);
    }
    private TextView getBlockNameTextView(String type) {
        View blockView = getBlockView(type);
        if (blockView == null) {
            throw new IllegalArgumentException("Unsupported block type: " + type);
        }
        return blockView.findViewById(R.id.tv_block_name);
    }



}
