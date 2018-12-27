package com.example.pariharsha.transactionviewer;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pariharsha.transactionviewer.appcomponent.ApplicationComponent;
import com.example.pariharsha.transactionviewer.appcomponent.DaggerDisplayTransactionComponent;
import com.example.pariharsha.transactionviewer.appcomponent.DisplayTransactionComponent;
import com.example.pariharsha.transactionviewer.model.Transaction;
import com.example.pariharsha.transactionviewer.modules.DisplayTransactionContextModule;
import com.example.pariharsha.transactionviewer.modules.DisplayTransactionMvpModule;
import com.example.pariharsha.transactionviewer.mvp.DisplayTransactionActivityContract;
import com.example.pariharsha.transactionviewer.mvp.PresenterImpl;
import com.example.pariharsha.transactionviewer.qualifier.ActivityContext;
import com.example.pariharsha.transactionviewer.qualifier.ApplicationContext;

import javax.inject.Inject;

public class DisplayTransactionActivity extends AppCompatActivity implements DisplayTransactionActivityContract.View,RecyclerViewAdapter.ClickListener{
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    DisplayTransactionComponent displayTransactionComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    PresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_transaction);

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        displayTransactionComponent = DaggerDisplayTransactionComponent.builder()
                .displayTransactionContextModule(new DisplayTransactionContextModule(this))
                .displayTransactionMvpModule(new DisplayTransactionMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        displayTransactionComponent.injectDisplayTransactionActivity(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.addItemDecoration(new DividerItemDecoration(activityContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(recyclerViewAdapter);
        progressBar = findViewById(R.id.progressBar);

        presenter.loadData();


    }

    @Override
    public void launchIntent(String name) {

        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();

        // startActivity(new Intent(activityContext, DetailActivity.class).putExtra("name", name));
    }
    @Override
    public void showData(Transaction data) {
        recyclerViewAdapter.setData(data);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DisplayTransactionActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
}
