package com.example.linetextbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.linetextbook.IsUiTestCheck;
import com.example.linetextbook.contract.DetailContract;
import com.example.linetextbook.Presenter.DetailPresenter;
import com.example.linetextbook.R;
import com.example.linetextbook.converters.ArrayConverters;
import com.example.linetextbook.database.MemoEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * 사용자가 터치한 메모의 상세한 정보를 UI를 통해 보여주는 액티비티
 * 사용자는 메뉴를 통해 편집,삭제 기능을 이용할 수 있다.
 * ButterKnife 라이브러리 사용 https://github.com/JakeWharton/butterknife
 * Glide 라이브러리 사용 https://github.com/bumptech/glide
 *
 * @author 이윤복
 * @version 1.0
 */

public class DetailViewActivity extends AppCompatActivity implements DetailContract.view {
    private boolean isRunningUiTest = IsUiTestCheck.isRunningUiTest; // Ui Test?
    private DetailPresenter mPresenter;
    private MemoEntity mMemo;
    @BindView(R.id.detail_content_view)  TextView detail_content_view;
    @BindView(R.id.detail_title_view)  TextView detail_title_view;
    @BindView(R.id.detail_time_view) TextView detail_time_view;
    @BindView(R.id.detail_image_container) LinearLayout detail_image_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        mPresenter = new DetailPresenter(this);
        ButterKnife.bind(this);
        if(isRunningUiTest == true) testShowMemoDetail();
        else showMemoDetail();
    }

    /**
     * 사용자가 메뉴의 삭제 버튼을 클릭한 경우 호출되는 콜백 메서드
     * DetailPresenter에게 삭제 요청을 전송한다.
     */
    @Override
    public void deleteMemo()
    {
        mPresenter.requestDeleteMemo(mMemo);
    }

    /**
     * <Ui Test용 메서드>
     * 작성된 테스트 데이터의 정보를 뷰에 뿌려준다.
     */
    public void testShowMemoDetail() {
        MemoEntity testMemo = new MemoEntity("제목","내용","시간",null);
        String[] mListImageUrl;
        ImageView mImageView;

        mListImageUrl = testMemo.getArrayImageUrl();
        detail_title_view.setText(testMemo.getTitle());
        detail_content_view.setText(testMemo.getContent());
        detail_time_view.setText(testMemo.getTime());
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(
                (int) getResources().getDimension(R.dimen.imageview_margin),
                (int) getResources().getDimension(R.dimen.imageview_margin),
                (int) getResources().getDimension(R.dimen.imageview_margin),
                (int) getResources().getDimension(R.dimen.imageview_margin)
        );

        if(mListImageUrl == null) return;
        for(String mImageUrl : mListImageUrl) {
            mImageView = new ImageView(this);
            Glide.with(this)
                    .load(Uri.parse(mImageUrl))
                    .error(R.drawable.ic_broken_image)
                    .into(mImageView);
            mImageView.setLayoutParams(layoutParams);
            mImageView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.imageview_height);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            detail_image_container.addView(mImageView);
        }
    }

    /**
     * 사용자가 클릭한 메모의 정보를 출력해주는 메서드
     * Intent를 통해 직렬화되어 넘어온 객체를 역직렬화를 통해 객체 형태로 변환 후 View에 뿌려준다.
     */
    @Override
    public void showMemoDetail() {
        Intent mFromListViewIntent;
        String[] mArrayImageUrl;
        ImageView mImageView;

        mFromListViewIntent = getIntent();
        mMemo = (MemoEntity) mFromListViewIntent.getSerializableExtra("MEMO");
        if(mMemo == null) return;
        mArrayImageUrl = mMemo.getArrayImageUrl();
        detail_title_view.setText(mMemo.getTitle());
        detail_content_view.setText(mMemo.getContent());
        detail_time_view.setText(mMemo.getTime());
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(
                (int) getResources().getDimension(R.dimen.imageview_margin),
                (int) getResources().getDimension(R.dimen.imageview_margin),
                (int) getResources().getDimension(R.dimen.imageview_margin),
                (int) getResources().getDimension(R.dimen.imageview_margin)
        );

        if(mArrayImageUrl == null) return;
        for(String mImageUrl : mArrayImageUrl) {
            mImageView = new ImageView(this);
            Glide.with(this)
                    .load(Uri.parse(mImageUrl))
                    .error(R.drawable.ic_broken_image)
                    .into(mImageView);
            mImageView.setLayoutParams(layoutParams);
            mImageView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.imageview_height);
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            detail_image_container.addView(mImageView);
        }
    }



    /**
     * presenter에게 한 요청이 완료되면 presenter에 의해 호출되는 콜백 메서드
     * ListViewActivity로 돌아간다.
     */
    @Override
    public void backListView() {
        Intent mStartListViewIntent;

        mStartListViewIntent = new Intent(this, ListViewActivity.class);
        mStartListViewIntent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mStartListViewIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mStartEditViewIntent;

        switch (item.getItemId()) {
            case R.id.menu_edit_btn:
                mStartEditViewIntent = new Intent(this,com.example.linetextbook.view.EditViewActivity.class);
                mStartEditViewIntent.putExtra("MEMO", mMemo);
                mStartEditViewIntent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mStartEditViewIntent);
                return true;
            case R.id.menu_delete_btn:
                deleteMemo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
