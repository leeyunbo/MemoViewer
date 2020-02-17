package com.example.linetextbook.contract;

import android.graphics.Bitmap;

import com.example.linetextbook.database.MemoEntity;

import java.util.LinkedList;
import java.util.List;

public interface AddContract {
    interface view {
        void addMemo(); //메모 추가 메서드, presenter에게 요청한다.
        void addImage(); //사진 추가 메서드, List에 Bitmap 추가
        void backListView();
    }

    interface presenter {
        void requestAddMemo(MemoEntity memo); //메모 추가 요청 메서드, Model에게 요청한다.
        void addCallBack(); //Model이 데이터 추가를 완료하면 해당 callback 메서드를 호출한다.
    }
}
