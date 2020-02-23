package com.example.linetextbook.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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
    private static MemoDatabase sINSTANCE;
    private static final Object sLock = new Object();

    /**
     * 데이터베이스를 컨트롤 할 수 있는 객체를 가져올 수 있는 싱글턴 패턴 메서드
     *
     * @param context Application Context 객체
     * @return 데이터베이스 객체
     */
    public static MemoDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (sINSTANCE == null) {
                sINSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MemoDatabase.class, "Memo.db")
                        .build();
            }
            return sINSTANCE;
        }
    }
}
