package com.example.linetextbook.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.linetextbook.dao.MemoDAO;

@Database(entities = {MemoDAO.class}, version = 1)
public abstract class MemoDatabase extends RoomDatabase {
    public abstract MemoDAO memoDAO();

    private static MemoDatabase INSTANCE;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    public static synchronized MemoDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static MemoDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context,
                MemoDatabase.class, "database-name.db")
                .addMigrations(MIGRATION_1_2)
                .build();
    }

}
