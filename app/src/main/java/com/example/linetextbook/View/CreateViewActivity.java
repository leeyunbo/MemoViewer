package com.example.linetextbook.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.linetextbook.Contract.CreateViewContract;
import com.example.linetextbook.Presenter.CreateViewPresenter;
import com.example.linetextbook.R;

public class CreateViewActivity extends AppCompatActivity implements CreateViewContract.view {
    private CreateViewPresenter presenter;
    @Override
    public void addMemo() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_view);
        this.presenter = new CreateViewPresenter(this);
    }
}
