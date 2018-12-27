package com.example.pariharsha.transactionviewer.model;

import com.google.gson.annotations.SerializedName;

public class TransactionData {
    @SerializedName("result")
    public String result;
    @SerializedName("time")
    public String time;
    @SerializedName("balance")
    public String balance;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
