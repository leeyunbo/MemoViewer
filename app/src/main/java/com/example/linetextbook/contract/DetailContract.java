package com.example.linetextbook.contract;

import android.view.View;

import com.example.linetextbook.database.MemoEntity;

public interface DetailContract {
    interface view {
        void deleteMemo(MemoEntity memo); //메모 삭제 메서드, presenter에게 요청
    }

    interface presenter {
        boolean requestDeleteMemo(MemoEntity memo); //메모 삭제 요청 메서드, Model에게 요청
    }


}
