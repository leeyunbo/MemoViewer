package com.example.linetextbook.contract;

import com.example.linetextbook.database.MemoEntity;

import java.util.List;

/**
 * EditViewActivity(View)와 EditPresenter에 대한 메서드를 담고 있는 인터페이스
 */

public interface EditContract {
    interface view {
        void editMemo();
        void getEditMemoInform();
        void backListView();
        void addAlbumImage();
        void addCameraImage();
        void addUrlImage();
        void changeImageRecyclerView(String path);
        void notifyDeleteImage(List<String> imageList);
    }
    interface presenter {
        boolean requestEditMemo(MemoEntity memo);
        void notifyItemEdit();
    }
}
