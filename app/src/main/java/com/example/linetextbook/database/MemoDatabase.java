package com.example.linetextbook.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * DB의 인스턴스를 반환해주는 클래스
 * Room 라이브러리 사용
 *
 * @author 이윤복
 * @version 1.0
 */
@Database(entities = {MemoEntity.class}, version = 1)
public abstract class MemoDatabase extends RoomDatabase {
    public abstract MemoDAO memoDAO();
    private static MemoDatabase INSTANCE;
    private static final Object Lock = new Object();

    public static MemoDatabase getInstance(Context context) {
        synchronized (Lock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MemoDatabase.class, "Memo.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
