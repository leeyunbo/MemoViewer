package com.example.linetextbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Layout;
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
import com.example.linetextbook.view.ListViewActivity;

import java.util.List;

/**
 * ListViewActivity에서 사용하는 리사이클러 뷰에 대한 어뎁터 클래스
 *
 * @author 이윤복
 * @version 1.0
 */

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private Context context;
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
            view.setOnClickListener(new View.OnClickListener() {
                /**
                 * 메모를 클릭하게 되면 호출되는 이벤트 콜백 메서드
                 * 클릭된 메모의 상세보기로 이동한다.
                 *
                 * @param v 클릭된 View
                 */
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), com.example.linetextbook.view.DetailViewActivity.class);
                    intent.putExtra("MEMO",memoData.get(getAdapterPosition()));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public MemoAdapter(List<MemoEntity> memoData, Context context) {
        this.context = context;
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
        Glide.with(context).load(Uri.parse(imageList[0])).into(holder.list_image);
        //holder.list_image.setImageURI(Uri.parse(imageList[0]));
        holder.list_title.setText(title);
        holder.list_content.setText(content);
    }

    @Override
    public int getItemCount() {
        return memoData == null ? null : memoData.size();
    }
}
