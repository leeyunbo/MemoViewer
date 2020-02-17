package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.DetailContract;
import com.example.linetextbook.database.MemoDAO;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.database.MemoModel;
import com.example.linetextbook.view.DetailViewActivity;

public class DetailPresenter implements DetailContract.presenter {
    private DetailViewActivity view;
    private MemoModel model;

    public DetailPresenter(DetailViewActivity view) {
        this.model = new MemoModel(view.getApplicationContext(),this);
        this.view = view;
    }

    @Override
    public void notifyItemDelete() {
        view.backListView();
    }

    @Override
    public void requestDeleteMemo(MemoEntity memo) {
        model.doDeleteMemo(memo);
    }

}
