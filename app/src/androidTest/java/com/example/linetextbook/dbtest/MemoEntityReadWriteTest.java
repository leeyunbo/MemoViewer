package com.example.linetextbook.dbtest;

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

/**
 * 내부 저장소에 Read와 Write 동작을 테스트하는 데이터베이스 테스트 코드
 *
 * @author 이윤복
 * @version 1.0
 */

@RunWith(AndroidJUnit4ClassRunner.class)
public class MemoEntityReadWriteTest {
    private MemoDAO mMemoDao;
    private MemoDatabase mDatabase;

    /**
     * DB를 컨트롤할 수 있는 객체를 생성한다.
     */
    @Before
    public void createDb(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mDatabase = Room.inMemoryDatabaseBuilder(appContext, MemoDatabase.class).build();
        mMemoDao = mDatabase.memoDAO();
    }

    /**
     * DB 종료 메서드
     */
    @After
    public void end() {
        mDatabase.close();
    }

    /**
     * DB에 대한 Read와 Write를 테스트하는 메서드
     */
    @Test
    public void writeMemoAndReadInList(){
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
        mMemoDao.doAddMemo(memo);
        mMemoDao.doAddMemo(memo2);
        mMemoDao.doAddMemo(memo3);
        mMemoDao.doAddMemo(memo4);
        mMemoDao.doAddMemo(memo5);
        mMemoDao.doAddMemo(memo6);
        mMemoDao.doAddMemo(memo7);
        mMemoDao.doAddMemo(memo8);

        /**
         * DB INSERT 및 DB SELECT 결과 확인
         */
        memoList = mMemoDao.getMemoList();
        assertNotNull(memoList);
        assertEquals(memoList.size(),8);

        assertNotNull(memoList.get(0));
        assertNotNull(memoList.get(1));
        assertNotNull(memoList.get(2));
        assertNotNull(memoList.get(3));
        assertNotNull(memoList.get(4));
        assertNotNull(memoList.get(5));
        assertNotNull(memoList.get(6));
        assertNotNull(memoList.get(7));

        assertEquals(memoList.get(0).getTitle(),memo.getTitle());
        assertEquals(memoList.get(1).getTitle(),memo2.getTitle());
        assertEquals(memoList.get(2).getTitle(),memo3.getTitle());
        assertEquals(memoList.get(3).getTitle(),memo4.getTitle());
        assertEquals(memoList.get(4).getTitle(),memo5.getTitle());
        assertEquals(memoList.get(5).getTitle(),memo6.getTitle());
        assertEquals(memoList.get(6).getTitle(),memo7.getTitle());
        assertEquals(memoList.get(7).getTitle(),memo8.getTitle());
    }
}
