package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.EditContract;
import com.example.linetextbook.database.MemoDAO;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.database.MemoModel;
import com.example.linetextbook.view.EditViewActivity;

public class EditPresenter implements EditContract.presenter {
    private EditViewActivity view;
    private MemoModel model;

    public EditPresenter(EditViewActivity view) {
        this.view = view;
        this.model = new MemoModel(view.getApplicationContext(),this);
    }

    @Override
    public void requestEditMemo(MemoEntity memo) {
        model.doEditMemo(memo);
    }

    @Override
    public void notifyItemEdit() {
        view.backListView();
    }

    @Override
    public MemoEntity requestEditMemoInform() {
        return null;
    }
}
