package com.hbgcjsxy.chainexplorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class BlockListAdapter extends ArrayAdapter<BlockList> {
    public BlockListAdapter(@NonNull Context context, int resource, @NonNull List<BlockList> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BlockList blockList = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.block_item,parent,false);
        TextView textView =
        return super.getView(position, convertView, parent);
    }
}
