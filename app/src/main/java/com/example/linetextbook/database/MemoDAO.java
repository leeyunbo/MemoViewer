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
    /**
     * 모든 메모의 정보를 가져오는 메서드
     *
     * @return 메모들의 객체를 담고있는 List
     */
    @Query("SELECT * FROM memo")
    List<MemoEntity> getMemoList();

    /**
     * 메모를 수정하는 메서드
     * @param memo 변경시킬 메모 정보를 담고있는 객체
     * @return 변경된 데이터의 수
     */
    @Update
    int doEditMemo(MemoEntity memo);

    /**
     * 메모를 추가하는 메서드
     * @param memo 추가시킬 메모의 정보를 담고있는 객체
     */
    @Insert
    void doAddMemo(MemoEntity memo);

    /**
     * 메모를 삭제하는 메서드
     * @param memo 삭제할 메모의 정보를 담고있는 객체
     * @return 삭제된 데이터의 수
     */
    @Delete
    int doDeleteMemo(MemoEntity memo);
}
