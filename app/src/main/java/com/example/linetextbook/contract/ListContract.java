package com.example.linetextbook.contract;

import com.example.linetextbook.database.MemoEntity;

import java.util.LinkedList;
import java.util.List;

public interface ListContract {
    interface view {
        void showMemoList(); //메모리스트 가져오기 메서드, presenter에게 요청한다.
        void changeRecyclerView(List<MemoEntity> memoData);
    }

    interface presenter {
        void requestMemoList(); //메모리스트 가져오기 요청 메서드, Model에게 요청한다.
        void notifyItemReceived(List<MemoEntity> memoData);
    }
}
