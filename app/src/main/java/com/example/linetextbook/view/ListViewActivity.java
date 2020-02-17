package com.example.linetextbook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.linetextbook.adapter.MemoAdapter;
import com.example.linetextbook.contract.ListContract;
import com.example.linetextbook.Presenter.ListPresenter;
import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoDAO;
import com.example.linetextbook.database.MemoEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListViewActivity extends AppCompatActivity implements ListContract.view {
    private ListPresenter presenter;
    private List<MemoEntity> memoData;
    @BindView(R.id.memoCount) TextView memoCount;
    @BindView(R.id.addButton) ImageView addButton;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        presenter = new ListPresenter(this);
        ButterKnife.bind(this);
        showMemoList();
    }

    /*사용자가 등록한 모든 메모를 리스트뷰에 출력*/
    @Override
    public void showMemoList() {
        presenter.requestMemoList();
    }

    /*데이터를 다 받아왔으면 RecyclerView에 뿌려줌 */
    @Override
    public void changeRecyclerView(List<MemoEntity> memoData) {
        this.memoData = memoData;
        if(memoData.size() == 0) {
            memoCount.setText("현재 " + 0 + "개의 메모");
            return;
        }
        MemoAdapter adapter = new MemoAdapter(memoData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        memoCount.setText("현재 " + memoData.size() + "개의 메모");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /*사용자가 addButton 클릭 이벤트 발생시, 메모 생성 액티비티로 이동*/
    @OnClick(R.id.addButton)
    public void addBtnClick() {
        Intent intent = new Intent(this, addViewActivity.class);
        startActivity(intent);
    }

}
