package com.example.linetextbook.Presenter;

import android.graphics.Bitmap;

import com.example.linetextbook.contract.AddContract;
import com.example.linetextbook.dao.MemoDAO;
import com.example.linetextbook.view.addViewActivity;

import java.util.LinkedList;

public class addPresenter implements AddContract.presenter {
    addViewActivity view;
    MemoDAO memoDAO;

    public addPresenter(addViewActivity view) {
        this.view = view;
        this.memoDAO = MemoDAO.getInstance();
    }

    @Override
    public boolean requestAddMemo(String title, String content, LinkedList<Bitmap> image) {
        return false;
    }
}
