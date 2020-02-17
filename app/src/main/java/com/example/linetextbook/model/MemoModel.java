package com.example.linetextbook.model;

import android.content.Context;

import com.example.linetextbook.dao.MemoDAO;
import com.example.linetextbook.database.MemoDatabase;
import com.example.linetextbook.entity.MemoEntity;

import java.util.ArrayList;

public class MemoModel implements MemoDAO {
    @Override
    public ArrayList<MemoEntity> getMemoList() {
        return null;
    }

    @Override
    public boolean doEditMemo(int id, String title, String content, String image) {
        return false;
    }

    @Override
    public boolean doAddMemo(String title, String content, String image, String time) {
        return false;
    }

    @Override
    public boolean doDeleteMemo(int id) {
        return false;
    }
}
