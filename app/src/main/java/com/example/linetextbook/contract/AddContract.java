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
        void addMemo(); //메모 추가 메서드, presenter에게 요청한다.
        void addAlbumImage(); //앨범 사진 추가 메서드
        void addCameraImage(); //카메라 사진 추가 메서드
        void addUrlImage(); //URL 사진 추가 메서드
        void backListView(); //다시 홈으로 복귀
        void changeImageRecyclerView(String path); //이미지 리사이클러 뷰 최신화
        void notifyDeleteImage(List<String> imageList);
    }

    interface presenter {
        void requestAddMemo(MemoEntity memo); //메모 추가 요청 메서드, Model에게 요청한다.
        void notifyAddSucceed(); //Model이 데이터 추가를 완료하면 해당 callback 메서드를 호출한다.
    }
}
