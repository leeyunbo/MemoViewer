package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.ListContract;
import com.example.linetextbook.database.MemoDAO;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.database.MemoDatabase;
import com.example.linetextbook.database.MemoModel;
import com.example.linetextbook.view.ListViewActivity;

import java.util.LinkedList;
import java.util.List;

public class ListPresenter implements ListContract.presenter {
    private ListViewActivity view;
    private MemoModel model;


    public ListPresenter(ListViewActivity view) {
        this.view = view;
        this.model = new MemoModel(view.getApplicationContext(),this);
    }

    @Override
    public void notifyItemReceived(List<MemoEntity> memoData) {
        view.changeRecyclerView(memoData);
    }

    @Override
    public void requestMemoList() {
        model.getMemoList();
    }
}
