package com.example.linetextbook.database;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 메모의 정보를 담을 수 있는 DO 클래스
 * ButterKnife 라이브러리 사용 https://github.com/JakeWharton/butterknife
 * Room 라이브러리 사용
 *
 * @author 이윤복
 * @version 1.0
 */
@Entity(tableName = "memo")
public class MemoEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "image")
    private String imageUrl;
    @Ignore
    private String[] imageList;

    public MemoEntity() {}
    public MemoEntity(String title, String content, String time, String[] imageList) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.imageList = imageList;
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

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public void setTime(String time) {
        this.time = time;
    }

    public void setImageList(String[] imageList) { this.imageList = imageList; }

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

    public String[] getImageList() { return imageList; }
}

