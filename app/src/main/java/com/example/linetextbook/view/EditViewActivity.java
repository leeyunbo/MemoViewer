package com.example.linetextbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.linetextbook.contract.EditContract;
import com.example.linetextbook.Presenter.EditPresenter;
import com.example.linetextbook.R;

public class EditViewActivity extends AppCompatActivity implements EditContract.view
{
    private EditPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);
        this.presenter = new EditPresenter(this);
        getEditMemoInform();
    }

    @Override
    public void getEditMemoInform() {

    }

    @Override
    public void editMemo() {

    }
}
