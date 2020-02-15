package com.example.linetextbook.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.linetextbook.Contract.DetailViewContract;
import com.example.linetextbook.Presenter.DetailViewPresenter;
import com.example.linetextbook.R;

public class DetailViewActivity extends AppCompatActivity implements DetailViewContract.view {
    private DetailViewPresenter presenter;
    @Override
    public void deleteMemo(View view) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        presenter = new DetailViewPresenter(this);
    }
}
