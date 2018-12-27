package com.example.pariharsha.transactionviewer;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pariharsha.transactionviewer.model.Transaction;
import com.example.pariharsha.transactionviewer.model.TransactionData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private  RecyclerViewAdapter.ClickListener clickListener;
    private List<TransactionData> transactionList;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener){
        this.clickListener = clickListener;
        transactionList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.result.setText(transactionList.get(position).result);
        holder.time.setText(transactionList.get(position).time);
        holder.balance.setText(transactionList.get(position).balance);
        String resultValue = holder.result.getText().toString();
        if (resultValue.startsWith("-")){
            holder.status.setText(R.string.money_sent);
            holder.status.setTextColor(Color.RED);
        } else {
            holder.status.setText(R.string.money_received);
            holder.status.setTextColor(Color.GREEN);
        }





    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView result;
        private TextView balance;
        private TextView time;
        private TextView status;
        private ConstraintLayout constraintLayoutContaincrer;

        ViewHolder(View itemView) {
            super(itemView);

            result = itemView.findViewById(R.id.result);
            balance = itemView.findViewById(R.id.balance);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);

            constraintLayoutContaincrer = itemView.findViewById(R.id.constraintLayout);
            constraintLayoutContaincrer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String resultValue = transactionList.get(getAdapterPosition()).result;
                    if (resultValue.startsWith("-")) {
                        clickListener.launchIntent("Bit coin " + transactionList.get(getAdapterPosition()).result + " is sent from the wallet");
                    }else {
                        clickListener.launchIntent("Bit coin " + transactionList.get(getAdapterPosition()).result + " is sent to the wallet");

                    }
                }
            });

        }
    }
        public interface ClickListener {
            void launchIntent(String name);
        }

        public void setData(Transaction data) {

            List<TransactionData> transactions = data.getTransactions();

         for (int i=0;i< data.getTransactions().size(); i++)
         {
             TransactionData txnData = new TransactionData();
             txnData.setBalance(transactions.get(i).getBalance());
             txnData.setTime(getDate(Long.parseLong(transactions.get(i).getTime())* 1000L));
             txnData.setResult(convertFromSatoshiToBTC(transactions.get(i).getResult()));
             transactionList.add(txnData);

             }

            notifyDataSetChanged();
        }

        public String convertFromSatoshiToBTC(String value){
          double result = Double.valueOf(value) / 10000000;
          String btc = String.valueOf(result);
          return btc;
        }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }
    }

