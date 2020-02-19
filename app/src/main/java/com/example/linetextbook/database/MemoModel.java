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
 *
 * @author 이윤복
 * @version 1.0
 */
public class MemoModel{
    private AddPresenter addPresenter;
    private DetailPresenter detailPresenter;
    private EditPresenter editPresenter;
    private ListPresenter listPresenter;
    private MemoDAO memoDAO;

    private static MemoDatabase db;
    public MemoModel(Context context, AddPresenter presenter) {
        db = MemoDatabase.getInstance(context);
        this.addPresenter = presenter;
        this.memoDAO = db.memoDAO();
    }

    public MemoModel(Context context, DetailPresenter presenter) {
        db = MemoDatabase.getInstance(context);
        this.detailPresenter = presenter;
        this.memoDAO = db.memoDAO();
    }

    public MemoModel(Context context, EditPresenter presenter) {
        db = MemoDatabase.getInstance(context);
        this.editPresenter = presenter;
        this.memoDAO = db.memoDAO();
    }

    public MemoModel(Context context, ListPresenter presenter) {
        db = MemoDatabase.getInstance(context);
        this.listPresenter = presenter;
        this.memoDAO = db.memoDAO();
    }

    /**
     * 모든 메모의 정보를 가져오는 메서드
     */
    public void getMemoList() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                List<MemoEntity> memoData = null;
                memoData = memoDAO.getMemoList();
                for(MemoEntity data : memoData) {
                    data.setImageList(ArrayConverters.convertStringToArray(data.getImageUrl()));
                }
                listPresenter.notifyItemReceived(memoData);
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
                memoDAO.doEditMemo(memo);
                editPresenter.notifyItemEdit();
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
                memoDAO.doAddMemo(memo);
                addPresenter.notifyAddSucceed();
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
                int a = memoDAO.doDeleteMemo(memo);
                System.out.println(a);
                detailPresenter.notifyItemDelete();
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }
}
