package com.example.linetextbook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linetextbook.IsUiTestCheck;
import com.example.linetextbook.adapter.MemoAdapter;
import com.example.linetextbook.contract.ListContract;
import com.example.linetextbook.Presenter.ListPresenter;
import com.example.linetextbook.R;
import com.example.linetextbook.database.MemoEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * 사용자가 작성한 모든 메모를 UI를 통해 보여주는 액티비티
 * ButterKnife 라이브러리 사용 https://github.com/JakeWharton/butterknife
 * RecyclerView 라이브러리 사용
 * CardView 라이브러리 사용
 *
 * @author 이윤복
 * @version 1.0
 */

public class ListViewActivity extends AppCompatActivity implements ListContract.view {

    private boolean isRunningUiTest = IsUiTestCheck.isRunningUiTest; //Ui Test?
    private ListPresenter mPresenter;
    private List<MemoEntity> mListMemoDatas;
    private String[] mPermissions =
                    {Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_EXTERNAL_STORAGE};

    @BindView(R.id.list_memoCount) TextView list_memoCount;
    @BindView(R.id.list_add_Btn) ImageView list_add_btn;
    @BindView(R.id.list_memoList) RecyclerView list_memoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mPresenter = new ListPresenter(this);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        if(Build.VERSION.SDK_INT >= 23){
            if(PackageManager.PERMISSION_GRANTED !=
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                requestPermissions(mPermissions, 0);
            }
        }
        if(isRunningUiTest) testShowMemoList();
        else
            showMemoList();
    }

    /**
     * 사용자가 addButton을 클릭했을 때, 발생하는 이벤트 콜백 메서드
     * addViewActivity로 이동한다.
     */
    @OnClick(R.id.list_add_Btn)
    public void addBtnClick() {
        Intent mStartAddViewIntent;

        mStartAddViewIntent =
                new Intent(this, com.example.linetextbook.view.AddViewActivity.class);
        mStartAddViewIntent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mStartAddViewIntent);
    }

    /**
     * <Ui Test용 메서드>
     * 작성된 테스트 데이터 리스트를 RecyclerView에 뿌려준다.
     */
    public void testShowMemoList() {
        List<MemoEntity> testMemoData = new ArrayList<>();
        testMemoData.add(new MemoEntity("제목","내용","시간",null));
        changeRecyclerView(testMemoData);
    }

    /**
     * 사용자가 추가한 모든 메모의 정보를 DB에서 가져올 수 있도록 ListPresenter에게 요청을 전송한다.
     */
    @Override
    public void showMemoList() {
        mPresenter.requestMemoList();
    }

    /**
     * ListPresenter에게 한 요청이 완료되었을 때, ListPresenter에게 호출되어지는 콜백메서드
     * RecyclerView에 모든 메모를 뿌려준다.
     *
     * @param memoData 사용자가 추가한 모든 메모의 DO 객체를 담고있는 List
     */
    @Override
    public void changeRecyclerView(List<MemoEntity> memoData) {
        MemoAdapter mMemoAdapter;

        this.mListMemoDatas = memoData;
        mMemoAdapter = new MemoAdapter(memoData,this);
        list_memoList.setLayoutManager(new LinearLayoutManager(this));
        list_memoList.setAdapter(mMemoAdapter);
        list_memoList.setItemAnimator(new DefaultItemAnimator());
        list_memoList.setNestedScrollingEnabled(false);
        list_memoCount.setText("현재 " + memoData.size() + "개의 메모");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                else {
                    Toast.makeText(this,"권한을 허용하셔야 이용가능합니다.",Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
        }
    }

}
