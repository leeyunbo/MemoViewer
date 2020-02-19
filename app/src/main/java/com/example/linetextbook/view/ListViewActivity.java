package com.example.linetextbook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linetextbook.adapter.MemoAdapter;
import com.example.linetextbook.contract.ListContract;
import com.example.linetextbook.Presenter.ListPresenter;
import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 사용자가 작성한 모든 메모를 UI를 통해 보여주는 액티비티
 *
 * @author 이윤복
 * @version 1.0
 */

public class ListViewActivity extends AppCompatActivity implements ListContract.view {
    private ListPresenter presenter;
    private List<MemoEntity> memoData;
    @BindView(R.id.memoCount) TextView memoCount;
    @BindView(R.id.addButton) ImageView addButton;
    @BindView(R.id.recyclerView) RecyclerView memoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        presenter = new ListPresenter(this);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        showMemoList();
    }

    /**
     * 사용자가 추가한 모든 메모의 정보를 DB에서 가져올 수 있도록 ListPresenter에게 요청을 전송한다.
     */
    @Override
    public void showMemoList() {
        presenter.requestMemoList();
    }

    /**
     * ListPresenter에게 한 요청이 완료되었을 때, ListPresenter에게 호출되어지는 콜백메서드
     * RecyclerView에 모든 메모를 뿌려준다.
     *
     * @param memoData 사용자가 추가한 모든 메모의 DO 객체를 담고있는 List
     */
    @Override
    public void changeRecyclerView(List<MemoEntity> memoData) {
        this.memoData = memoData;
        MemoAdapter adapter = new MemoAdapter(memoData,this);
        memoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        memoRecyclerView.setAdapter(adapter);
        memoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        memoRecyclerView.setNestedScrollingEnabled(false);
        memoCount.setText("현재 " + memoData.size() + "개의 메모");
    }

    /**
     * 사용자가 addButton을 클릭했을 때, 발생하는 이벤트 콜백 메서드
     * addViewActivity로 이동한다.
     */
    @OnClick(R.id.addButton)
    public void addBtnClick() {
        Intent intent = new Intent(this, AddViewActivity.class);
        startActivity(intent);
    }

}
