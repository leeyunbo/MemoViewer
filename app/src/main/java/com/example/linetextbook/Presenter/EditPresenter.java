package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.EditContract;
import com.example.linetextbook.dao.MemoDAO;
import com.example.linetextbook.entity.MemoEntity;
import com.example.linetextbook.view.EditViewActivity;

public class EditPresenter implements EditContract.presenter {
    private EditViewActivity view;
    private MemoDAO memoDAO;

    public EditPresenter(EditViewActivity view) {
        this.view = view;
        this.memoDAO = MemoDAO.getInstance();
    }

    @Override
    public boolean requestEditMemo() {
        return false;
    }

    @Override
    public MemoEntity requestEditMemoInform() {
        return null;
    }
}
