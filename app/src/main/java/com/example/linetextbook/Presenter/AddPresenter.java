package com.example.linetextbook.Presenter;

import android.graphics.Bitmap;

import com.example.linetextbook.contract.AddContract;
import com.example.linetextbook.database.MemoDAO;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.database.MemoModel;
import com.example.linetextbook.view.addViewActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AddPresenter implements AddContract.presenter {
    addViewActivity view;
    MemoModel model;

    public AddPresenter(addViewActivity view) {
        this.view = view;
        this.model = new MemoModel(view.getApplicationContext(),this);
    }

    @Override
    public void requestAddMemo(MemoEntity memo) {
        model.doAddMemo(memo);
    }

    @Override
    public void addCallBack() {
        view.backListView();
    }
}
