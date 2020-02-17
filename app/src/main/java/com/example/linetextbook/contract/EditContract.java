package com.example.linetextbook.contract;

import com.example.linetextbook.database.MemoEntity;

public interface EditContract {
    interface view {
        void editMemo();
        void getEditMemoInform();
    }
    interface presenter {
        boolean requestEditMemo();
        MemoEntity requestEditMemoInform();
    }
}
