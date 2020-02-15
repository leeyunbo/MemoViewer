package com.example.linetextbook.Presenter;

import android.graphics.Bitmap;

import com.example.linetextbook.Contract.CreateViewContract;
import com.example.linetextbook.DAO.MemoDAO;
import com.example.linetextbook.View.CreateViewActivity;

import java.util.LinkedList;

public class CreateViewPresenter implements CreateViewContract.presenter {
    CreateViewActivity view;
    MemoDAO memoDAO;

    public CreateViewPresenter(CreateViewActivity view) {
        this.view = view;
        memoDAO = new MemoDAO();
    }

    @Override
    public boolean requestAddMemo(String title, String content, LinkedList<Bitmap> image) {
        return false;
    }
}
