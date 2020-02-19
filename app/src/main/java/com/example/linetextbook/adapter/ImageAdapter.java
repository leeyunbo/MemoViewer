package com.example.linetextbook.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.linetextbook.R;
import com.example.linetextbook.view.AddViewActivity;

import java.util.List;

/**
 * AddViewActivity에서 사용하는 리사이클러 뷰에 대한 어뎁터 클래스
 * Glide 라이브러리 사용 https://github.com/bumptech/glide
 * RecyclerView 라이브러리 사용
 *
 * @author 이윤복
 * @version 1.0
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    List<String> imageList;
    AddViewActivity context;
    ImageView delete_image_btn;
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView add_imageView;
        ViewHolder(View view) {
            super(view);
            add_imageView = view.findViewById(R.id.add_imageView);
            delete_image_btn = view.findViewById(R.id.delete_image_btn);
            delete_image_btn.setOnClickListener(new View.OnClickListener() {
                /**
                 * 사용자가 메모 수정 화면에서 사진의 엑스 버튼을 클릭하면 호출되는 이벤트 콜백 메서드
                 * 해당 이미지가 삭제된다.
                 *
                 * @param v 클릭된 View
                 */
                @Override
                public void onClick(View v) {
                    imageList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), imageList.size());
                    context.notifyDeleteImage(imageList);
                }
            });
        }
    }

    public ImageAdapter(List<String> imageList, AddViewActivity context) {
        this.imageList = imageList;
        this.context = context;
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
        if(imageList.get(position).contains("http://") || imageList.get(position).contains("https://")) {
            Glide.with(context).load(imageList.get(position)).error(R.drawable.ic_broken_image).into(holder.add_imageView);

        }
        else{
            Glide.with(context).load(Uri.parse(imageList.get(position))).error(R.drawable.ic_broken_image).into(holder.add_imageView);
        }
    }

    @Override
    public int getItemCount() {
        return imageList == null ? null : imageList.size();
    }
}
