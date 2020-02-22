package com.example.linetextbook;
import android.content.Context;

import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.linetextbook.database.MemoDAO;
import com.example.linetextbook.database.MemoDatabase;
import com.example.linetextbook.database.MemoEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * 내부 저장소에 Edit과 Delete 동작을 테스트하는 테스트 코드
 *
 * @author 이윤복
 * @version 1.0
 */

@RunWith(AndroidJUnit4ClassRunner.class)
public class MemoEntityEditDeleteTest {
    private MemoDAO memoDAO;
    private MemoDatabase db;
    @Before
    public void createDb(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(appContext, MemoDatabase.class).build();
        memoDAO = db.memoDAO();
    }

    @After
    public void end() {
        db.close();
    }

    @Test
    public void writeMemoAndReadInList(){
        int cnt;
        List<MemoEntity> memoList;

        /**
         * 테스트 데이터 8개 생성
         */
        MemoEntity memo = new MemoEntity("title","content","1시 30분",null);
        MemoEntity memo2 = new MemoEntity("title","content","1시 30분",null);
        MemoEntity memo3 = new MemoEntity("title","content","1시 30분",null);
        MemoEntity memo4 = new MemoEntity("title","content","1시 30분",null);
        MemoEntity memo5 = new MemoEntity("title","content","1시 30분",null);
        MemoEntity memo6 = new MemoEntity("title","content","1시 30분",null);
        MemoEntity memo7 = new MemoEntity("title","content","1시 30분",null);
        MemoEntity memo8 = new MemoEntity("title","content","1시 30분",null);


        /**
         * DB INSERT
         */
        memoDAO.doAddMemo(memo);
        memoDAO.doAddMemo(memo2);
        memoDAO.doAddMemo(memo3);
        memoDAO.doAddMemo(memo4);
        memoDAO.doAddMemo(memo5);
        memoDAO.doAddMemo(memo6);
        memoDAO.doAddMemo(memo7);
        memoDAO.doAddMemo(memo8);

        /**
         * DB INSERT 및 DB SELECT 결과 확인
         */
        memoList = memoDAO.getMemoList();
        assertNotNull(memoList);
        assertEquals(memoList.size(),8);

        /**
         * DB EDIT 결과 확인, memo 3개 정보 변경
         */
        memoList.get(0).setTitle("Edit_title");
        cnt = memoDAO.doEditMemo(memoList.get(0));
        assertSame(cnt, 1);

        memoList.get(1).setTitle("Edit_title");
        cnt = memoDAO.doEditMemo(memoList.get(1));
        assertSame(cnt, 1);

        memoList.get(2).setTitle("Edit_title");
        cnt = memoDAO.doEditMemo(memoList.get(2));
        assertSame(cnt, 1);


        /**
         * DB Delete 결과 확인, 데이터 3개 제거
         */
        memoList = memoDAO.getMemoList();
        assertNotNull(memoList);
        assertEquals(memoList.size(),8);

        memoDAO.doDeleteMemo(memoList.get(0));
        memoDAO.doDeleteMemo(memoList.get(1));
        memoDAO.doDeleteMemo(memoList.get(2));

        memoList = memoDAO.getMemoList();
        assertNotNull(memoList);
        assertSame(memoList.size(), 8-3);



    }
}
