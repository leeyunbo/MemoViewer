package com.example.linetextbook.Presenter;

import com.example.linetextbook.contract.ListContract;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.database.MemoModel;
import com.example.linetextbook.view.ListViewActivity;
import java.util.List;

/**
 * ListViewActivity(View)와 MemoModel(Model) 사이의 요청을 처리해주는 Presenter
 *
 * @author 이윤복
 * @version 1.0
 */
public class ListPresenter implements ListContract.presenter {
    private ListViewActivity view;
    private MemoModel model;


    public ListPresenter(ListViewActivity view) {
        this.view = view;
        this.model = new MemoModel(view.getApplicationContext(),this);
    }

    /**
     * View에게 요청이 도착하면 Model에게 모든 메모의 데이터를 요청하는 메서드
     */
    @Override
    public void requestMemoList() {
        model.getMemoList();
    }

    /**
     * Model에게 한 요청이 종료된 후, Model이 호출하는 콜백 메서드
     * view에게 UI 변경 요청을 전송한다.
     *
     * @param memoData 사용자가 등록한 모든 메모의 DO를 담고있는 List
     */
    @Override
    public void notifyItemReceived(List<MemoEntity> memoData) {
        view.changeRecyclerView(memoData);
    }
}
