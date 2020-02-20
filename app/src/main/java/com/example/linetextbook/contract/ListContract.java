package com.example.linetextbook.contract;

import com.example.linetextbook.database.MemoEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * ListViewActivity(View)와 ListPresenter에 대한 메서드를 담고 있는 인터페이스
 */

public interface ListContract {
    interface view {
        void showMemoList();
        void changeRecyclerView(List<MemoEntity> memoData);
    }

    interface presenter {
        void requestMemoList();
        void notifyItemReceived(List<MemoEntity> memoData);
    }
}
