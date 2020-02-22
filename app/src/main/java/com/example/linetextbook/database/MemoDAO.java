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

/**
 * Model에서 사용할 DB 관련 추상 메서드를 가지고 있는 DAO 클래스
 * Room 라이브러리 사용
 *
 * @author 이윤복
 * @version 1.0
 */

@Dao
public interface MemoDAO {
    @Query("SELECT * FROM memo")
    List<MemoEntity> getMemoList();
    @Update
    int doEditMemo(MemoEntity memo);
    @Insert
    void doAddMemo(MemoEntity memo);
    @Delete
    int doDeleteMemo(MemoEntity memo);
}
