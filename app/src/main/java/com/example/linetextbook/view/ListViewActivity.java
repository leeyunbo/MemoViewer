package com.example.linetextbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.linetextbook.contract.ListContract;
import com.example.linetextbook.Presenter.ListPresenter;
import com.example.linetextbook.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListViewActivity extends AppCompatActivity implements ListContract.view {
    private ListPresenter presenter;
    @BindView(R.id.memoCount) TextView memoCount;
    @BindView(R.id.addButton) ImageView addButton;
    @BindView(R.id.listView) ListView listView;

    /*사용자가 등록한 모든 메모를 리스트뷰에 출력*/
    @Override
    public void showMemoList() {
        presenter.requestMemoList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        presenter = new ListPresenter(this);
        ButterKnife.bind(this);
        showMemoList();
    }

    /*사용자가 addButton 클릭 이벤트 발생시, 메모 생성 액티비티로 이동*/
    @OnClick(R.id.addButton)
    public void addBtnClick() {
        Intent intent = new Intent(this, addViewActivity.class);
        startActivity(intent);
    }
}
