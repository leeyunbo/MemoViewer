package com.example.linetextbook.database;

import android.content.Context;

import com.example.linetextbook.Presenter.DetailPresenter;
import com.example.linetextbook.Presenter.EditPresenter;
import com.example.linetextbook.Presenter.ListPresenter;
import com.example.linetextbook.Presenter.AddPresenter;
import com.example.linetextbook.converters.ArrayConverters;

import java.util.List;

/**
 * 스마트폰의 내부 저장소에 접근하여 요청을 처리하는 메서드들이 정의되어있는 Model
 * Room 라이브러리 사용
 *
 * @author 이윤복
 * @version 1.0
 */
public class MemoModel{
    private AddPresenter mAddPresenter;
    private DetailPresenter mDetailPresenter;
    private EditPresenter mEditPresenter;
    private ListPresenter mListPresenter;
    private MemoDAO mMemoDAO;
    private static MemoDatabase sDataBase;

    public MemoModel(Context context, AddPresenter presenter) {
        sDataBase = MemoDatabase.getInstance(context);
        this.mAddPresenter = presenter;
        this.mMemoDAO = sDataBase.memoDAO();
    }

    public MemoModel(Context context, DetailPresenter presenter) {
        sDataBase = MemoDatabase.getInstance(context);
        this.mDetailPresenter = presenter;
        this.mMemoDAO = sDataBase.memoDAO();
    }

    public MemoModel(Context context, EditPresenter presenter) {
        sDataBase = MemoDatabase.getInstance(context);
        this.mEditPresenter = presenter;
        this.mMemoDAO = sDataBase.memoDAO();
    }

    public MemoModel(Context context, ListPresenter presenter) {
        sDataBase = MemoDatabase.getInstance(context);
        this.mListPresenter = presenter;
        this.mMemoDAO = sDataBase.memoDAO();
    }

    /**
     * 모든 메모의 정보를 가져오는 메서드
     */
    public void getMemoList() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                List<MemoEntity> memoData = null;
                memoData = mMemoDAO.getMemoList();
                for(MemoEntity data : memoData) {
                    data.setImageList(ArrayConverters.convertStringToArray(data.getImageUrl()));
                }
                mListPresenter.notifyItemReceived(memoData);
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    /**
     * 메모를 수정하는 메서드
     *
     * @param memo 수정된 메모의 정보를 담고있는 DO 객체
     * @return
     */
    public void doEditMemo(MemoEntity memo) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String stringImageUrl = ArrayConverters.convertArrayToString(memo.getImageList());
                memo.setImageUrl(stringImageUrl);
                mMemoDAO.doEditMemo(memo);
                mEditPresenter.notifyItemEdit();
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    /**
     * 메모를 추가하는 메서드
     *
     * @param memo 추가할 메모의 정보를 담고있는 DO 객체
     * @return
     */
    public void doAddMemo(MemoEntity memo) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(memo.getImageList() != null) {
                    String stringImageUrl = ArrayConverters.convertArrayToString(memo.getImageList());
                    memo.setImageUrl(stringImageUrl);
                }
                mMemoDAO.doAddMemo(memo);
                mAddPresenter.notifyAddSucceed();
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }

    /**
     * 메모를 삭제하는 메서드
     *
     * @param memo 삭제할 메모의 정보를 담고있는 DO 객체
     */
    public void doDeleteMemo(MemoEntity memo) {
        Runnable r = new Runnable() {
            @Override
            public void run()
            {
                int a = mMemoDAO.doDeleteMemo(memo);
                System.out.println(a);
                mDetailPresenter.notifyItemDelete();
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }
}
