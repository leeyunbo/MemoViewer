package com.example.linetextbook.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.linetextbook.Contract.ListViewContract;
import com.example.linetextbook.Presenter.ListViewPresenter;
import com.example.linetextbook.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewActivity extends AppCompatActivity implements ListViewContract.view {
    private ListViewPresenter presenter;
    @BindView(R.id.memoCount) TextView memoCount;
    @BindView(R.id.addButton) ImageView addButton;
    @BindView(R.id.listView) ListView listView;

    @Override
    public void showMemoList() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);
        presenter = new ListViewPresenter(this);
    }
}
