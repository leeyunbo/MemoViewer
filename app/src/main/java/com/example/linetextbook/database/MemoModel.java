package com.example.linetextbook.database;

import android.content.Context;

import com.example.linetextbook.Presenter.DetailPresenter;
import com.example.linetextbook.Presenter.EditPresenter;
import com.example.linetextbook.Presenter.ListPresenter;
import com.example.linetextbook.Presenter.AddPresenter;
import com.example.linetextbook.converters.ArrayConverters;

import java.util.List;

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

    public boolean doEditMemo(MemoEntity memo) {
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
        return false;
    }

    public boolean doAddMemo(MemoEntity memo) {
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
        return false;
    }

    public boolean doDeleteMemo(MemoEntity memo) {
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
        return false;
    }
}
