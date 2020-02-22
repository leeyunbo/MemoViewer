package com.example.linetextbook.contract;

import android.view.View;

import com.example.linetextbook.database.MemoEntity;

import java.util.List;

/**
 * DetailViewActivity(View)와 DetailPresenter에 대한 메서드를 담고 있는 인터페이스
 */

public interface DetailContract {
    interface view {
        void deleteMemo();
        void showMemoDetail();
        void backListView();
    }

    interface presenter {
        boolean requestDeleteMemo(MemoEntity memo);
        void notifyItemDelete(); 
    }


}
