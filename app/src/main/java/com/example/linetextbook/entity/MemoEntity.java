package com.example.linetextbook.entity;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.LinkedList;
@Entity(tableName = "memo")
public class MemoEntity {
    @PrimaryKey(autoGenerate = true) private int id;
    @ColumnInfo(name = "title") private String title;
    @ColumnInfo(name = "content") private String content;
    @ColumnInfo(name = "time") private String time;
    @ColumnInfo(name = "image") private LinkedList<Bitmap> imageList;

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LinkedList<Bitmap> getImageList() {
        return imageList;
    }

    public void setImageList(LinkedList<Bitmap> imageList) {
        this.imageList = imageList;
    }
}
