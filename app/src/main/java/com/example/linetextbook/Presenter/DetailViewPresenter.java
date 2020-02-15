package com.example.linetextbook.Presenter;

import com.example.linetextbook.Contract.DetailViewContract;
import com.example.linetextbook.DAO.MemoDAO;
import com.example.linetextbook.DO.MemoDO;
import com.example.linetextbook.View.DetailViewActivity;
import com.example.linetextbook.View.ListViewActivity;

import java.util.LinkedList;

public class DetailViewPresenter implements DetailViewContract.presenter {
    private DetailViewActivity view;
    private MemoDAO memoDAO;

    @Override
    public boolean requestDeleteMemo(int id) {
        return false;
    }



    public DetailViewPresenter(DetailViewActivity view) {
        this.view = view;
        this.memoDAO = new MemoDAO();
    }
}
