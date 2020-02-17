package com.example.linetextbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.linetextbook.contract.AddContract;
import com.example.linetextbook.Presenter.addPresenter;
import com.example.linetextbook.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class addViewActivity extends AppCompatActivity implements AddContract.view {
    private addPresenter presenter; //presenter

    @BindView(R.id.contentEditText) private EditText contentEditText;
    @BindView(R.id.titleEditText) private EditText titleEditText;
    @BindView(R.id.createButton) private Button createButton;
    @BindView(R.id.imageAddButton) private Button imageAddButton;
    @BindView(R.id.refreshButton) private Button refreshButton;

    @Override
    public void addMemo() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_view);
        ButterKnife.bind(this);
        this.presenter = new addPresenter(this);
    }
}
