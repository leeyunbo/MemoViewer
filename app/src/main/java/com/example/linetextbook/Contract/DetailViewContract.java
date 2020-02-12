package com.example.linetextbook.Contract;

import android.view.View;

import com.example.linetextbook.DO.MemoDO;

import java.util.LinkedList;

public interface DetailViewContract {
    interface view {
        void deleteMemo(View view); //메모 삭제 메서드, presenter에게 요청
        void editMemo(View view); //메모 에딧 메서드, presenter에게 요청
    }

    interface presenter {
        boolean requestDeleteMemo(int id); //메모 삭제 요청 메서드, Model에게 요청
        boolean requestEditMemo(int id); //메모 수정 요청 메서드, Model에게 요청
    }


}
