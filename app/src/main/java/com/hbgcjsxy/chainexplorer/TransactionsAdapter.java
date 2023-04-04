package com.hbgcjsxy.chainexplorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TransactionsAdapter extends ArrayAdapter<TransactionList> {
    public TransactionsAdapter(@NonNull Context context, int resource, @NonNull List<TransactionList> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TransactionList transactionList = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.transactions_item,parent,false);
        TextView transaction_hash = view.findViewById(R.id.transaction_hash);
        TextView transaction_info = view.findViewById(R.id.transaction_info);
        transaction_hash.setText(String.format("%s...%s", transactionList.getTxid().substring(0, 6), transactionList.getTxid().substring(transactionList.getTxid().length()-4)));
        transaction_info.setText(String.format("%s %s", transactionList.getAmount(), transactionList.getTransactionSymbol()));
        return view;
    }
}
