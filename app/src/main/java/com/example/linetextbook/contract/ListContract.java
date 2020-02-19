package com.example.linetextbook.contract;

import com.example.linetextbook.database.MemoEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * ListViewActivity(View)와 ListPresenter에 대한 메서드를 담고 있는 인터페이스
 */

public interface ListContract {
    interface view {
        void showMemoList(); //메모리스트 가져오기 메서드, presenter에게 요청한다.
        void changeRecyclerView(List<MemoEntity> memoData); //메모리스트 가져오기 완료 후, 리사이클러 뷰에 뿌려줌
    }

    interface presenter {
        void requestMemoList(); //메모리스트 가져오기 요청 메서드, Model에게 요청한다.
        void notifyItemReceived(List<MemoEntity> memoData); //메모리스트 가져오기 완료 콜백 메서드
    }
}
