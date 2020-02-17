package com.example.linetextbook.database;

import android.graphics.Bitmap;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.linetextbook.database.MemoEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MemoDAO {
    @Query("SELECT * FROM memo")
    List<MemoEntity> getMemoList();
    @Update
    int doEditMemo(MemoEntity memo);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void doAddMemo(MemoEntity memo);
    @Delete
    int doDeleteMemo(MemoEntity memo);
}
