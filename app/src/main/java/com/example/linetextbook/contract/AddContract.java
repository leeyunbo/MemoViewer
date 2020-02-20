package com.example.linetextbook.contract;

import android.graphics.Bitmap;

import com.example.linetextbook.database.MemoEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * AddViewActivity(View)와 AddPresenter에 대한 메서드를 담고 있는 인터페이스
 */

public interface AddContract {
    interface view {
        void addMemo();
        void addAlbumImage();
        void addCameraImage();
        void addUrlImage();
        void backListView();
        void changeImageRecyclerView(String path);
        void notifyDeleteImage(List<String> imageList);
    }

    interface presenter {
        void requestAddMemo(MemoEntity memo);
        void notifyAddSucceed(); 
    }
}
