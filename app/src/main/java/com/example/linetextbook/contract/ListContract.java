package com.example.linetextbook.contract;

import com.example.linetextbook.entity.MemoEntity;

import java.util.LinkedList;

public interface ListContract {
    interface view {
        void showMemoList(); //메모리스트 가져오기 메서드, presenter에게 요청한다.
    }

    interface presenter {
        LinkedList<MemoEntity> requestMemoList(); //메모리스트 가져오기 요청 메서드, Model에게 요청한다.
    }
}
