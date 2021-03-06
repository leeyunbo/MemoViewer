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

import com.bumptech.glide.Glide;
import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoEntity;
import com.example.linetextbook.view.DetailViewActivity;

import java.util.List;

/**
 * ListViewActivity에서 사용하는 리사이클러 뷰에 대한 어뎁터 클래스
 * Glide 라이브러리 사용 https://github.com/bumptech/glide
 * RecyclerView 라이브러리 사용
 *
 * @author 이윤복
 * @version 1.0
 */

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {
    private Context mContext;
    private List<MemoEntity> mListMemoDatas;

    public MemoAdapter(List<MemoEntity> ListMemoDatas, Context context) {
        this.mContext = context;
        this.mListMemoDatas = ListMemoDatas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView memo_list_title;
        TextView memo_list_content;
        ImageView memo_list_imageView;
        ViewHolder(View view) {
            super(view);
            memo_list_title = view.findViewById(R.id.memo_list_title);
            memo_list_content = view.findViewById(R.id.memo_list_content);
            memo_list_imageView = view.findViewById(R.id.memo_list_imageView);
            view.setOnClickListener(new View.OnClickListener() {
                /**
                 * 메모를 클릭하게 되면 호출되는 이벤트 콜백 메서드
                 * 클릭된 메모의 상세보기로 이동한다.
                 *
                 * @param v 클릭된 View
                 */
                @Override
                public void onClick(View v) {
                    Intent mIntent;

                    mIntent = new Intent(mContext, DetailViewActivity.class);
                    mIntent.putExtra("MEMO", mListMemoDatas.get(getAdapterPosition()));
                    mContext.startActivity(mIntent);
                }
            });
        }
    }

    @NonNull
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater;
        View mView;
        MemoAdapter.ViewHolder mViewHolder;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.memo_list_layout, parent, false);
        mViewHolder = new MemoAdapter.ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemoAdapter.ViewHolder holder, int position) {
        MemoEntity mMemo;
        String[] mListImageUrl;

        mMemo = mListMemoDatas.get(position);
        holder.memo_list_title.setText(mMemo.getTitle());
        holder.memo_list_content.setText(mMemo.getContent());

        /**
         * 만약 해당 메모의 이미지가 존재하지 않는다면, 이미지 없음을 표시한다.
         */
        if(mMemo.getStringImageUrl() == null || mMemo.getArrayImageUrl() == null) {
            Glide.with(mContext).
                    load(R.drawable.ic_no_image).
                    into(holder.memo_list_imageView);
        }

        /**
         * 해당 메모의 이미지가 존재한다면, 가지고 있는 이미지 리스트의 첫번째 사진을 표시한다.
         */
        else {
            mListImageUrl = mMemo.getArrayImageUrl();
            Glide.with(mContext)
                    .load(Uri.parse(mListImageUrl[0]))
                    .error(R.drawable.ic_broken_image)
                    .into(holder.memo_list_imageView);
        }
    }

    @Override
    public int getItemCount() {
        return mListMemoDatas == null ? null : mListMemoDatas.size();
    }
}
