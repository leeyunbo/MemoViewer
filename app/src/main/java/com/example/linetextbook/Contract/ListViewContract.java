package com.example.linetextbook.Contract;

import android.graphics.Bitmap;

import com.example.linetextbook.DO.MemoDO;

import java.util.LinkedList;

public interface ListViewContract {
    interface view {
        void addMemo(); //메모 추가 메서드, prsenter에게 요청한다.
        void showMemoList(); //메모리스트 가져오기 메서드, presenter에게 요청한다.
    }

    interface presenter {
        boolean requestAddMemo(String title, String content, LinkedList<Bitmap> image); //메모 추가 요청 메서드, Model에게 요청한다.
        LinkedList<MemoDO> requestMemoList(); //메모리스트 가져오기 요청 메서드, Model에게 요청한다.
    }
}
