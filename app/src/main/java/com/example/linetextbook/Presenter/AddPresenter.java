package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.AddContract;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.database.MemoModel;
import com.example.linetextbook.view.AddViewActivity;

/**
 * AddViewActivity(View)와 MemoModel(Model) 사이의 요청을 처리해주는 Presenter
 *
 * @author 이윤복
 * @version 1.0
 */
public class AddPresenter implements AddContract.presenter {
    private AddViewActivity mView;
    private MemoModel mModel;
    private boolean mSucceed = false;

    public AddPresenter(AddViewActivity view) {
        this.mView = view;
        this.mModel = new MemoModel(view.getApplicationContext(),this);
    }

    /**
     * View에게 요청이 도착하면 Model에게 메모의 추가를 요청하는 메서드
     */
    @Override
    public boolean requestAddMemo(MemoEntity memo) {
        mModel.doAddMemo(memo);
        if(mSucceed == true) {
            mSucceed = false;
            return true;
        }
        return mSucceed;
    }

    /**
     * Model에게 한 요청이 종료된 후, Model이 호출하는 콜백 메서드
     */
    @Override
    public void notifyAddSucceed() {
        mSucceed = true;
        mView.backListView();
    }
}
