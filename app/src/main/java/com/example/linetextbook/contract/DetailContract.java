package com.example.linetextbook.contract;

import android.view.View;

public interface DetailContract {
    interface view {
        void deleteMemo(View view); //메모 삭제 메서드, presenter에게 요청
    }

    interface presenter {
        boolean requestDeleteMemo(int id); //메모 삭제 요청 메서드, Model에게 요청
    }


}
