package com.example.linetextbook.converters;

import java.util.ArrayList;
import java.util.List;

/**
 * 타입 변환 메서드를 가지고 있는 클래스
 *
 * @author 이윤복
 * @version 1.0
 */


public class ArrayConverters {
    public static String sStrSeparator = "__,__";

    /**
     * Array 타입을 String 타입으로 변환하는 메서드
     *
     * @param array String으로 변환시킬 Array 타입 변수
     * @return Array 타입 변수를 String으로 변환시켜 반환
     */
    public static String convertArrayToString(String[] array){
        String mStr = "";
        for (int i = 0;i<array.length; i++) {
            mStr = mStr+array[i];
            if(i<array.length-1){
                mStr = mStr + sStrSeparator;
            }
        }
        return mStr;
    }

    /**
     * String 타입을 Array 타입으로 변환한다.
     *
     * @param str 변환할 String 타입 변수
     * @return String 타입 변수를 Array 타입으로 변환시켜 반환
     */
    public static String[] convertStringToArray(String str){
        String[] mArr = str.split(sStrSeparator);
        return mArr;
    }

    /**
     * Array 타입을 List 타입으로 변환한다.
     *
     * @param array 변환할 Array 타입 변수
     * @return Array 타입 변수를 List 타입 객체로 변환시켜 반환
     */
    public static List<String> convertArrayToList(String[] array) {
        List<String> mList = new ArrayList<String>();
        for(int i=0; i<array.length; i++) {
            mList.add(array[i]);
        }
        return mList;
    }
}
