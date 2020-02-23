package com.example.linetextbook.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * 메모의 정보를 담을 수 있는 DO 클래스
 * ButterKnife 라이브러리 사용 https://github.com/JakeWharton/butterknife
 * Room 라이브러리 사용
 *
 * 1. Column 정보
 * int id : 메모의 고유 ID를 담고 있는 Column, Primary Key로 지정
 * String title : 메모의 제목을 담고 있는 Column
 * String content : 메모의 내용을 담고 있는 Column
 * String time : 메모의 작성 시간을 담고 있는 Column
 * String stringImageUrl : 메모에 담겨있는 이미지들의 URL 정보들을 String 형식으로 담고 있는 Column
 *
 * 2. 그 외 추가 정보
 * String[] arrayImageUrl : 메모에 담겨있는 이미지들의 URL 정보들을 List 형식으로 담고 있는 변수, 해당 List를 String으로 변환시켜 데이터베이스에 저장한다.
 *
 * @author 이윤복
 * @version 1.0
 */
@Entity(tableName = "memo")
public class MemoEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title") //메모의 제목을 담고 있는 Column
    private String title;
    @ColumnInfo(name = "content") //
    private String content;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "image")
    private String stringImageUrl;
    @Ignore
    private String[] arrayImageUrl;

    public MemoEntity() {}
    public MemoEntity(String title, String content, String time, String[] listImageUrl) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.arrayImageUrl = listImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public String getStringImageUrl() { return stringImageUrl; }

    public void setStringImageUrl(String stringImageUrl) { this.stringImageUrl = stringImageUrl; }

    public void setTime(String time) {
        this.time = time;
    }

    public void setArrayImageUrl(String[] arrayImageUrl) { this.arrayImageUrl = arrayImageUrl; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getArrayImageUrl() { return arrayImageUrl; }
}

