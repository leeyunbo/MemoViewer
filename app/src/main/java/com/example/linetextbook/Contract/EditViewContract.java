package com.example.linetextbook.Contract;

public interface EditViewContract {
    interface view {
        void editMemo();
    }
    interface presenter {
        boolean requestEditMemo();
    }
}
