package com.example.linetextbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.view.DetailViewActivity;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    List<String> imageList;
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView add_imageView;
        ViewHolder(View view) {
            super(view);
            add_imageView = view.findViewById(R.id.add_imageView);
        }
    }

    public ImageAdapter(List<String> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_image_layout, parent, false);
        ImageAdapter.ViewHolder vh = new ImageAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        holder.add_imageView.setImageURI(Uri.parse(imageList.get(position)));
    }

    @Override
    public int getItemCount() {
        return imageList == null ? null : imageList.size();
    }
}
