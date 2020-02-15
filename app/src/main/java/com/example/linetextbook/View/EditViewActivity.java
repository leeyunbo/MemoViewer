package com.example.linetextbook.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.linetextbook.Contract.EditViewContract;
import com.example.linetextbook.R;

public class EditViewActivity extends AppCompatActivity implements EditViewContract.view
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);
    }

    @Override
    public void editMemo() {

    }
}
