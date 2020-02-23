package com.example.linetextbook.adapter;

import android.content.Context;
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
    private List<String> mListImageUrl;
    private AddViewActivity mContext;

    public ImageAdapter(List<String> imageList, AddViewActivity context) {
        this.mListImageUrl = imageList;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_list_imageView;
        ImageView image_list_delete_btn;
        ViewHolder(View view) {
            super(view);
            image_list_imageView = view.findViewById(R.id.image_list_imageView);
            image_list_delete_btn = view.findViewById(R.id.image_list_delete_btn);
            image_list_delete_btn.setOnClickListener(new View.OnClickListener() {
                /**
                 * 사용자가 메모 수정 화면에서 사진의 엑스 버튼을 클릭하면 호출되는 이벤트 콜백 메서드
                 * 해당 이미지가 삭제된다.
                 *
                 * @param v 클릭된 View
                 */
                @Override
                public void onClick(View v) {
                    int mPosition = getAdapterPosition();
                    mListImageUrl.remove(mPosition);
                    notifyItemRemoved(mPosition);
                    notifyItemRangeChanged(mPosition, mListImageUrl.size());
                    mContext.notifyDeleteImage(mListImageUrl);
                }
            });
        }
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater;
        View mView;
        ImageAdapter.ViewHolder mViewHolder;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.image_list_layout, parent, false);
        mViewHolder = new ImageAdapter.ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String mImageUrl;

        mImageUrl = mListImageUrl.get(position);
        Glide.with(mContext)
                .load(mImageUrl)
                .error(R.drawable.ic_broken_image)
                .into(holder.image_list_imageView);
    }

    @Override
    public int getItemCount() {
        return mListImageUrl == null ? null : mListImageUrl.size();
    }
}
