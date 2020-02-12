package com.example.linetextbook.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.linetextbook.Contract.DetailViewContract;
import com.example.linetextbook.Contract.ListViewContract;
import com.example.linetextbook.Presenter.DetailViewPresenter;
import com.example.linetextbook.Presenter.ListViewPresenter;
import com.example.linetextbook.R;

public class ListViewActivity extends AppCompatActivity implements ListViewContract.view {
    private ListViewPresenter presenter;

    @Override
    public void createMemo() {

    }

    @Override
    public void getMemoList() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new ListViewPresenter(this);
    }
}
