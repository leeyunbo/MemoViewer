package com.example.linetextbook.contract;

import android.view.View;

import com.example.linetextbook.database.MemoEntity;

import java.util.List;

/**
 * DetailViewActivity(View)와 DetailPresenter에 대한 메서드를 담고 있는 인터페이스
 */

public interface DetailContract {
    interface view {
        void deleteMemo(); //메모 삭제 메서드, presenter에게 요청
        void showMemoDetail(); //클릭한 메모의 상세 내용을 출력한다.
        void backListView(); //다시 홈으로 복귀
    }

    interface presenter {
        void requestDeleteMemo(MemoEntity memo); //메모 삭제 요청 메서드, Model에게 요청
        void notifyItemDelete(); //메모 삭제 완료시 호출되는 콜백 메서드
    }


}
