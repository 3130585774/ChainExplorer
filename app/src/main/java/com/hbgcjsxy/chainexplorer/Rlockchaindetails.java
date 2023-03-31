package com.hbgcjsxy.chainexplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@SuppressLint("NonConstantResourceId")
@ContentView(R.layout.activity_blockchaindetails)
public class Rlockchaindetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView() {
    //TODO 详情
    }
}