package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.DetailContract;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.database.MemoModel;
import com.example.linetextbook.view.DetailViewActivity;
/**
 * DetailViewActivity(View)와 MemoModel(Model) 사이의 요청을 처리해주는 Presenter
 *
 * @author 이윤복
 * @version 1.0
 */
public class DetailPresenter implements DetailContract.presenter {
    private DetailViewActivity mView;
    private MemoModel mModel;
    private boolean mSucceed = false;

    public DetailPresenter(DetailViewActivity view) {
        this.mModel = new MemoModel(view.getApplicationContext(),this);
        this.mView = view;
    }

    /**
     * View에게 요청이 도착하면 Model에게 메모의 삭제를 요청하는 메서드
     */
    @Override
    public boolean requestDeleteMemo(MemoEntity memo) {
        mModel.doDeleteMemo(memo);
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
    public void notifyItemDelete() {
        mSucceed = true;
        mView.backListView();
    }
}
