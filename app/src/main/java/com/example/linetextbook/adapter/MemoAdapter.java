package com.example.linetextbook.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.view.ListViewActivity;

import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private List<MemoEntity> memoData = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView list_title;
        TextView list_content;
        ImageView list_image;
        ViewHolder(View view) {
            super(view);
            list_title = view.findViewById(R.id.list_title);
            list_content = view.findViewById(R.id.list_content);
            list_image = view.findViewById(R.id.list_image);
        }
    }

    public MemoAdapter(List<MemoEntity> memoData) {
        this.memoData = memoData;
    }

    @NonNull
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_element_layout, parent, false);
        MemoAdapter.ViewHolder vh = new MemoAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MemoAdapter.ViewHolder holder, int position) {
        String title = memoData.get(position).getTitle();
        String content = memoData.get(position).getContent();
        String[] imageList = memoData.get(position).getImageList();
        if(imageList.length == 0) {
            holder.list_image.setImageResource(R.drawable.ic_no_image);
        }
        else {
            holder.list_image.setImageURI(Uri.parse(imageList[0]));
        }
        holder.list_title.setText(title);
        holder.list_content.setText(content);
    }

    @Override
    public int getItemCount() {
        return memoData == null ? null : memoData.size();
    }
}
