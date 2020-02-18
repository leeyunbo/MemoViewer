package com.example.linetextbook.view;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.linetextbook.contract.DetailContract;
import com.example.linetextbook.Presenter.DetailPresenter;
import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoEntity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailViewActivity extends AppCompatActivity implements DetailContract.view {
    private DetailPresenter presenter;
    private MemoEntity memo;
    @BindView(R.id.contentView)  TextView contentView;
    @BindView(R.id.titleView)  TextView titleView;
    @BindView(R.id.detail_time_view) TextView detail_time_view;
    @BindView(R.id.detail_image_container) LinearLayout detail_image_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        presenter = new DetailPresenter(this);
        ButterKnife.bind(this);
        showMemoDetail();
    }

    @Override
    public void deleteMemo()
    {
        presenter.requestDeleteMemo(memo);
    }

    @Override
    public void showMemoDetail() {
        Intent intent = getIntent();
        memo = (MemoEntity) intent.getSerializableExtra("MEMO");
        String[] imageList = memo.getImageList();
        titleView.setText(memo.getTitle());
        contentView.setText(memo.getContent());
        detail_time_view.setText(memo.getTime());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(
                (int) getResources().getDimension(R.dimen.imageview_margin),
                (int) getResources().getDimension(R.dimen.imageview_margin),
                (int) getResources().getDimension(R.dimen.imageview_margin),
                (int) getResources().getDimension(R.dimen.imageview_margin)
        );
        for(String image : imageList) {
            ImageView imageView = new ImageView(this);
            imageView.setImageURI(Uri.parse(image));
            imageView.setLayoutParams(layoutParams);
            imageView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.imageview_height);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            detail_image_container.addView(imageView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_view_menu, menu);
        return true;
    }

    @Override
    public void backListView() {
        Intent intent = new Intent(this, ListViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_edit_btn:
                intent = new Intent(this,EditViewActivity.class);
                intent.putExtra("MEMO",memo);
                startActivity(intent);
                return true;
            case R.id.menu_delete_btn:
                presenter.requestDeleteMemo(memo);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
