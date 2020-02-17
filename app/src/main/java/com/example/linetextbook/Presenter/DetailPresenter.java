package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.DetailContract;
import com.example.linetextbook.dao.MemoDAO;
import com.example.linetextbook.view.DetailViewActivity;

public class DetailPresenter implements DetailContract.presenter {
    private DetailViewActivity view;
    private MemoDAO memoDAO;

    @Override
    public boolean requestDeleteMemo(int id) {
        return false;
    }



    public DetailPresenter(DetailViewActivity view) {
        this.view = view;
        this.memoDAO = MemoDAO.getInstance();
    }
}
