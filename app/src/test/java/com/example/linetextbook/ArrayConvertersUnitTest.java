package com.example.linetextbook;

import com.example.linetextbook.converters.ArrayConverters;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;


public class ArrayConvertersUnitTest {
    String[] array;
    String string;

    /**
     * 테스트 데이터 생성
     */
    @Before
    public void createData() {
        array = new String[100];
        for(int i=0; i<100; i++) {
            String si = String.valueOf(i);
            array[i] = si;
        }
    }

    /**
     * array를 이용하여, string을 생성하고, 다시 string을 이용하여 array를 생성한다.
     * 결과로 나온 array와 처음 데이터로 사용한 array의 사이즈가 같은지 확인한다.
     *
     * array를 이용하여 List를 생성하고, 생성된 List와 array의 사이즈가 같은지 확인한다.
     */
    @Test
    public void testConvertFunction() {
        string = ArrayConverters.convertArrayToString(array);
        assertEquals(ArrayConverters.convertStringToArray(string).length,array.length);
        assertEquals(ArrayConverters.convertArrayToList(array).size(), array.length);
    }

}
