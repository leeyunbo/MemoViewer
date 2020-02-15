package com.example.linetextbook.Presenter;

import android.graphics.Bitmap;
import com.example.linetextbook.Contract.ListViewContract;
import com.example.linetextbook.DO.MemoDO;
import com.example.linetextbook.View.ListViewActivity;

import java.util.LinkedList;
import java.util.List;

public class ListViewPresenter implements ListViewContract.presenter {
    private ListViewActivity view;
    public ListViewPresenter(ListViewActivity view) {
        this.view = view;
    }

    @Override
    public LinkedList<MemoDO> requestMemoList() {
        return null;
    }
}
