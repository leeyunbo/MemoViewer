package com.example.linetextbook.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.linetextbook.entity.MemoEntity;

import java.util.ArrayList;

public interface MemoDAO {
    @Query("SELECT * FROM memo")
    ArrayList<MemoEntity> getMemoList();
    @Query("UPDATE memo SET title = :title, content = :content, image = :image WHERE :id")
    boolean doEditMemo(int id, String title, String content, String image);
    @Insert
    boolean doAddMemo(String title, String content, String image, String time);
    @Delete
    boolean doDeleteMemo(int id);
}
