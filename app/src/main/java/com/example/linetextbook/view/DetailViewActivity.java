package com.example.linetextbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linetextbook.contract.DetailContract;
import com.example.linetextbook.Presenter.DetailPresenter;
import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailViewActivity extends AppCompatActivity implements DetailContract.view {
    private DetailPresenter presenter;
    @BindView(R.id.contentView)  TextView contentView;
    @BindView(R.id.titleView)  TextView titleView;
    //@BindView(R.id.imageView) private ImageView imageView;

    @Override
    public void deleteMemo(MemoEntity memo) {
        presenter.requestDeleteMemo(memo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        presenter = new DetailPresenter(this);
        ButterKnife.bind(this);
    }
}
