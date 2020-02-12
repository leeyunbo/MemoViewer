package com.example.linetextbook.DO;

import android.graphics.Bitmap;

import java.util.LinkedList;

public class MemoDO {
    private int id;
    private String title;
    private String content;
    private LinkedList<Bitmap> imageList;

    public int getId() {
        return id;
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
