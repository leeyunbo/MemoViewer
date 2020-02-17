package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.ListContract;
import com.example.linetextbook.dao.MemoDAO;
import com.example.linetextbook.entity.MemoEntity;
import com.example.linetextbook.database.MemoDatabase;
import com.example.linetextbook.view.ListViewActivity;

import java.util.LinkedList;

public class ListPresenter implements ListContract.presenter {
    private ListViewActivity view;


    public ListPresenter(ListViewActivity view) {
        this.view = view;
    }

    @Override
    public LinkedList<MemoEntity> requestMemoList() {
    }
}
