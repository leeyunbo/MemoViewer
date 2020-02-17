package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.DetailContract;
import com.example.linetextbook.database.MemoDAO;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.view.DetailViewActivity;

public class DetailPresenter implements DetailContract.presenter {
    private DetailViewActivity view;

    @Override
    public boolean requestDeleteMemo(MemoEntity memo) {
        return false;
    }

    public DetailPresenter(DetailViewActivity view) {
        this.view = view;
    }
}
