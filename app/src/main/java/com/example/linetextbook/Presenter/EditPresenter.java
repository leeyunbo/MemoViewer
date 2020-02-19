package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.EditContract;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.database.MemoModel;
import com.example.linetextbook.view.EditViewActivity;
/**
 * EditViewActivity(View)와 MemoModel(Model) 사이의 요청을 처리해주는 Presenter
 *
 * @author 이윤복
 * @version 1.0
 */
public class EditPresenter implements EditContract.presenter {
    private EditViewActivity view;
    private MemoModel model;

    public EditPresenter(EditViewActivity view) {
        this.view = view;
        this.model = new MemoModel(view.getApplicationContext(),this);
    }

    /**
     * View에게 요청이 도착하면 Model에게 메모의 정보 변경을 요청하는 메서드
     */
    @Override
    public void requestEditMemo(MemoEntity memo) {
        model.doEditMemo(memo);
    }

    /**
     * Model에게 한 요청이 종료된 후, Model이 호출하는 콜백 메서드
     */
    @Override
    public void notifyItemEdit() {
        view.backListView();
    }
}
