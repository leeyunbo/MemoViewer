package com.example.linetextbook.contract;

import com.example.linetextbook.database.MemoEntity;

public interface EditContract {
    interface view {
        void editMemo();
        void getEditMemoInform();
        void backListView();
    }
    interface presenter {
        void requestEditMemo(MemoEntity memo);
        void notifyItemEdit();
        MemoEntity requestEditMemoInform();
    }
}
