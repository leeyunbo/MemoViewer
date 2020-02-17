package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.EditContract;
import com.example.linetextbook.database.MemoDAO;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.view.EditViewActivity;

public class EditPresenter implements EditContract.presenter {
    private EditViewActivity view;

    public EditPresenter(EditViewActivity view) {
        this.view = view;
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
