package com.example.pariharsha.transactionviewer.mvp;

import com.example.pariharsha.transactionviewer.model.Transaction;

public class DisplayTransactionActivityContract {
    public interface View {
        void showData(Transaction data);
        void showError(String message);
        void showComplete();
        void showProgress();
        void hideProgress();
    }

    interface  Presenter {
        void loadData();
    }
}
